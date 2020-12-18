package ru.skubatko.dev.otus.java.hw20.jdbc.mapper;

/**
 * Создает SQL - запросы
 */
public interface EntitySQLMetaData {

    String getSelectAllSql();

    String getSelectByIdSql();

    String getInsertSql();

    String getInsertAutoincrementSql();

    String getUpdateSql();
}
