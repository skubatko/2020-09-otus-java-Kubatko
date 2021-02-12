package ru.skubatko.dev.otus.java.hw30.messaging.db;

import ru.skubatko.dev.otus.java.hw30.domain.User;
import ru.skubatko.dev.otus.java.hw30.dto.UserData;
import ru.skubatko.dev.otus.java.hw30.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageHelper;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SaveUserDataRequestHandler implements RequestHandler<UserData> {

    private final UserService service;

    @Override
    public Optional<Message> handle(Message msg) {
        UserData data = MessageHelper.getPayload(msg);
        service.save(new User(data.getName(), data.getLogin(), data.getPassword()));
        return Optional.empty();
    }
}
