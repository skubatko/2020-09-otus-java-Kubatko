package ru.skubatko.dev.otus.java.hw18.jdbc.sessionmanager;

import ru.skubatko.dev.otus.java.hw18.sessionmanager.DatabaseSession;

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
