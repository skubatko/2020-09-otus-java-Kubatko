package ru.skubatko.dev.otus.java.hw23.dao;

public class DaoException extends RuntimeException {

    public DaoException(Exception ex) {
        super(ex);
    }
}
