package ru.skubatko.dev.otus.java.hw23.dao;

import ru.skubatko.dev.otus.java.hw23.model.Unique;
import ru.skubatko.dev.otus.java.hw23.sessionmanager.SessionManager;

import java.util.Optional;

public interface Dao<T extends Unique<K>, K> {

    Optional<T> findById(K id);

    K insert(T entity);

    void update(T entity);

    K insertOrUpdate(T entity);

    void delete(T entity);

    SessionManager getSessionManager();
}
