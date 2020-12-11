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

    private static final Logger log = LoggerFactory.getLogger(HomeWork.class);

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

        Optional<Client> clientOptional = dbServiceClient.getEntityById(clientId);
        clientOptional.ifPresentOrElse(
                client -> log.info("created client, name:{}", client.getName()),
                () -> log.info("client was not created")
        );

        dbServiceClient.saveEntity(new Client(clientId, "dbServiceClientUpdated", 25));

        clientOptional = dbServiceClient.getEntityById(clientId);
        clientOptional.ifPresentOrElse(
                client -> log.info("updated client, name:{}", client.getName()),
                () -> log.info("client was not updated")
        );

// Работа со счетом
        DbExecutorImpl<Account> dbAccountExecutor = new DbExecutorImpl<>();
        EntitySQLMetaData accountSQLMetaData = new EntitySQLMetaDataImpl(Account.class);
        EntityClassMetaData<Account> accountClassMetaData = new EntityClassMetaDataImpl<>(Account.class);
        JdbcMapper<Account> accountDao = new JdbcMapperImpl<>(sessionManager, dbAccountExecutor, accountSQLMetaData, accountClassMetaData);

        var dbServiceAccount = new DbServiceAccountImpl(accountDao, sessionManager);

        String accountId = UUID.randomUUID().toString();

        dbServiceAccount.saveEntity(new Account(accountId, "dbServiceAccountType", 17.78));

        Optional<Account> accountOptional = dbServiceAccount.getEntityById(accountId);
        accountOptional.ifPresentOrElse(
                account -> log.info("created account, type:{}", account.getType()),
                () -> log.info("account was not created")
        );

        dbServiceAccount.saveEntity(new Account(accountId, "dbServiceAccountTypeUpdated", 12.78));

        accountOptional = dbServiceAccount.getEntityById(accountId);
        accountOptional.ifPresentOrElse(
                account -> log.info("updated account, type:{}", account.getType()),
                () -> log.info("account was not updated")
        );
    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                             .dataSource(dataSource)
                             .locations("classpath:/db/migration")
                             .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }
}
