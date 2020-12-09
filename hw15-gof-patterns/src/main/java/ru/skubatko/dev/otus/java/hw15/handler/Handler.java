package ru.skubatko.dev.otus.java.hw15.handler;

import ru.skubatko.dev.otus.java.hw15.Message;
import ru.skubatko.dev.otus.java.hw15.listener.Listener;

public interface Handler {
    Message handle(Message msg);

    void addListener(Listener listener);
    void removeListener(Listener listener);
}
