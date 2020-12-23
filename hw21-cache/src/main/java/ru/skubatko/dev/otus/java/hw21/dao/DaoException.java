package ru.skubatko.dev.otus.java.hw21.dao;

public class DaoException extends RuntimeException {

    public DaoException(Exception e) {
        super(e);
    }
}
