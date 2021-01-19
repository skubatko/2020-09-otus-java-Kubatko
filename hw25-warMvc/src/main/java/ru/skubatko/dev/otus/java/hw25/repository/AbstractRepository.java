package ru.skubatko.dev.otus.java.hw25.repository;

import ru.skubatko.dev.otus.java.hw25.domain.Unique;
import ru.skubatko.dev.otus.java.hw25.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.skubatko.dev.otus.java.hw25.hibernate.sessionmanager.SessionManagerHibernate;
import ru.skubatko.dev.otus.java.hw25.sessionmanager.SessionManager;

import org.hibernate.Session;

public abstract class AbstractRepository<T extends Unique<K>, K> implements Repository<T, K> {

    protected final SessionManagerHibernate sessionManager;

    protected AbstractRepository(SessionManagerHibernate sessionManager) {
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
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(T entity) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.merge(entity);
        } catch (Exception e) {
            throw new RepositoryException(e);
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
            throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(T entity) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.remove(entity);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
