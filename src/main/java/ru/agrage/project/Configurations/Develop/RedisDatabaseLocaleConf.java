package ru.agrage.project.Configurations.Develop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import ru.agrage.project.Configurations.SystemConstants;

@Configuration
@Profile(SystemConstants.SPRING_PROFILE_DEVELOPMENT)
public class RedisDatabaseLocaleConf {
    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(6379);
        return connectionFactory;
    }
}
