package ru.skubatko.dev.otus.java.hw23.server;

public interface WebServer {

    void start() throws Exception;

    void join() throws Exception;

    void stop() throws Exception;
}
