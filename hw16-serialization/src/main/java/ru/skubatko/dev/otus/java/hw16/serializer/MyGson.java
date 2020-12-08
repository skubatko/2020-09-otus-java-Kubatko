package ru.skubatko.dev.otus.java.hw16.serializer;

public final class MyGson {

    private static final MyParserChain myParserChain = new MyParserChain();

    public String toJson(Object obj) {
        return myParserChain.handle(obj);
    }
}
