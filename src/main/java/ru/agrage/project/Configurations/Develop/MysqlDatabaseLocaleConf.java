package ru.agrage.project.Configurations.Develop;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.agrage.project.Configurations.SystemConstants;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@ComponentScan({ "ru.agrage.project.Configurations.Develop" })
@Profile(SystemConstants.SPRING_PROFILE_DEVELOPMENT)
public class MysqlDatabaseLocaleConf {

    //  Объявляем источник данных с базой "agrage"
    @Bean
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource basicDataSource = new DriverManagerDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/agrage");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("626836");
        return basicDataSource;
    }

    /*
     *  Самая важная часть
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource()); // Внедряем источник данных в фабрику сеансов
        sessionFactory.setPackagesToScan(new String[] { "ru.agrage.project.Models" });
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true"); // Выводов логов в консоль
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    /*
     *  Диспетчер транзакций для транзакционноrо доступа к данным
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        txManager.setAutodetectDataSource(false);
        return txManager;
    }
}
