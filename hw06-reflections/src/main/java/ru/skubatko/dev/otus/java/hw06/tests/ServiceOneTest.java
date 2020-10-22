package ru.skubatko.dev.otus.java.hw06.tests;

import ru.skubatko.dev.otus.java.hw06.annotations.After;
import ru.skubatko.dev.otus.java.hw06.annotations.Before;
import ru.skubatko.dev.otus.java.hw06.annotations.Test;

public class ServiceOneTest {

    @Before
    public void beforeFirst() {
    }

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

    @Before
    public void beforeSecond() {
    }

    @Test
    public void testFourth() {
        throw new RuntimeException();
    }

    @After
    public void afterSecond() {
    }
}
