package ru.skubatko.dev.otus.java.hw20.sessionmanager;

public interface SessionManager extends AutoCloseable {

    void beginSession();

    void commitSession();

    void rollbackSession();

    @Override
    void close();

    DatabaseSession getCurrentSession();
}
