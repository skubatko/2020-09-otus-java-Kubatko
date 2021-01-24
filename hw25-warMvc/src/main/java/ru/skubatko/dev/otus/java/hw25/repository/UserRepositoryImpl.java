package ru.skubatko.dev.otus.java.hw25.repository;

import ru.skubatko.dev.otus.java.hw25.domain.User;
import ru.skubatko.dev.otus.java.hw25.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.skubatko.dev.otus.java.hw25.hibernate.sessionmanager.SessionManagerHibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User, Long> implements UserRepository {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public UserRepositoryImpl(SessionManagerHibernate sessionManager) {
        super(sessionManager);
    }

    @Override
    public Optional<User> findById(Long id) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().get(User.class, id));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Criteria userCriteria = currentSession.getHibernateSession().createCriteria(User.class);
            userCriteria.add(Restrictions.eq("login", login));
            return Optional.ofNullable((User) userCriteria.uniqueResult());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return currentSession.getHibernateSession().createCriteria(User.class).list();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}
