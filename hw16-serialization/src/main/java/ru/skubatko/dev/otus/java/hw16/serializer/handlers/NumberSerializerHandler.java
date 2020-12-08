package ru.skubatko.dev.otus.java.hw16.serializer.handlers;

import ru.skubatko.dev.otus.java.hw16.serializer.AbstractSerializerHandler;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class NumberSerializerHandler extends AbstractSerializerHandler {

    private static final Set<Class<?>> WRAPPER_TYPES;

    static {
        WRAPPER_TYPES = Stream.of(
                Byte.class,
                Short.class,
                Integer.class,
                Long.class,
                Float.class,
                Double.class
        ).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public String handle(Object obj) {
        if (WRAPPER_TYPES.contains(obj.getClass())) {
            return String.valueOf(obj);
        }

        return next.handle(obj);
    }
}
