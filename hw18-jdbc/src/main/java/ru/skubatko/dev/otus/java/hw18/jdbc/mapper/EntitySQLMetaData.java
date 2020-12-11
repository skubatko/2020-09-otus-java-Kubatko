package ru.skubatko.dev.otus.java.hw18.jdbc.mapper;

/**
 * Создает SQL - запросы
 */
public interface EntitySQLMetaData {

    String getSelectAllSql();

    String getSelectByIdSql();

    String getInsertSql();

    String getUpdateSql();
}
