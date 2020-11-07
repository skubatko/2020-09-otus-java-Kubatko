package ru.skubatko.dev.otus.java.hw10;

public class TestLoggingImpl implements TestLogging {

    @Log
    @Override
    public void calculation(int param) {}

    @Log
    @Override
    public void calculation(int param1, int param2) {}

    @Log
    @Override
    public void calculation(int param1, int param2, String param3) {}

    @Override
    public void calculation(int param1, int param2, String param3, long param4) {}
}
