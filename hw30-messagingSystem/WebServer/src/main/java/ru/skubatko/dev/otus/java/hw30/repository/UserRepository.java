package ru.skubatko.dev.otus.java.hw30.repository;

import ru.skubatko.dev.otus.java.hw30.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
