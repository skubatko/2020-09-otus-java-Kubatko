package ru.skubatko.dev.otus.java.hw25.repository;

public class RepositoryException extends RuntimeException {

    public RepositoryException(Exception ex) {
        super(ex);
    }
}
