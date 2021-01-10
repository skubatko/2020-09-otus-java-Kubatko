package ru.skubatko.dev.otus.java.hw18.service;

public class DbServiceException extends RuntimeException {
    public DbServiceException(Exception e) {
        super(e);
    }
}
