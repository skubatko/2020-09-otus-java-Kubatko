package ru.skubatko.dev.otus.java.hw23.server;


import ru.skubatko.dev.otus.java.hw23.processor.TemplateProcessor;
import ru.skubatko.dev.otus.java.hw23.service.auth.UserAuthService;
import ru.skubatko.dev.otus.java.hw23.service.db.UserService;
import ru.skubatko.dev.otus.java.hw23.servlet.AuthorizationFilter;
import ru.skubatko.dev.otus.java.hw23.servlet.LoginServlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Arrays;

public class SecuredWebServer extends SimpleWebServer {

    private final UserAuthService authService;

    public SecuredWebServer(int port,
                            UserAuthService authService,
                            UserService userService,
                            TemplateProcessor templateProcessor) {
        super(port, userService, templateProcessor);
        this.authService = authService;
    }

    @Override
    protected Handler applySecurity(ServletContextHandler servletContextHandler, String... paths) {
        servletContextHandler.addServlet(new ServletHolder(new LoginServlet(templateProcessor, authService)), "/login");
        AuthorizationFilter authorizationFilter = new AuthorizationFilter();
        Arrays.stream(paths).forEachOrdered(path -> servletContextHandler.addFilter(new FilterHolder(authorizationFilter), path, null));
        return servletContextHandler;
    }
}
