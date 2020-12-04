package ru.skubatko.dev.otus.java.hw15.processor.homework;

import ru.skubatko.dev.otus.java.hw15.Message;
import ru.skubatko.dev.otus.java.hw15.processor.Processor;

public class ExchangeFields11And12Processor implements Processor {

    //todo: 2. Сделать процессор, который поменяет местами значения field11 и field12

    @Override
    public Message process(Message message) {
        String field11 = message.getField11();
        return message.toBuilder().field11(message.getField12()).field12(field11).build();
    }
}
