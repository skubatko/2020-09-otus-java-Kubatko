package ru.skubatko.dev.otus.java.hw21.service;

import ru.skubatko.dev.otus.java.hw21.model.Unique;

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
