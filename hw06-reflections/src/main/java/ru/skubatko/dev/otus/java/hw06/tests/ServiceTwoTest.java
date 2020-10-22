package ru.skubatko.dev.otus.java.hw06.tests;

import ru.skubatko.dev.otus.java.hw06.annotations.Before;
import ru.skubatko.dev.otus.java.hw06.annotations.Test;

public class ServiceTwoTest {

    @Before
    public void beforeFirst() {
    }

    @Test
    public void testFirst() {
    }

    @Test
    public void testSecond() {
        throw new RuntimeException();
    }

    @Test
    public void testThird() {
        throw new RuntimeException();
    }

    @Before
    public void beforeSecond() {
    }

    @Test
    public void testFourth() {
        throw new RuntimeException();
    }

    @Test
    public void testFifth() {
        throw new RuntimeException();
    }
}
