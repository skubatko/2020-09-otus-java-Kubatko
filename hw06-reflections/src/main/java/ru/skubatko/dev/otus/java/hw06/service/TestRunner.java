package ru.skubatko.dev.otus.java.hw06.service;

import ru.skubatko.dev.otus.java.hw06.annotations.After;
import ru.skubatko.dev.otus.java.hw06.annotations.Before;
import ru.skubatko.dev.otus.java.hw06.annotations.Test;
import ru.skubatko.dev.otus.java.hw06.enums.TestResult;
import ru.skubatko.dev.otus.java.hw06.exceptions.TestExecutionFailedException;
import ru.skubatko.dev.otus.java.hw06.model.TestResultDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log
@RequiredArgsConstructor
public class TestRunner {

    private final Class<?> clazz;

    public List<TestResultDetails> launch() {
        return getMethodsByAnnotation(Test.class).stream()
                       .map(this::performTest)
                       .collect(Collectors.toList());
    }

    private TestResultDetails performTest(Method method) {
        try {
            log.info(String.format("performTest() - start: method = %s", method.getName()));
            Object instance = clazz.getDeclaredConstructor().newInstance();

            setUp(instance);
            TestResultDetails testResultDetails = executeTest(method, instance);
            tearDown(instance);

            log.info(String.format("performTest() - end: testResultDetails = %s", testResultDetails));
            return testResultDetails;
        } catch (ReflectiveOperationException e) {
            String errorMsg = "Default constructor is not accessible";
            log.warning(String.format("performTest() - verdict: failed with msg = %s", errorMsg));
            throw new TestExecutionFailedException(errorMsg);
        }
    }

    private TestResultDetails executeTest(Method method, Object instance) {
        try {
            log.info(String.format("executeTest() - start: method = %s, instance = %s", method.getName(), instance));
            method.invoke(instance);
            log.info("executeTest() - verdict: passed");
            return new TestResultDetails(method, TestResult.PASSED, "success");
        } catch (Exception e) {
            log.info(String.format("executeTest() - verdict: failed with msg = %s", e.getMessage()));
            return new TestResultDetails(method, TestResult.FAILED, e.getCause().toString());
        }
    }

    private void setUp(Object instance) {
        getMethodsByAnnotation(Before.class)
                .forEach(beforeMethod -> executeBeforeMethod(beforeMethod, instance));
    }

    private void executeBeforeMethod(Method method, Object instance) {
        try {
            log.info(String.format("executeBeforeMethod() - start: method = %s, instance = %s", method.getName(), instance));
            method.invoke(instance);
            log.info("executeBeforeMethod() - end");
        } catch (Exception e) {
            log.info(String.format("executeBeforeMethod() - verdict: failed with msg = %s", e.getMessage()));
        }
    }

    private void tearDown(Object instance) {
        getMethodsByAnnotation(After.class)
                .forEach(afterMethod -> executeAfterMethod(afterMethod, instance));
    }

    private void executeAfterMethod(Method method, Object instance) {
        try {
            log.info(String.format("executeAfterMethod() - start: method = %s, instance = %s", method.getName(), instance));
            method.invoke(instance);
            log.info("executeAfterMethod() - end");
        } catch (Exception e) {
            log.info(String.format("executeAfterMethod() - verdict: failed with msg = %s", e.getMessage()));
        }
    }

    private List<Method> getMethodsByAnnotation(Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods())
                       .filter(method -> method.isAnnotationPresent(annotation))
                       .collect(Collectors.toList());
    }
}
