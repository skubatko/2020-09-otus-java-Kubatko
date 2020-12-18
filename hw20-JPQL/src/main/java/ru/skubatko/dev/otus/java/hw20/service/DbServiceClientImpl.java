package ru.skubatko.dev.otus.java.hw20.service;

import ru.skubatko.dev.otus.java.hw20.jdbc.mapper.JdbcMapper;
import ru.skubatko.dev.otus.java.hw20.model.Client;
import ru.skubatko.dev.otus.java.hw20.sessionmanager.SessionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DbServiceClientImpl implements DBServiceClient<Client, Long> {

    private final JdbcMapper<Client> jdbcMapper;
    private final SessionManager sessionManager;

    private static final Logger logger = LoggerFactory.getLogger(DbServiceClientImpl.class);

    public DbServiceClientImpl(JdbcMapper<Client> jdbcMapper, SessionManager sessionManager) {
        this.jdbcMapper = jdbcMapper;
        this.sessionManager = sessionManager;
    }

    @Override
    public Long saveEntity(Client client) {
        sessionManager.beginSession();
        try {
            jdbcMapper.insertOrUpdate(client);
            sessionManager.commitSession();

            logger.info("saved client: {}", client);
            return client.getId();
        } catch (Exception e) {
            sessionManager.rollbackSession();
            throw new DbServiceException(e);
        }
    }

    @Override
    public Optional<Client> getEntityById(Long id) {
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
