package ru.skubatko.dev.otus.java.hw25.controller;

import ru.skubatko.dev.otus.java.hw25.domain.User;
import ru.skubatko.dev.otus.java.hw25.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable(name = "id") long id) {
        return userService.findById(id).orElse(null);
    }

    @GetMapping("/api/user")
    public User getUserByName(@RequestParam(name = "login") String login) {
        return userService.findByLogin(login).orElse(null);
    }

    @PostMapping("/api/user")
    public Long saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/user/random")
    public User findRandomUser() {
        return userService.findRandom();
    }
}
