package ru.skubatko.dev.otus.java.hw25.repository;

import ru.skubatko.dev.otus.java.hw25.domain.Unique;
import ru.skubatko.dev.otus.java.hw25.sessionmanager.SessionManager;

import java.util.Optional;

public interface Repository<T extends Unique<K>, K> {

    Optional<T> findById(K id);

    K insert(T entity);

    void update(T entity);

    K insertOrUpdate(T entity);

    void delete(T entity);

    SessionManager getSessionManager();
}
