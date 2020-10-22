package ru.skubatko.dev.otus.java.hw06.exceptions;

public class TestExecutionFailedException extends RuntimeException {

    public TestExecutionFailedException(String message) {
        super(message);
    }
}
