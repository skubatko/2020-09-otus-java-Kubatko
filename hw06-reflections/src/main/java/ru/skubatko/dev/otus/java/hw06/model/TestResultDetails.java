package ru.skubatko.dev.otus.java.hw06.model;

import ru.skubatko.dev.otus.java.hw06.enums.TestResult;

import lombok.Value;

import java.lang.reflect.Method;

@Value
public class TestResultDetails {
    Method method;
    TestResult result;
    String details;
}
