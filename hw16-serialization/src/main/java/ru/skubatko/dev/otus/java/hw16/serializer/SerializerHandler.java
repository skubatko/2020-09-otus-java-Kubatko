package ru.skubatko.dev.otus.java.hw16.serializer;

public interface SerializerHandler {

    void setNext(SerializerHandler next);

    String handle(Object obj);
}
