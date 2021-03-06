package ru.skubatko.dev.otus.java.hw12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AtmImpl implements Atm {
    private final Map<Banknote, Integer> container;
    private int sum = 0;

    public AtmImpl(Map<Banknote, Integer> container) {
        this.container = container;
        this.sum = calculateAmount(container);
    }

    @Override
    public void put(Map<Banknote, Integer> banknotes) {
        banknotes.forEach((banknote, number) -> container.compute(banknote, (b, n) -> n == null ? number : n + number));
        sum += calculateAmount(banknotes);
    }

    @Override
    public Map<Banknote, Integer> get(int amount) {
        Map<Banknote, Integer> result = new TreeMap<>();

        int sumLeft = amount;
        List<Banknote> banknotes = Arrays.stream(Banknote.values()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (Banknote banknote : banknotes) {
            int containerNumber = container.getOrDefault(banknote, 0);
            int requiredNumber = sumLeft / banknote.getValue();

            int takenNumber = Math.min(containerNumber, requiredNumber);
            int withdrawAmount = takenNumber * banknote.getValue();

            sumLeft -= withdrawAmount;
            sum -= withdrawAmount;

            if (takenNumber > 0) {
                container.put(banknote, containerNumber - takenNumber);
                result.put(banknote, takenNumber);
            }
        }

        if (sumLeft != 0) {
            throw new AtmWithdrawException("No suitable banknotes available");
        }

        return result;
    }

    @Override
    public int sum() {
        return sum;
    }

    @Override
    public String toString() {
        return "AtmImpl{" +
                       "container=" + container +
                       ", sum=" + sum +
                       '}';
    }

    private int calculateAmount(Map<Banknote, Integer> banknotes) {
        return banknotes.entrySet().stream().mapToInt(entry -> entry.getKey().getValue() * entry.getValue()).sum();
    }
}
