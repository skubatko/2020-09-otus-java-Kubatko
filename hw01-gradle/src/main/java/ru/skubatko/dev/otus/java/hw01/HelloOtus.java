package ru.skubatko.dev.otus.java.hw01;

import com.google.common.base.MoreObjects;

public class HelloOtus {
    private String greeting = "Welcome to";
    private String name = "OTUS";

    public void sayHelloGuava() {
        System.out.println(
                MoreObjects.toStringHelper(this)
                        .omitNullValues()
                        .add("greeting", greeting)
                        .add("name", name)
                        .toString());
    }
}
