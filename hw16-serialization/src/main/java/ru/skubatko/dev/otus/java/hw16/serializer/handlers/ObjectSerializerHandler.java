package ru.skubatko.dev.otus.java.hw16.serializer.handlers;

import static java.lang.reflect.Modifier.isStatic;

import ru.skubatko.dev.otus.java.hw16.serializer.AbstractSerializerHandler;
import ru.skubatko.dev.otus.java.hw16.serializer.MyParserChain;
import ru.skubatko.dev.otus.java.hw16.serializer.SerializerHandler;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ObjectSerializerHandler extends AbstractSerializerHandler {

    private final MyParserChain myParserChain;

    public ObjectSerializerHandler(MyParserChain myParserChain) {this.myParserChain = myParserChain;}

    @Override
    public void setNext(SerializerHandler next) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String handle(Object obj) {
        List<Field> fields = Arrays.stream(obj.getClass().getDeclaredFields()).filter(field -> !(isStatic(field.getModifiers()))).collect(Collectors.toList());
        if (fields.isEmpty()) {
            return obj.toString();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                sb.append("\"").append(field.getName()).append("\"")
                        .append(":")
                        .append(myParserChain.handle(field.get(obj)))
                        .append(",");
            } catch (IllegalAccessException e) {
                // empty
            }
        }
        sb.replace(sb.length() - 1, sb.length(), "}");

        System.out.println("json = " + sb.toString());

        return sb.toString();
    }
}
