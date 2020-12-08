package ru.skubatko.dev.otus.java.hw16.serializer.handlers;

import ru.skubatko.dev.otus.java.hw16.serializer.AbstractSerializerHandler;

public final class NullSerializerHandler extends AbstractSerializerHandler {

    @Override
    public String handle(Object obj) {
        if (obj == null) {
            return "null";
        }

        return next.handle(obj);
    }
}
