package ru.skubatko.dev.otus.java.hw30.service;

import ru.skubatko.dev.otus.java.hw30.dto.UserData;
import ru.skubatko.dev.otus.java.hw30.dto.UserListData;

import ru.otus.messagesystem.client.MessageCallback;

public interface FrontendService {

    void findAll(MessageCallback<UserListData> callback);

    void save(UserData userData);

    void delete(String login);
}
