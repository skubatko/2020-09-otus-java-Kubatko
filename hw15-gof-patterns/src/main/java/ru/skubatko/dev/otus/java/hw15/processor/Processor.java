package ru.skubatko.dev.otus.java.hw15.processor;

import ru.skubatko.dev.otus.java.hw15.Message;

public interface Processor {

    Message process(Message message);
}
