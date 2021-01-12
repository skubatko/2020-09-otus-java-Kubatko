package ru.skubatko.dev.otus.java.hw23.service.db;

import ru.skubatko.dev.otus.java.hw23.model.Unique;

import java.util.Optional;

/**
 * Сервис работы с БД.
 *
 * @param <T> entity
 * @param <K> primary key type
 */
public interface DBService<T extends Unique<K>, K> {

    K save(T entity);

    Optional<T> getById(K id);
}
