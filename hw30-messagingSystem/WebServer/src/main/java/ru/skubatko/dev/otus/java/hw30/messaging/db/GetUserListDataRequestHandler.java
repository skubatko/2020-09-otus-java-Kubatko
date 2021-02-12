package ru.skubatko.dev.otus.java.hw30.messaging.db;

import static ru.skubatko.dev.otus.java.hw30.dto.UserListData.toUserListData;

import ru.skubatko.dev.otus.java.hw30.dto.UserListData;
import ru.skubatko.dev.otus.java.hw30.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.message.MessageType;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetUserListDataRequestHandler implements RequestHandler<UserListData> {

    private final UserService service;

    @Override
    public Optional<Message> handle(Message msg) {
        UserListData data = toUserListData(service.findAll());
        return Optional.of(MessageBuilder.buildReplyMessage(msg, data, MessageType.USER_LIST_DATA));
    }
}
