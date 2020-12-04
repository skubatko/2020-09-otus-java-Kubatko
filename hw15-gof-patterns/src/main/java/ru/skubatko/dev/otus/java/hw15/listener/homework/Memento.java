package ru.skubatko.dev.otus.java.hw15.listener.homework;

import java.time.LocalDateTime;

class Memento {
    private final State state;
    private final LocalDateTime createdAt;

    Memento(State state, LocalDateTime createdAt) {
        this.state = new State(state);
        this.createdAt = createdAt;
    }

    State getState() {
        return state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
