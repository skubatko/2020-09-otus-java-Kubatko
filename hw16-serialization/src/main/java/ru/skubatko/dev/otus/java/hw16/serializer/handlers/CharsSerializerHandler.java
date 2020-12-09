package ru.skubatko.dev.otus.java.hw16.serializer.handlers;

import ru.skubatko.dev.otus.java.hw16.serializer.AbstractSerializerHandler;

public final class CharsSerializerHandler extends AbstractSerializerHandler {

    @Override
    public String handle(Object obj) {
        if (obj instanceof String || obj instanceof Character) {
            return "\"" + obj + "\"";
        }

        return next.handle(obj);
    }
}
