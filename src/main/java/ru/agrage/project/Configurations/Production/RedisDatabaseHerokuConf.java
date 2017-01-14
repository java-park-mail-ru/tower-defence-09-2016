package ru.agrage.project.Configurations.Production;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import ru.agrage.project.Configurations.SystemConstants;

import java.net.URI;


@Configuration
@Profile(SystemConstants.SPRING_PROFILE_PRODUCTION)
@EnableRedisHttpSession(
        maxInactiveIntervalInSeconds = 86400
)
public class RedisDatabaseHerokuConf {
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        URI redistogoUri = URI.create("redis://h:paa915b672b71f2439f5b941a91db40edfa27f1d3ca019ee669928f4e720dcbb7@ec2-184-72-246-90.compute-1.amazonaws.com:13689");
        connectionFactory.setUsePool(true);
        connectionFactory.setHostName(redistogoUri.getHost());
        connectionFactory.setPort(redistogoUri.getPort());
        connectionFactory.setPassword(redistogoUri.getUserInfo().split(":", 2)[1]);
        return connectionFactory;
    }

    @Bean
    public RedisTemplate< String, Object > redisTemplate() {
        final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
        template.setConnectionFactory( connectionFactory() );
        template.setKeySerializer( new StringRedisSerializer() );
        template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        return template;
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("SESSION");
        serializer.setCookieMaxAge(86400);
        serializer.setCookiePath("/");
        return serializer;
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
