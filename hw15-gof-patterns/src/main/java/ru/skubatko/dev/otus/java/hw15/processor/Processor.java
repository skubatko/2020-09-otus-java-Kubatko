package ru.skubatko.dev.otus.java.hw15.processor;

import ru.skubatko.dev.otus.java.hw15.Message;

public interface Processor {

    Message process(Message message);

    //todo: 2. Сделать процессор, который поменяет местами значения field11 и field12
}
