package ru.skubatko.dev.otus.java.hw18.service;

import ru.skubatko.dev.otus.java.hw18.jdbc.mapper.JdbcMapper;
import ru.skubatko.dev.otus.java.hw18.model.Client;
import ru.skubatko.dev.otus.java.hw18.sessionmanager.SessionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DbServiceClientImpl implements DBServiceClient {

    private final JdbcMapper<Client> jdbcMapper;
    private final SessionManager sessionManager;

    private static final Logger logger = LoggerFactory.getLogger(DbServiceClientImpl.class);

    public DbServiceClientImpl(JdbcMapper<Client> JdbcMapper, SessionManager sessionManager) {
        this.jdbcMapper = JdbcMapper;
        this.sessionManager = sessionManager;
    }

    @Override
    public long saveClient(Client client) {
        sessionManager.beginSession();
        try {
            jdbcMapper.insert(client);
            sessionManager.commitSession();

            logger.info("created client: {}", client);
            return client.getId();
        } catch (Exception e) {
            sessionManager.rollbackSession();
            throw new DbServiceException(e);
        }
    }

    @Override
    public Optional<Client> getClient(long id) {
        sessionManager.beginSession();
        try {
            Client client = jdbcMapper.findById(id, Client.class);

            logger.info("client: {}", client);
            return Optional.ofNullable(client);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            sessionManager.rollbackSession();
        }
        return Optional.empty();
    }
}
