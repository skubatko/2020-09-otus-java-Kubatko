package ru.skubatko.dev.otus.java.hw20.jdbc.sessionmanager;

import ru.skubatko.dev.otus.java.hw20.sessionmanager.DatabaseSession;

import java.sql.Connection;

public class DatabaseSessionJdbc implements DatabaseSession {
    private final Connection connection;

    DatabaseSessionJdbc(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
