package ru.skubatko.dev.otus.java.hw30.repository;

import ru.skubatko.dev.otus.java.hw30.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    void deleteByLogin(String login);
}
