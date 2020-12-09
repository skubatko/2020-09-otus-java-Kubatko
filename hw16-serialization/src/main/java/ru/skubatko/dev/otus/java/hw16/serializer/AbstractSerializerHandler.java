package ru.skubatko.dev.otus.java.hw16.serializer;

public abstract class AbstractSerializerHandler implements SerializerHandler{

    protected SerializerHandler next;

    @Override
    public void setNext(SerializerHandler next) {
        this.next = next;
    }
}
