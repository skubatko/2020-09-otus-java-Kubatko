package ru.skubatko.dev.otus.java.hw23.servlet;

import ru.skubatko.dev.otus.java.hw23.model.User;
import ru.skubatko.dev.otus.java.hw23.processor.TemplateProcessor;
import ru.skubatko.dev.otus.java.hw23.service.db.UserService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UsersServlet extends HttpServlet {

    private final UserService userService;
    private final TemplateProcessor templateProcessor;

    private static final String USERS_PAGE_TEMPLATE = "users.html";
    private static final String TEMPLATE_ATTR_RANDOM_USER = "randomUser";

    public UsersServlet(TemplateProcessor templateProcessor, UserService userService) {
        this.templateProcessor = templateProcessor;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        List<User> users = userService.findAll();
        User randomUser = users.get(new Random(System.currentTimeMillis()).nextInt(users.size()));
        paramsMap.put(TEMPLATE_ATTR_RANDOM_USER, randomUser);

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }
}
