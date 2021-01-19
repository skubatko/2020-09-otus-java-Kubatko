package ru.skubatko.dev.otus.java.hw25.config;

import ru.skubatko.dev.otus.java.hw25.domain.User;
import ru.skubatko.dev.otus.java.hw25.flyway.MigrationsExecutorFlyway;
import ru.skubatko.dev.otus.java.hw25.hibernate.HibernateUtils;
import ru.skubatko.dev.otus.java.hw25.hibernate.sessionmanager.SessionManagerHibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    @Bean
    public static SessionManagerHibernate sessionManagerHibernate() {
        var configuration = new org.hibernate.cfg.Configuration().configure(HIBERNATE_CFG_FILE);

        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, User.class);
        return new SessionManagerHibernate(sessionFactory);
    }
}
