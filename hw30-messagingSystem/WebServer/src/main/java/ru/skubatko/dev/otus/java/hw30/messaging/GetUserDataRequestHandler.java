package ru.skubatko.dev.otus.java.hw30.messaging;

import ru.skubatko.dev.otus.java.hw30.dto.UserData;
import ru.skubatko.dev.otus.java.hw30.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.message.MessageHelper;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetUserDataRequestHandler implements RequestHandler<UserData> {

    private final UserService service;

    @Override
    public Optional<Message> handle(Message msg) {
        UserData userData = MessageHelper.getPayload(msg);
        UserData data = new UserData(userData.getUserId(), service.findById(userData.getUserId()).toString());
        return Optional.of(MessageBuilder.buildReplyMessage(msg, data));
    }
}
