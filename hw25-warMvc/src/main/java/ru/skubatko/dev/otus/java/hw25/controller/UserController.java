package ru.skubatko.dev.otus.java.hw25.controller;

import ru.skubatko.dev.otus.java.hw25.domain.User;
import ru.skubatko.dev.otus.java.hw25.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
        populateDb(userService);
    }

    private void populateDb(UserService userService) {
        log.info("populateDb() - start");

        if (userService.findAll().isEmpty()) {
            List<User> users = List.of(
                    new User(1L, "user1", "login1", "pass1"),
                    new User(2L, "user2", "login2", "pass2"),
                    new User(3L, "user3", "login3", "pass3"),
                    new User(4L, "user4", "login4", "pass4"),
                    new User(5L, "user5", "login5", "pass5")
            );
            users.forEach(userService::save);
        }

        log.info("populateDb() - end");
    }

    @GetMapping({"/", "/user/list"})
    public String usersListView(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "usersList.html";
    }

    @GetMapping("/user/create")
    public String userCreateView(Model model) {
        model.addAttribute("user", new User());
        return "userCreate.html";
    }

    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        userService.save(user);
        return new RedirectView("/", true);
    }
}
