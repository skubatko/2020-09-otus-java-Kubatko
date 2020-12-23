package ru.skubatko.dev.otus.java.hw21.service;

public class DbServiceException extends RuntimeException {

    public DbServiceException(Exception e) {
        super(e);
    }
}
