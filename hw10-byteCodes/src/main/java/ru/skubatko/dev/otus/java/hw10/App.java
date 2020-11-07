package ru.skubatko.dev.otus.java.hw10;

public class App {

    public static void main(String[] args) {
        TestLogging testLoggingClass = Ioc.createTestLoggingClass();
        testLoggingClass.calculation(1);
        testLoggingClass.calculation(1, 2);
        testLoggingClass.calculation(1, 2, "3S");
        testLoggingClass.calculation(1, 2, "3S", 4L);
    }
}
