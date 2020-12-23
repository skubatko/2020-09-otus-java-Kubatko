package ru.skubatko.dev.otus.java.hw21.service;

import ru.skubatko.dev.otus.java.hw21.cachehw.HwCache;
import ru.skubatko.dev.otus.java.hw21.dao.Dao;
import ru.skubatko.dev.otus.java.hw21.model.Unique;
import ru.skubatko.dev.otus.java.hw21.sessionmanager.SessionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DbServiceImpl<T extends Unique<K>, K> implements DBService<T, K> {

    private final Dao<T, K> dao;
    private final HwCache<K, T> cache;

    private static final Logger log = LoggerFactory.getLogger(DbServiceImpl.class);

    public DbServiceImpl(Dao<T, K> dao, HwCache<K, T> cache) {
        this.dao = dao;
        this.cache = cache;
    }

    @Override
    public K save(T entity) {
        try (SessionManager sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                K id = dao.insertOrUpdate(entity);
                sessionManager.commitSession();

                cache.put(id, entity);
                log.info("cached entity: {}", id);

                log.info("created entity: {}", id);
                return id;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<T> getById(K id) {
        T cached = cache.get(id);
        if (cached != null) {
            log.info("cached entity: {}", cached);
            return Optional.of(cached);
        }

        try (SessionManager sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<T> optionalEntity = dao.findById(id);
                optionalEntity.ifPresent(entity -> cache.put(id, entity));

                log.info("entity: {}", optionalEntity.orElse(null));
                return optionalEntity;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }
}
