package ru.skubatko.dev.otus.java.hw20.service;

import ru.skubatko.dev.otus.java.hw20.dao.Dao;
import ru.skubatko.dev.otus.java.hw20.model.Client;

public class ClientDbService extends AbstractDbService<Client, Long> {

    public ClientDbService(Dao<Client, Long> dao) {
        super(dao);
    }
}
