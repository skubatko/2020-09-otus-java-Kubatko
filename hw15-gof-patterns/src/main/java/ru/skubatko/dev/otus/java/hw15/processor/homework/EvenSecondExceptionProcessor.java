package ru.skubatko.dev.otus.java.hw15.processor.homework;

import ru.skubatko.dev.otus.java.hw15.Message;
import ru.skubatko.dev.otus.java.hw15.processor.Processor;

// todo: 3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
public class EvenSecondExceptionProcessor implements Processor {

    private final DateTimeProvider dateTimeProvider;

    public EvenSecondExceptionProcessor(DateTimeProvider dateTimeProvider) { this.dateTimeProvider = dateTimeProvider;}

    @Override
    public Message process(Message message) {

        int second = dateTimeProvider.getDate().getSecond();
        System.out.printf("EvenSecondExceptionProcessor() - second = %d%n", second);
        if (second % 2 == 0) {
            throw new RuntimeException("EvenSecondException");
        }

        return message;
    }
}
