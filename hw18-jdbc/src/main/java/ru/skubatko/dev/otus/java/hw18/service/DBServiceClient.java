package ru.skubatko.dev.otus.java.hw18.service;

import ru.skubatko.dev.otus.java.hw18.model.Client;

import java.util.Optional;

public interface DBServiceClient {

    long saveClient(Client client);

    Optional<Client> getClient(long id);
}
