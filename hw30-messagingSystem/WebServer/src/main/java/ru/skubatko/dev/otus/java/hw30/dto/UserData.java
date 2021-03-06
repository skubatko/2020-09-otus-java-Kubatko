package ru.skubatko.dev.otus.java.hw30.dto;

import ru.skubatko.dev.otus.java.hw30.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ru.otus.messagesystem.client.ResultDataType;

@Getter
@ToString
@AllArgsConstructor
public class UserData extends ResultDataType {

    private Long id;
    private String name;
    private String login;
    private String password;

    public UserData(Long id) {
        this.id = id;
    }

    public UserData(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public static UserData toUserData(User user) {
        return new UserData(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getPassword()
        );
    }
}
