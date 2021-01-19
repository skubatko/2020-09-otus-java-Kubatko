package ru.skubatko.dev.otus.java.hw25.service;

public class DbServiceException extends RuntimeException {

    public DbServiceException(Exception e) {
        super(e);
    }
}
