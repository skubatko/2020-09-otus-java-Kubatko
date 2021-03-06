package ru.skubatko.dev.otus.java.hw20;

import ru.skubatko.dev.otus.java.hw20.dao.AccountDao;
import ru.skubatko.dev.otus.java.hw20.dao.ClientDao;
import ru.skubatko.dev.otus.java.hw20.dao.Dao;
import ru.skubatko.dev.otus.java.hw20.flyway.MigrationsExecutorFlyway;
import ru.skubatko.dev.otus.java.hw20.hibernate.HibernateUtils;
import ru.skubatko.dev.otus.java.hw20.hibernate.sessionmanager.SessionManagerHibernate;
import ru.skubatko.dev.otus.java.hw20.model.Account;
import ru.skubatko.dev.otus.java.hw20.model.AddressDataSet;
import ru.skubatko.dev.otus.java.hw20.model.Client;
import ru.skubatko.dev.otus.java.hw20.model.PhoneDataSet;
import ru.skubatko.dev.otus.java.hw20.service.DBService;
import ru.skubatko.dev.otus.java.hw20.service.DbServiceClientImpl;
import ru.skubatko.dev.otus.java.hw20.service.DbServiceImpl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HomeWork {

    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    private static final Logger log = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
// Общая часть
        Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        String dbUrl = configuration.getProperty("hibernate.connection.url");
        String dbUserName = configuration.getProperty("hibernate.connection.username");
        String dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(configuration,
                Client.class, Account.class, AddressDataSet.class, PhoneDataSet.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);

// Работа с клиентами
        Dao<Client, Long> clientDao = new ClientDao(sessionManager);
        DBService<Client, Long> dbServiceClient = new DbServiceClientImpl(clientDao);

        var aClient = new Client("dbServiceClient", 17);
        aClient.setAddress(new AddressDataSet("dbServiceClientAddress", aClient));
        var p1 = new PhoneDataSet();
        p1.setNumber("1111");
        var p2 = new PhoneDataSet();
        p2.setNumber("222");
        aClient.setPhones(List.of(p1, p2));

        var clientId = dbServiceClient.save(aClient);

        Optional<Client> clientOptional = dbServiceClient.getById(clientId);
        clientOptional.ifPresentOrElse(
                client -> log.info("created client, name:{}", client.getName()),
                () -> log.info("client was not created")
        );

        clientOptional.map(Client::getAddress).ifPresentOrElse(
                address -> log.info("created client address:{}", address),
                () -> log.info("address was not created")
        );

        clientOptional.map(Client::getPhones).ifPresent(phones -> log.info("created client phones:{}", phones));

        Client persisted = clientOptional.orElseThrow();
        persisted.setAge(25);
        dbServiceClient.save(persisted);

        clientOptional = dbServiceClient.getById(clientId);
        clientOptional.ifPresentOrElse(
                client -> log.info("updated client: name:{}, age:{}", client.getName(), client.getAge()),
                () -> log.info("client was not updated")
        );

// Работа со счетом
        Dao<Account, String> accountDao = new AccountDao(sessionManager);
        var dbServiceAccount = new DbServiceImpl<>(accountDao);

        String accountId = UUID.randomUUID().toString();

        dbServiceAccount.save(new Account(accountId, "dbServiceAccountType", 17.78));

        Optional<Account> accountOptional = dbServiceAccount.getById(accountId);
        accountOptional.ifPresentOrElse(
                account -> log.info("created account, type:{}", account.getType()),
                () -> log.info("account was not created")
        );

        dbServiceAccount.save(new Account(accountId, "dbServiceAccountTypeUpdated", 12.39));

        accountOptional = dbServiceAccount.getById(accountId);
        accountOptional.ifPresentOrElse(
                account -> log.info("updated account, type:{}", account.getType()),
                () -> log.info("account was not updated")
        );
    }
}
