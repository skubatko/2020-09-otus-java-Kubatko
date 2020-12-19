package ru.skubatko.dev.otus.java.hw20.service;

import ru.skubatko.dev.otus.java.hw20.dao.Dao;
import ru.skubatko.dev.otus.java.hw20.model.Account;

public class AccountDbService extends AbstractDbService<Account, String> {

    public AccountDbService(Dao<Account, String> dao) {
        super(dao);
    }
}
