package ru.skubatko.dev.otus.java.hw06.service;

import ru.skubatko.dev.otus.java.hw06.enums.TestResult;
import ru.skubatko.dev.otus.java.hw06.model.TestResultDetails;

import java.util.List;
import java.util.stream.Collectors;

public class TestReportService {

    public void report(Class<?> clazz,List<TestResultDetails> testResults) {
        long total = testResults.size();
        long passed = testResults.stream()
                              .filter(testResultDetails -> testResultDetails.getResult() == TestResult.PASSED)
                              .count();
        long failed = testResults.stream()
                              .filter(testResultDetails1 -> testResultDetails1.getResult() == TestResult.FAILED).count();

        System.out.printf("Summary for class = %s: total tests: %d, passed: %d, failed: %d%n",
                clazz.getSimpleName(), total, passed, failed);

        testResults.stream()
                .filter(testResultDetails -> testResultDetails.getResult() == TestResult.FAILED).collect(Collectors.toList())
                .forEach(result ->
                                 System.out.printf("test = %s failed, details = %s%n",
                                         result.getMethod().getName(), result.getDetails()));
    }
}
