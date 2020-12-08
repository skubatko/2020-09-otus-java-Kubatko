package ru.skubatko.dev.otus.java.hw16.serializer;

import static java.lang.reflect.Modifier.isStatic;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyGson {

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

    public String toJson(Object obj) {
        if (obj == null) {
            return "null";
        }

        Class<?> clazz = obj.getClass();
        if (WRAPPER_TYPES.contains(clazz)) {
            return String.valueOf(obj);
        }

        if (clazz.isArray()) {
            return Arrays.toString((int[]) obj).replaceAll(" ", "");
        }

        if (obj instanceof List) {
            return obj.toString().replaceAll(" ", "");
        }

        if (obj instanceof String || obj instanceof Character) {
            return "\"" + obj + "\"";
        }

        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).filter(field -> !(isStatic(field.getModifiers()))).collect(Collectors.toList());
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
                        .append(toJson(field.get(obj)))
                        .append(",");
            } catch (IllegalAccessException e) {
                // empty
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");

        System.out.println("json = " + sb.toString());

        return sb.toString();
    }
}
