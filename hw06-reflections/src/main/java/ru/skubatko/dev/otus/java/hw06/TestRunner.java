package ru.skubatko.dev.otus.java.hw06;

import ru.skubatko.dev.otus.java.hw06.annotations.After;
import ru.skubatko.dev.otus.java.hw06.annotations.Before;
import ru.skubatko.dev.otus.java.hw06.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {

    private static List<Method> beforeMethods;
    private static List<Method> afterMethods;
    private static Statistics statistics;

    public static void launch(Class<?> clazz) {
        System.out.printf("====> started for class = %s%n", clazz.getSimpleName());

        Method[] methods = clazz.getDeclaredMethods();
        System.out.printf("number of methods = %d%n", methods.length);

        beforeMethods = getMethodsByAnnotation(methods, Before.class);
        System.out.printf("number of before methods = %d%n", beforeMethods.size());

        afterMethods = getMethodsByAnnotation(methods, After.class);
        System.out.printf("number of after methods = %d%n", afterMethods.size());

        statistics = new Statistics(clazz);

        List<Method> testMethods = getMethodsByAnnotation(methods, Test.class);
        System.out.printf("number of test methods = %d%n", testMethods.size());
        testMethods.forEach(testMethod -> executeTest(testMethod, clazz));

        System.out.println(statistics);
        System.out.printf("====> finished for class = %s%n", clazz.getSimpleName());
    }

    private static List<Method> getMethodsByAnnotation(Method[] methods, Class<? extends Annotation> annotation) {
        return Arrays.stream(methods)
                       .filter(method -> method.isAnnotationPresent(annotation))
                       .collect(Collectors.toList());
    }

    private static void executeTest(Method method, Class<?> clazz) {
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();

            executeBeforeSet(instance);
            System.out.printf("==> execute test method = %s%n", method.getName());
            method.invoke(instance);
            executeAfterSet(instance);

            statistics.passed++;
            System.out.printf("==> verdict: test method = %s passed%n", method.getName());
        } catch (Exception e) {
            statistics.failed++;
            System.out.printf("==> verdict: test method = %s failed%n", method.getName());
        } finally {
            statistics.total++;
        }
    }

    private static void executeBeforeSet(Object instance) throws Exception {
        for (Method method : beforeMethods) {
            System.out.printf("=> execute before method = %s%n", method.getName());
            method.invoke(instance);
        }
    }

    private static void executeAfterSet(Object instance) throws Exception {
        for (Method method : afterMethods) {
            System.out.printf("=> execute after method = %s%n", method.getName());
            method.invoke(instance);
        }
    }

    private static class Statistics {
        private int passed;
        private int failed;
        private int total;

        private final Class<?> clazz;

        public Statistics(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public String toString() {
            return String.format("Summary for class = %s: total tests: %d, passed: %d, failed: %d",
                    clazz.getSimpleName(), total, passed, failed);
        }
    }
}
