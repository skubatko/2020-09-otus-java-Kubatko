package ru.skubatko.dev.otus.java.hw25.repository;

import ru.skubatko.dev.otus.java.hw25.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    Optional<User> findByLogin(String login);

    List<User> findAll();
}
