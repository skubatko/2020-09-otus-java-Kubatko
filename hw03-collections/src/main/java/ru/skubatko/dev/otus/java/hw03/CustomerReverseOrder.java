package ru.skubatko.dev.otus.java.hw03;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public final class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса

    Queue<Customer> pq = new PriorityQueue<>(Comparator.comparingLong(Customer::getId).reversed());

    public void add(Customer customer) {
        pq.add(customer);
    }

    public Customer take() {
        return pq.remove();
    }
}
