package ru.skubatko.dev.otus.java.hw23;

import ru.skubatko.dev.otus.java.hw23.dao.UserDaoImpl;
import ru.skubatko.dev.otus.java.hw23.hibernate.sessionmanager.SessionManagerHibernate;
import ru.skubatko.dev.otus.java.hw23.processor.TemplateProcessor;
import ru.skubatko.dev.otus.java.hw23.processor.TemplateProcessorImpl;
import ru.skubatko.dev.otus.java.hw23.server.SecuredWebServer;
import ru.skubatko.dev.otus.java.hw23.server.WebServer;
import ru.skubatko.dev.otus.java.hw23.service.auth.UserAuthService;
import ru.skubatko.dev.otus.java.hw23.service.auth.UserAuthServiceImpl;
import ru.skubatko.dev.otus.java.hw23.service.db.UserService;
import ru.skubatko.dev.otus.java.hw23.service.db.UserServiceImpl;
import ru.skubatko.dev.otus.java.hw23.utils.DbHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public final class WebServerApp {

    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    private static final Logger log = LoggerFactory.getLogger(WebServerApp.class);

    public static void main(String[] args) throws Exception {
        log.info("main() - start: args = {}", Arrays.toString(args));

        SessionManagerHibernate sessionManager = DbHelper.buildSessionManager();
        UserService userService = new UserServiceImpl(new UserDaoImpl(sessionManager));
        DbHelper.populateDb(userService);
        log.info("main() - info: db prepared");

        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(userService);
        WebServer usersWebServer =
                new SecuredWebServer(WEB_SERVER_PORT, authService, userService, templateProcessor);
        log.info("main() - info: webserver prepared");

        usersWebServer.start();
        log.info("main() - info: webserver started");

        usersWebServer.join();

        log.info("main() - end");
    }
}
