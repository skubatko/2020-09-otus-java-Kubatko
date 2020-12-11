package ru.skubatko.dev.otus.java.hw18;

import ru.skubatko.dev.otus.java.hw18.config.HomeWorkDataSource;
import ru.skubatko.dev.otus.java.hw18.jdbc.DbExecutorImpl;
import ru.skubatko.dev.otus.java.hw18.jdbc.mapper.EntityClassMetaData;
import ru.skubatko.dev.otus.java.hw18.jdbc.mapper.EntityClassMetaDataImpl;
import ru.skubatko.dev.otus.java.hw18.jdbc.mapper.EntitySQLMetaData;
import ru.skubatko.dev.otus.java.hw18.jdbc.mapper.EntitySQLMetaDataImpl;
import ru.skubatko.dev.otus.java.hw18.jdbc.mapper.JdbcMapper;
import ru.skubatko.dev.otus.java.hw18.jdbc.mapper.JdbcMapperImpl;
import ru.skubatko.dev.otus.java.hw18.jdbc.sessionmanager.SessionManagerJdbc;
import ru.skubatko.dev.otus.java.hw18.model.Account;
import ru.skubatko.dev.otus.java.hw18.model.Client;
import ru.skubatko.dev.otus.java.hw18.service.DbServiceAccountImpl;
import ru.skubatko.dev.otus.java.hw18.service.DbServiceClientImpl;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.UUID;


public class HomeWork {

    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
// Общая часть
        var dataSource = new HomeWorkDataSource();
        flywayMigrations(dataSource);
        var sessionManager = new SessionManagerJdbc(dataSource);

// Работа с клиентами
        DbExecutorImpl<Client> dbClientExecutor = new DbExecutorImpl<>();
        EntitySQLMetaData clientSQLMetaData = new EntitySQLMetaDataImpl(Client.class);
        EntityClassMetaData<Client> clientClassMetaData = new EntityClassMetaDataImpl<>(Client.class);
        JdbcMapper<Client> clientDao = new JdbcMapperImpl<>(sessionManager, dbClientExecutor, clientSQLMetaData, clientClassMetaData);

// Код дальше должен остаться, т.е. clientDao должен использоваться
        var dbServiceClient = new DbServiceClientImpl(clientDao, sessionManager);
        var clientId = dbServiceClient.saveEntity(new Client(0, "dbServiceClient", 17));
        Optional<Client> clientOptional = dbServiceClient.getEntity(clientId);

        clientOptional.ifPresentOrElse(
                client -> logger.info("created client, name:{}", client.getName()),
                () -> logger.info("client was not created")
        );

// Работа со счетом
        DbExecutorImpl<Account> dbAccountExecutor = new DbExecutorImpl<>();
        EntitySQLMetaData accountSQLMetaData = new EntitySQLMetaDataImpl(Account.class);
        EntityClassMetaData<Account> accountClassMetaData = new EntityClassMetaDataImpl<>(Account.class);
        JdbcMapper<Account> accountDao = new JdbcMapperImpl<>(sessionManager, dbAccountExecutor, accountSQLMetaData, accountClassMetaData);

        var dbServiceAccount = new DbServiceAccountImpl(accountDao, sessionManager);
        var accountId = dbServiceAccount.saveEntity(new Account(UUID.randomUUID().toString(), "dbServiceAccountType", 17.78));
        Optional<Account> accountOptional = dbServiceAccount.getEntity(accountId);

        accountOptional.ifPresentOrElse(
                account -> logger.info("created account, type:{}", account.getType()),
                () -> logger.info("account was not created")
        );
    }

    private static void flywayMigrations(DataSource dataSource) {
        logger.info("db migration started...");
        var flyway = Flyway.configure()
                             .dataSource(dataSource)
                             .locations("classpath:/db/migration")
                             .load();
        flyway.migrate();
        logger.info("db migration finished.");
        logger.info("***");
    }
}
