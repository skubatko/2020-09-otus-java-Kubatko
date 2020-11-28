package ru.skubatko.dev.otus.java.hw15.listener.homework;

import ru.skubatko.dev.otus.java.hw15.Message;
import ru.skubatko.dev.otus.java.hw15.listener.Listener;

import java.util.ArrayDeque;
import java.util.Queue;

//todo: 4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
public class HistoryListener implements Listener {

    private final Queue<HistoryMessages> history = new ArrayDeque<>();

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        history.add(new HistoryMessages(oldMsg, newMsg));
    }

    public Queue<HistoryMessages> getHistory() {
        return history;
    }

    public static class HistoryMessages {
        private final Message oldMsg;
        private final Message newMsg;

        public HistoryMessages(Message oldMsg, Message newMsg) {
            this.oldMsg = Message.copyOf(oldMsg);
            this.newMsg = Message.copyOf(newMsg);
        }

        public Message getOldMsg() {
            return oldMsg;
        }

        public Message getNewMsg() {
            return newMsg;
        }
    }
}
