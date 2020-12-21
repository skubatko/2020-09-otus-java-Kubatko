package ru.skubatko.dev.otus.java.hw21.hibernate.sessionmanager;

import ru.skubatko.dev.otus.java.hw21.sessionmanager.DatabaseSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseSessionHibernate implements DatabaseSession {

    private final Session session;
    private final Transaction transaction;

    DatabaseSessionHibernate(Session session) {
        this.session = session;
        this.transaction = session.beginTransaction();
    }

    public Session getHibernateSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void close() {
        if (transaction.isActive()) {
            transaction.commit();
        }
        session.close();
    }
}
