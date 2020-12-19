package ru.skubatko.dev.otus.java.hw20.dao;

import ru.skubatko.dev.otus.java.hw20.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.skubatko.dev.otus.java.hw20.hibernate.sessionmanager.SessionManagerHibernate;
import ru.skubatko.dev.otus.java.hw20.model.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ClientDao extends AbstractDao<Client, Long> {

    private static final Logger log = LoggerFactory.getLogger(ClientDao.class);

    public ClientDao(SessionManagerHibernate sessionManager) {
        super(sessionManager);
    }

    @Override
    public Optional<Client> findById(Long id) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(Client.class, id));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }
}
