package ru.skubatko.dev.otus.java.hw15.listener;

import ru.skubatko.dev.otus.java.hw15.Message;

public interface Listener {

    void onUpdated(Message oldMsg, Message newMsg);

    //todo: 4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
}
