package ru.skubatko.dev.otus.java.hw15;

import ru.skubatko.dev.otus.java.hw15.handler.ComplexProcessor;
import ru.skubatko.dev.otus.java.hw15.listener.homework.HistoryListener;
import ru.skubatko.dev.otus.java.hw15.processor.ProcessorConcatFields;
import ru.skubatko.dev.otus.java.hw15.processor.ProcessorUpperField10;
import ru.skubatko.dev.otus.java.hw15.processor.homework.EvenSecondExceptionProcessor;
import ru.skubatko.dev.otus.java.hw15.processor.homework.ExchangeFields11And12Processor;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
       4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элементов "to do" создать new ComplexProcessor и обработать сообщение
         */
        var historyListener = new HistoryListener();

        var processors1 = List.of(
                new ProcessorConcatFields(),
                new ProcessorUpperField10()
        );

        var complexProcessor1 = new ComplexProcessor(processors1, ex -> System.out.println(ex.getMessage()));
        complexProcessor1.addListener(historyListener);

        var message1 = new ru.skubatko.dev.otus.java.hw15.Message.Builder(1L)
                               .field1("field1")
                               .field2("field2")
                               .field3("field3")
                               .field6("field6")
                               .field10("field10")
                               .build();

        var result1 = complexProcessor1.handle(message1);
        System.out.println("result1:" + result1);

        var processors2 = List.of(
                new ExchangeFields11And12Processor(),
                new EvenSecondExceptionProcessor()
        );

        var complexProcessor2 = new ComplexProcessor(processors2, ex -> System.out.println(ex.getMessage()));
        complexProcessor2.addListener(historyListener);

        var message2 = new ru.skubatko.dev.otus.java.hw15.Message.Builder(2L)
                               .field1("field1")
                               .field2("field2")
                               .field3("field3")
                               .field6("field6")
                               .field10("field10")
                               .field11("field11")
                               .field12("field12")
                               .field14(() -> GregorianCalendar.getInstance().get(Calendar.SECOND))
                               .build();

        var result2 = complexProcessor2.handle(message2);
        System.out.println("result2:" + result2);

        System.out.println("history of messages:");
        historyListener.getHistory()
                .forEach(history -> System.out.printf("---%nold msg = [%s]%nnew msg = [%s]%n",
                        history.getOldMsg(), history.getNewMsg()));

        complexProcessor1.removeListener(historyListener);
        complexProcessor2.removeListener(historyListener);
    }
}
