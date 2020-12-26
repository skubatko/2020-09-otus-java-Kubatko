package ru.skubatko.dev.otus.java.hw20.dao;

public class DaoException extends RuntimeException {
    public DaoException(Exception ex) {
        super(ex);
    }
}
