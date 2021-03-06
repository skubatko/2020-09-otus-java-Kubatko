package ru.skubatko.dev.otus.java.hw15.listener.homework;

import ru.skubatko.dev.otus.java.hw15.Message;

public class MessageHistory {
    private final Message oldMsg;
    private final Message newMsg;

    public MessageHistory(Message oldMsg, Message newMsg) {
        this.oldMsg = oldMsg;
        this.newMsg = newMsg;
    }

    public MessageHistory(MessageHistory messageHistory) {
        this.oldMsg = Messages.copyOf(messageHistory.getOldMsg());
        this.newMsg = Messages.copyOf(messageHistory.getNewMsg());
    }

    public Message getOldMsg() {
        return oldMsg;
    }

    public Message getNewMsg() {
        return newMsg;
    }

    @Override
    public String toString() {
        return "MessageHistory{\n" +
                       "oldMsg=" + oldMsg +
                       "\nnewMsg=" + newMsg +
                       "\n}";
    }
}
