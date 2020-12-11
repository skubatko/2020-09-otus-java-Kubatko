package ru.skubatko.dev.otus.java.hw18.jdbc.mapper;

import ru.skubatko.dev.otus.java.hw18.jdbc.DbExecutor;
import ru.skubatko.dev.otus.java.hw18.jdbc.sessionmanager.SessionManagerJdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class JdbcMapperImpl<T> implements JdbcMapper<T> {

    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<T> dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    private static final Logger log = LoggerFactory.getLogger(JdbcMapperImpl.class);

    public JdbcMapperImpl(SessionManagerJdbc sessionManager,
                          DbExecutor<T> dbExecutor,
                          EntitySQLMetaData entitySQLMetaData,
                          EntityClassMetaData<T> entityClassMetaData) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public void insert(T objectData) {
        log.debug("insert() - start: objectData = {}", objectData);
        try {
            String query = entitySQLMetaData.getInsertSql();
            log.trace("insert() - trace: query = {}", query);

            List<Object> params = entityClassMetaData.getFieldsWithoutId().stream()
                                          .map(field -> getFieldValue(field, objectData))
                                          .collect(Collectors.toList());
            log.trace("insert() - trace: params = {}", params);
            dbExecutor.executeInsert(getConnection(), query, params);
        } catch (Exception e) {
            log.debug("insert() - verdict: cannot be performed");
            throw new JdbcMapperException(e);
        }
        log.debug("insert() - end");
    }

    private Object getFieldValue(Field field, T objectData) {
        try {
            field.setAccessible(true);
            return field.get(objectData);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T objectData) {
        log.debug("update() - start: objectData = {}", objectData);
        try {
            dbExecutor.executeInsert(getConnection(), entitySQLMetaData.getUpdateSql(),
                    Collections.singletonList(objectData));
        } catch (Exception e) {
            log.debug("update() - verdict: cannot be performed");
            throw new JdbcMapperException(e);
        }
        log.debug("update() - end");
    }

    @Override
    public void insertOrUpdate(T objectData) {
        // FIXME: should check if objectData exists and then insert or update
        try {
            dbExecutor.executeInsert(getConnection(), entitySQLMetaData.getInsertSql(),
                    Collections.singletonList(objectData));
        } catch (Exception e) {
            throw new JdbcMapperException(e);
        }
    }

    @Override
    public T findById(Object id, Class<T> clazz) {
        try {
            return dbExecutor.executeSelect(getConnection(), entitySQLMetaData.getSelectByIdSql(), id,
                    rs -> {
                        try {
                            if (rs.next()) {
                                Constructor<T> constructor = (Constructor<T>) clazz.getDeclaredConstructors()[0];
                                Parameter[] parameters = constructor.getParameters();
                                int numberOfParameters = parameters.length;
                                Object[] initArgs = new Object[numberOfParameters];
                                for (int i = 0; i < numberOfParameters; i++) {
                                    Class<?> type = parameters[i].getType();
                                    String name = parameters[i].getName();
                                    initArgs[i] = rs.getObject(name, type);
                                }
                                return constructor.newInstance(initArgs);
                            }
                        } catch (SQLException | ReflectiveOperationException e) {
                            throw new JdbcMapperException(e);
                        }
                        return null;
                    }).orElse(null);
        } catch (Exception e) {
            throw new JdbcMapperException(e);
        }
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }
}
