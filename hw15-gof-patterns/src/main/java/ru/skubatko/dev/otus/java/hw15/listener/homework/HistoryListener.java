package ru.skubatko.dev.otus.java.hw15.listener.homework;

import ru.skubatko.dev.otus.java.hw15.Message;
import ru.skubatko.dev.otus.java.hw15.listener.Listener;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;

//todo: 4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
public class HistoryListener implements Listener {

    private final Deque<Memento> stack = new ArrayDeque<>();

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        MessageHistory messageHistory = new MessageHistory(oldMsg, newMsg);
        State state = new State(messageHistory);
        Memento memento = new Memento(state, LocalDateTime.now());
        stack.push(memento);
    }

    public State restoreState() {
        var memento = stack.pop();
        System.out.println("createdAt:" + memento.getCreatedAt());
        return memento.getState();
    }
}
