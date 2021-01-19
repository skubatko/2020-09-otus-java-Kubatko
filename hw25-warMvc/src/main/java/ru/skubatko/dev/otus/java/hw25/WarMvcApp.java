package ru.skubatko.dev.otus.java.hw25;

import ru.skubatko.dev.otus.java.hw25.dao.UserDaoImpl;
import ru.skubatko.dev.otus.java.hw25.hibernate.sessionmanager.SessionManagerHibernate;
import ru.skubatko.dev.otus.java.hw25.service.UserService;
import ru.skubatko.dev.otus.java.hw25.service.UserServiceImpl;
import ru.skubatko.dev.otus.java.hw25.utils.DbHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class WarMvcApp {

    private static final Logger log = LoggerFactory.getLogger(WarMvcApp.class);

    public static void main(String[] args) {
        log.info("main() - start: args = {}", Arrays.toString(args));

        SessionManagerHibernate sessionManager = DbHelper.buildSessionManager();
        UserService userService = new UserServiceImpl(new UserDaoImpl(sessionManager));
        DbHelper.populateDb(userService);
        log.info("main() - info: db prepared");
    }
}
