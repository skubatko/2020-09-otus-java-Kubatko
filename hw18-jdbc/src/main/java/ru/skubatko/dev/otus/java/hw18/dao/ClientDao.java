package ru.skubatko.dev.otus.java.hw18.dao;


import ru.skubatko.dev.otus.java.hw18.model.Client;
import ru.skubatko.dev.otus.java.hw18.sessionmanager.SessionManager;

import java.util.Optional;

public interface ClientDao {
    Optional<Client> findById(long id);

    long insert(Client client);

    //void update(Client user);
    //void insertOrUpdate(Client user);

    SessionManager getSessionManager();
}
