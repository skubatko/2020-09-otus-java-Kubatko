package ru.skubatko.dev.otus.java.hw03;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;

public final class CustomerService {

    //todo: 3. надо реализовать методы этого класса

    private final NavigableMap<Customer, String> customers =
            new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return getNextImmutableEntry(getIterator());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        boolean exists = customers.containsKey(customer);
        if (!exists) {
            customers.put(customer, "getNext");
        }

        Iterator<Map.Entry<Customer, String>> iterator = getIterator();
        while (iterator.hasNext()) {
            Map.Entry<Customer, String> entry = iterator.next();
            if (Objects.equals(entry.getKey(), customer)) {
                AbstractMap.SimpleEntry<Customer, String> result = getNextImmutableEntry(iterator);

                if (!exists) {
                    customers.remove(customer);
                }

                return result;
            }
        }

        return null;
    }

    private Iterator<Map.Entry<Customer, String>> getIterator() {
        return customers.entrySet().iterator();
    }

    private AbstractMap.SimpleEntry<Customer, String> getNextImmutableEntry(
            Iterator<Map.Entry<Customer, String>> iterator) {
        if (!iterator.hasNext()) {
            return null;
        }

        Map.Entry<Customer, String> entry = iterator.next();
        Customer key = entry.getKey();
        return new AbstractMap.SimpleEntry<>(
                new Customer(key.getId(), key.getName(), key.getScores()),
                entry.getValue());
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
