package ru.skubatko.dev.otus.java.hw23.dao;

import ru.skubatko.dev.otus.java.hw23.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User, Long> {

    Optional<User> findByLogin(String login);

    List<User> findAll();
}
