package ru.skubatko.dev.otus.java.hw03;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public final class CustomerService {

    //todo: 3. надо реализовать методы этого класса

    private final NavigableMap<Customer, String> customers =
            new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return getEntryOrNull(customers.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return getEntryOrNull(customers.higherEntry(customer));
    }

    private Map.Entry<Customer, String> getEntryOrNull(Map.Entry<Customer, String> entry) {
        return entry == null ? null : new CustomerEntry(entry.getKey(), entry.getValue());
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }

    private static class CustomerEntry implements Map.Entry<Customer, String> {

        private final Customer key;
        private String value;

        public CustomerEntry(Customer key, String value) {
            this.key = Customer.copyOf(key);
            this.value = value;
        }

        @Override
        public Customer getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String setValue(String value) {
            this.value = value;
            return value;
        }
    }
}
