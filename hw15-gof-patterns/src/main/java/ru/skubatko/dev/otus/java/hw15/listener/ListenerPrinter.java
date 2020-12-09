package ru.skubatko.dev.otus.java.hw15.listener;

import ru.skubatko.dev.otus.java.hw15.Message;

public class ListenerPrinter implements Listener {

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        var logString = String.format("oldMsg:%s, newMsg:%s", oldMsg, newMsg);
        System.out.println(logString);
    }
}
