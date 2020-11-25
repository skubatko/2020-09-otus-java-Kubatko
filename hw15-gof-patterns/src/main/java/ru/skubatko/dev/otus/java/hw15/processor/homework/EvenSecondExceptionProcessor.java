package ru.skubatko.dev.otus.java.hw15.processor.homework;

import ru.skubatko.dev.otus.java.hw15.Message;
import ru.skubatko.dev.otus.java.hw15.processor.Processor;

public class EvenSecondExceptionProcessor implements Processor {

    //todo: 3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)

    public EvenSecondExceptionProcessor() {
    }

    @Override
    public Message process(Message message) {
        Integer seconds = message.getField14().get();
        System.out.printf("EvenSecondExceptionProcessor() - seconds = %d%n", seconds);
        if (seconds % 2 == 0) {
            throw new RuntimeException("EvenSecondException");
        }
        return message;
    }
}
