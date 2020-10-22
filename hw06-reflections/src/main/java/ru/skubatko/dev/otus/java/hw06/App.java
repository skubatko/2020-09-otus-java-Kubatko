package ru.skubatko.dev.otus.java.hw06;

import ru.skubatko.dev.otus.java.hw06.model.TestResultDetails;
import ru.skubatko.dev.otus.java.hw06.service.TestReportService;
import ru.skubatko.dev.otus.java.hw06.service.TestRunner;
import ru.skubatko.dev.otus.java.hw06.tests.ServiceOneTest;
import ru.skubatko.dev.otus.java.hw06.tests.ServiceThreeTest;
import ru.skubatko.dev.otus.java.hw06.tests.ServiceTwoTest;

import java.util.List;

public class App {

    public static void main(String[] args) {
        TestReportService testReportService = new TestReportService();

        List<TestResultDetails> testResults = new TestRunner(ServiceOneTest.class).launch();
        testReportService.report(ServiceOneTest.class, testResults);

        testResults = new TestRunner(ServiceTwoTest.class).launch();
        testReportService.report(ServiceTwoTest.class, testResults);

        testResults = new TestRunner(ServiceThreeTest.class).launch();
        testReportService.report(ServiceThreeTest.class, testResults);
    }
}
