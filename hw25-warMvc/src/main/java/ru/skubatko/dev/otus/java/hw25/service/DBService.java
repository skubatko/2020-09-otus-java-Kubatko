package ru.skubatko.dev.otus.java.hw25.service;

import ru.skubatko.dev.otus.java.hw25.domain.Unique;

import java.util.Optional;

/**
 * Сервис работы с БД.
 *
 * @param <T> entity
 * @param <K> primary key type
 */
public interface DBService<T extends Unique<K>, K> {

    K save(T entity);

    Optional<T> findById(K id);
}
