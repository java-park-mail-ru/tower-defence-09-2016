package ru.agrage.project.Configurations;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class InitializerSecurityConf extends AbstractHttpSessionApplicationInitializer {
    public InitializerSecurityConf() {
        super (SecurityConfig.class);
    }
}
