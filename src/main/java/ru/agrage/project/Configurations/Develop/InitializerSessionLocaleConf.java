package ru.agrage.project.Configurations.Develop;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class InitializerSessionLocaleConf extends AbstractHttpSessionApplicationInitializer {
    public InitializerSessionLocaleConf() {
        super(RedisDatabaseLocaleConf.class);
    }
}