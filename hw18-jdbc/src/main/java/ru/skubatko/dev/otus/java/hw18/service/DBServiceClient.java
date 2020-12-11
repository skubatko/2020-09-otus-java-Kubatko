package ru.skubatko.dev.otus.java.hw18.service;

import java.util.Optional;

/**
 * Сервис работы с БД.
 *
 * @param <T> entity
 * @param <K> primary key type
 */
public interface DBServiceClient<T, K> {

    K saveEntity(T entity);

    Optional<T> getEntity(K id);
}
