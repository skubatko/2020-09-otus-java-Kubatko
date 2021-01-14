package ru.skubatko.dev.otus.java.hw24.services;

public interface IOService {
    void out(String message);
    String readLn(String prompt);
    int readInt(String prompt);
}
