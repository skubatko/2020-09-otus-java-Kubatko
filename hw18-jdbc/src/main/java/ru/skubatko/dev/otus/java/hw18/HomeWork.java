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
import ru.skubatko.dev.otus.java.hw18.model.Client;
import ru.skubatko.dev.otus.java.hw18.service.DbServiceClientImpl;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Optional;


public class HomeWork {

    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
// Общая часть
        var dataSource = new HomeWorkDataSource();
        flywayMigrations(dataSource);
        var sessionManager = new SessionManagerJdbc(dataSource);

// Работа с клиентами
        DbExecutorImpl<Client> dbExecutor = new DbExecutorImpl<>();
        EntitySQLMetaData entitySQLMetaData = new EntitySQLMetaDataImpl(Client.class);
        EntityClassMetaData<Client> entityClassMetaData = new EntityClassMetaDataImpl<>(Client.class);
        JdbcMapper<Client> clientDao = new JdbcMapperImpl<>(sessionManager, dbExecutor, entitySQLMetaData, entityClassMetaData);

// Код дальше должен остаться, т.е. clientDao должен использоваться
        var dbServiceClient = new DbServiceClientImpl(clientDao, sessionManager);
        var id = dbServiceClient.saveClient(new Client(0, "dbServiceClient", 17));
        Optional<Client> clientOptional = dbServiceClient.getClient(id);

        clientOptional.ifPresentOrElse(
                client -> logger.info("created client, name:{}", client.getName()),
                () -> logger.info("client was not created")
        );
// Работа со счетом


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
