package ru.skubatko.dev.otus.java.hw16.serializer.handlers;

import ru.skubatko.dev.otus.java.hw16.serializer.AbstractSerializerHandler;

import java.util.List;

public final class ListSerializerHandler extends AbstractSerializerHandler {

    @Override
    public String handle(Object obj) {
        if (obj instanceof List) {
            return obj.toString().replaceAll(" ", "");
        }

        return next.handle(obj);
    }
}
