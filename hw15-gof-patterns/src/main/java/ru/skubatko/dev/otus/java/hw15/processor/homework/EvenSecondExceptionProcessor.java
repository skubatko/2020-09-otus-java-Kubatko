package ru.skubatko.dev.otus.java.hw15.processor.homework;

import ru.skubatko.dev.otus.java.hw15.Message;
import ru.skubatko.dev.otus.java.hw15.ObjectForMessage;
import ru.skubatko.dev.otus.java.hw15.processor.Processor;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public class EvenSecondExceptionProcessor implements Processor {

    //todo: 3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)

    public EvenSecondExceptionProcessor() {
    }

    @Override
    public Message process(Message message) {
        List<String> data = Optional.ofNullable(message.getField13())
                                    .map(ObjectForMessage::getData)
                                    .orElseGet(Collections::emptyList);
        int seconds = data.isEmpty()
                      ? GregorianCalendar.getInstance().get(Calendar.SECOND)
                      : Integer.parseInt(data.get(0));

        System.out.printf("EvenSecondExceptionProcessor() - seconds = %d%n", seconds);
        if (seconds % 2 == 0) {
            throw new RuntimeException("EvenSecondException");
        }

        return message;
    }
}
