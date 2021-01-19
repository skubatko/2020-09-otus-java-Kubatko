package ru.skubatko.dev.otus.java.hw25.dao;

public class DaoException extends RuntimeException {

    public DaoException(Exception ex) {
        super(ex);
    }
}
