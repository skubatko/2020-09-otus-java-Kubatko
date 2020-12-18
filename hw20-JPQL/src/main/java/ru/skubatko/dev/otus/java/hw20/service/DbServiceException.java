package ru.skubatko.dev.otus.java.hw20.service;

public class DbServiceException extends RuntimeException {
    public DbServiceException(Exception e) {
        super(e);
    }
}
