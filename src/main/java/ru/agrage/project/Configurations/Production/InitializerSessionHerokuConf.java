package ru.agrage.project.Configurations.Production;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import ru.agrage.project.Configurations.Develop.RedisDatabaseLocaleConf;

public class InitializerSessionHerokuConf extends AbstractHttpSessionApplicationInitializer {
    public InitializerSessionHerokuConf() {
        super(RedisDatabaseLocaleConf.class);
    }
}
