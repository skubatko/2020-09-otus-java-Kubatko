package ru.skubatko.dev.otus.java.hw30.service;

import ru.skubatko.dev.otus.java.hw30.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    void deleteByLogin(String login);
}
