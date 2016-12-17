package ru.agrage.project.Configurations.Production;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import ru.agrage.project.Configurations.SystemConstants;

import java.net.URI;


@Configuration
@Profile(SystemConstants.SPRING_PROFILE_PRODUCTION)
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
}
