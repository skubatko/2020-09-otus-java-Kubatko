package ru.skubatko.dev.otus.java.hw23;

import ru.skubatko.dev.otus.java.hw23.dao.UserDaoImpl;
import ru.skubatko.dev.otus.java.hw23.flyway.MigrationsExecutorFlyway;
import ru.skubatko.dev.otus.java.hw23.hibernate.HibernateUtils;
import ru.skubatko.dev.otus.java.hw23.hibernate.sessionmanager.SessionManagerHibernate;
import ru.skubatko.dev.otus.java.hw23.model.User;
import ru.skubatko.dev.otus.java.hw23.processor.TemplateProcessor;
import ru.skubatko.dev.otus.java.hw23.processor.TemplateProcessorImpl;
import ru.skubatko.dev.otus.java.hw23.server.SecuredWebServer;
import ru.skubatko.dev.otus.java.hw23.server.WebServer;
import ru.skubatko.dev.otus.java.hw23.service.auth.UserAuthService;
import ru.skubatko.dev.otus.java.hw23.service.auth.UserAuthServiceImpl;
import ru.skubatko.dev.otus.java.hw23.service.db.UserService;
import ru.skubatko.dev.otus.java.hw23.service.db.UserServiceImpl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebServerApp {

    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    private static final Logger log = LoggerFactory.getLogger(WebServerApp.class);

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        String dbUrl = configuration.getProperty("hibernate.connection.url");
        String dbUserName = configuration.getProperty("hibernate.connection.username");
        String dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(configuration, User.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);

        UserService userService = new UserServiceImpl(new UserDaoImpl(sessionManager));
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(userService);

        WebServer usersWebServer = new SecuredWebServer(WEB_SERVER_PORT,
                authService, userService, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
