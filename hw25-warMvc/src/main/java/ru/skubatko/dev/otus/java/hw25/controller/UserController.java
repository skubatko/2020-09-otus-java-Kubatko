package ru.skubatko.dev.otus.java.hw25.controller;

import ru.skubatko.dev.otus.java.hw25.domain.User;
import ru.skubatko.dev.otus.java.hw25.service.UserService;

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

    public UserController(UserService userService) {
        this.userService = userService;
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
