package ru.skubatko.dev.otus.java.hw15;

import ru.skubatko.dev.otus.java.hw15.handler.ComplexProcessor;
import ru.skubatko.dev.otus.java.hw15.listener.ListenerPrinter;
import ru.skubatko.dev.otus.java.hw15.processor.LoggerProcessor;
import ru.skubatko.dev.otus.java.hw15.processor.ProcessorConcatFields;
import ru.skubatko.dev.otus.java.hw15.processor.ProcessorUpperField10;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        var processors = List.of(new ProcessorConcatFields(),
                new LoggerProcessor(new ProcessorUpperField10()));

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {});
        var listenerPrinter = new ListenerPrinter();
        complexProcessor.addListener(listenerPrinter);

        var message = new ru.skubatko.dev.otus.java.hw15.Message.Builder(1L)
                              .field1("field1")
                              .field2("field2")
                              .field3("field3")
                              .field6("field6")
                              .field10("field10")
                              .build();

        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);

        complexProcessor.removeListener(listenerPrinter);
    }
}
