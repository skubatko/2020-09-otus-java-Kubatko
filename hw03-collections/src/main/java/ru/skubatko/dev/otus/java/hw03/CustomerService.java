package ru.skubatko.dev.otus.java.hw03;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.TreeMap;

public final class CustomerService {

    //todo: 3. надо реализовать методы этого класса

    TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return getNextImmutableEntry(getIterator().next());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Iterator<Map.Entry<Customer, String>> iterator = getIterator();
        while (iterator.hasNext()) {
            Map.Entry<Customer, String> entry = iterator.next();
            if (Objects.equals(entry.getKey(), customer)) {
                return getNextImmutableEntry(iterator.next());
            }
        }
        throw new NoSuchElementException();
    }

    private Iterator<Map.Entry<Customer, String>> getIterator() {
        return map.entrySet().iterator();
    }

    private AbstractMap.SimpleEntry<Customer, String> getNextImmutableEntry(Map.Entry<Customer, String> entry) {
        Customer key = entry.getKey();
        return new AbstractMap.SimpleEntry<>(
                new Customer(key.getId(), key.getName(), key.getScores()),
                entry.getValue());
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
