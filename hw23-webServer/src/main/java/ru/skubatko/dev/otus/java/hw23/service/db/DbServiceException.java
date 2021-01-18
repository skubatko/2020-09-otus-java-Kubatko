package ru.skubatko.dev.otus.java.hw23.service.db;

public class DbServiceException extends RuntimeException {

    public DbServiceException(Exception e) {
        super(e);
    }
}
