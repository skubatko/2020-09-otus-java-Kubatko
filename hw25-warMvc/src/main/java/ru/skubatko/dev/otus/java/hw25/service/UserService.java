package ru.skubatko.dev.otus.java.hw25.service;

import ru.skubatko.dev.otus.java.hw25.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends DBService<User, Long> {

    Optional<User> findByLogin(String login);

    User findRandom();

    List<User> findAll();

    void delete(User user);
}
