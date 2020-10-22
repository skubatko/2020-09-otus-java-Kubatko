package ru.skubatko.dev.otus.java.hw06.tests;

import ru.skubatko.dev.otus.java.hw06.annotations.After;
import ru.skubatko.dev.otus.java.hw06.annotations.Test;

public class ServiceThreeTest {

    @Test
    public void testFirst() {
    }

    @After
    public void afterFirst() {
    }

    @Test
    public void testSecond() {
        throw new RuntimeException();
    }

    @Test
    public void testThird() {
    }

    @After
    public void afterThird() {
    }

    @After
    public void afterSecond() {
    }
}
