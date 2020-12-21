package ru.skubatko.dev.otus.java.hw21.service;

import ru.skubatko.dev.otus.java.hw21.dao.Dao;
import ru.skubatko.dev.otus.java.hw21.model.Account;

public class AccountDbService extends AbstractDbService<Account, String> {

    public AccountDbService(Dao<Account, String> dao) {
        super(dao);
    }
}
