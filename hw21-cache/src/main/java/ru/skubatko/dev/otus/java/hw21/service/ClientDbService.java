package ru.skubatko.dev.otus.java.hw21.service;

import ru.skubatko.dev.otus.java.hw21.dao.Dao;
import ru.skubatko.dev.otus.java.hw21.model.Client;

public class ClientDbService extends AbstractDbService<Client, Long> {

    public ClientDbService(Dao<Client, Long> dao) {
        super(dao);
    }
}
