package ru.skubatko.dev.otus.java.hw21.dao;

import ru.skubatko.dev.otus.java.hw21.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.skubatko.dev.otus.java.hw21.hibernate.sessionmanager.SessionManagerHibernate;
import ru.skubatko.dev.otus.java.hw21.model.Unique;
import ru.skubatko.dev.otus.java.hw21.sessionmanager.SessionManager;

import org.hibernate.Session;

public abstract class AbstractDao<T extends Unique<K>, K> implements Dao<T, K> {

    protected final SessionManagerHibernate sessionManager;

    protected AbstractDao(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public K insert(T entity) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.persist(entity);
            hibernateSession.flush();
            return entity.getId();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(T entity) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.merge(entity);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public K insertOrUpdate(T entity) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            if (entity.getId() != null) {
                hibernateSession.merge(entity);
            } else {
                hibernateSession.persist(entity);
                hibernateSession.flush();
            }
            return entity.getId();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
