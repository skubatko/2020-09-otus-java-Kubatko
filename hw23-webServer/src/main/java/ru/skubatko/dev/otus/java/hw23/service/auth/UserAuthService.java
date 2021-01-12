package ru.skubatko.dev.otus.java.hw23.service.auth;

public interface UserAuthService {

    boolean authenticate(String login, String password);
}
