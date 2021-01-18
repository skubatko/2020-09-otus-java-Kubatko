package ru.skubatko.dev.otus.java.hw23.sessionmanager;

public class SessionManagerException extends RuntimeException {

    public SessionManagerException(String msg) {
        super(msg);
    }

    public SessionManagerException(Exception ex) {
        super(ex);
    }
}
