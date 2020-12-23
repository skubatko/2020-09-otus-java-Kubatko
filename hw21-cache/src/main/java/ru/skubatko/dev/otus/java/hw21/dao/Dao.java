package ru.skubatko.dev.otus.java.hw21.dao;

import ru.skubatko.dev.otus.java.hw21.model.Unique;
import ru.skubatko.dev.otus.java.hw21.sessionmanager.SessionManager;

import java.util.Optional;

public interface Dao<T extends Unique<K>, K> {

    Optional<T> findById(K id);

    K insert(T entity);

    void update(T entity);

    K insertOrUpdate(T entity);

    SessionManager getSessionManager();
}
