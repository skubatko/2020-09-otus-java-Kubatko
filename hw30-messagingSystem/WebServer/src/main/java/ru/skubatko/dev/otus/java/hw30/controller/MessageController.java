package ru.skubatko.dev.otus.java.hw30.controller;

import ru.skubatko.dev.otus.java.hw30.domain.User;
import ru.skubatko.dev.otus.java.hw30.dto.UserMessage;
import ru.skubatko.dev.otus.java.hw30.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;

    @MessageMapping("/users")
    public void generateUserList() {
        log.info("generateUserList() - start");
        userService.findAll().forEach(this::sendUser);
        log.info("generateUserList() - end");
    }

    public void sendUser(User user) {
        log.info("sendUser() - info: user = {}", user);
        val userMessage = new UserMessage(user.getName(), user.getLogin(), user.getPassword());
        messagingTemplate.convertAndSend("/topic/response", userMessage);
    }

    @MessageMapping("/user")
    @SendTo("/topic/response")
    public UserMessage createUser(UserMessage userMessage) {
        log.info("createUser() - start: userMessage = {}", userMessage);
        val user = new User(userMessage.getName(), userMessage.getLogin(), userMessage.getPassword());
        userService.save(user);
        log.info("createUser() - end");
        return new UserMessage(user.getName(), user.getLogin(), user.getPassword());
    }
}
