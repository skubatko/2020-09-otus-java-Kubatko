package ru.skubatko.dev.otus.java.hw03;

import java.util.ArrayDeque;
import java.util.Deque;

public final class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса

    private final Deque<Customer> stack = new ArrayDeque<>();

    public void add(Customer customer) {
        stack.push(customer);
    }

    public Customer take() {
        return stack.pop();
    }
}
