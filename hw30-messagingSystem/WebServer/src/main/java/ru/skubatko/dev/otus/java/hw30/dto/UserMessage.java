package ru.skubatko.dev.otus.java.hw30.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessage {

    private String name;
    private String login;
    private String password;
}
