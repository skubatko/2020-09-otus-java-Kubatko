package ru.skubatko.dev.otus.java.hw20.jdbc.mapper;

/**
 * Сохраняет объект в базу, читает объект из базы
 *
 * @param <T>
 */
public interface JdbcMapper<T> {

    void insert(T objectData);

    void update(T objectData);

    void insertOrUpdate(T objectData);

    T findById(Object id, Class<T> clazz);
}
