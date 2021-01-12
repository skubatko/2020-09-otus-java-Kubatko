package ru.skubatko.dev.otus.java.hw23.service.db;

import ru.skubatko.dev.otus.java.hw23.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends DBService<User, Long> {

    Optional<User> findByLogin(String login);

    List<User> findAll();

    void delete(User user);
}
