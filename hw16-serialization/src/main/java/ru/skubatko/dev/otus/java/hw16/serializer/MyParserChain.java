package ru.skubatko.dev.otus.java.hw16.serializer;

import ru.skubatko.dev.otus.java.hw16.serializer.handlers.ArraySerializerHandler;
import ru.skubatko.dev.otus.java.hw16.serializer.handlers.CharsSerializerHandler;
import ru.skubatko.dev.otus.java.hw16.serializer.handlers.ListSerializerHandler;
import ru.skubatko.dev.otus.java.hw16.serializer.handlers.NullSerializerHandler;
import ru.skubatko.dev.otus.java.hw16.serializer.handlers.NumberSerializerHandler;
import ru.skubatko.dev.otus.java.hw16.serializer.handlers.ObjectSerializerHandler;

public final class MyParserChain {

    private final SerializerHandler head;

    public MyParserChain() {
        SerializerHandler nullSerializerHandler = new NullSerializerHandler();
        SerializerHandler numberSerializerHandler = new NumberSerializerHandler();
        SerializerHandler charsSerializerHandler = new CharsSerializerHandler();
        SerializerHandler arraySerializerHandler = new ArraySerializerHandler();
        SerializerHandler listSerializerHandler = new ListSerializerHandler();
        SerializerHandler objectSerializerHandler = new ObjectSerializerHandler(this);

        head = nullSerializerHandler;
        head.setNext(numberSerializerHandler);
        numberSerializerHandler.setNext(charsSerializerHandler);
        charsSerializerHandler.setNext(arraySerializerHandler);
        arraySerializerHandler.setNext(listSerializerHandler);
        listSerializerHandler.setNext(objectSerializerHandler);
    }

    public String handle(Object obj) {
        return head.handle(obj);
    }
}
