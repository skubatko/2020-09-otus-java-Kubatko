package ru.skubatko.dev.otus.java.hw30.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ru.otus.messagesystem.client.ResultDataType;

@Getter
@ToString
@AllArgsConstructor
public class UserData extends ResultDataType {

    private final long userId;
    private final String data;

    public UserData(long userId) {
        this.userId = userId;
        this.data = null;
    }
}
