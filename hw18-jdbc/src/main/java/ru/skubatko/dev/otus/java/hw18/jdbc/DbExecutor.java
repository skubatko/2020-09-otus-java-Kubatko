package ru.skubatko.dev.otus.java.hw18.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface DbExecutor<T> {

    String executeInsert(Connection connection, String sql, List<Object> params) throws SQLException;

    Optional<T> executeSelect(Connection connection, String sql, Object id, Function<ResultSet, T> rsHandler) throws SQLException;
}
