package ru.skubatko.dev.otus.java.hw12;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

@DisplayName("банкомат")
class AtmImplTest {

    private Atm atm;

    @BeforeEach
    private void setUp() {
        Map<Banknote, Integer> container = new TreeMap<>();
        container.put(Banknote.FIVE, 5);
        container.put(Banknote.TEN, 5);
        container.put(Banknote.FIFTY, 5);
        container.put(Banknote.HUNDRED, 5);
        container.put(Banknote.TWO_HUNDRED, 5);
        container.put(Banknote.FIVE_HUNDRED, 5);
        container.put(Banknote.THOUSAND, 5);
        container.put(Banknote.TWO_THOUSAND, 5);
        container.put(Banknote.FIVE_THOUSAND, 5);

        atm = new AtmImpl(container);
    }

    @DisplayName("должен принимать банкноты разных номиналов")
    @Test
    void shouldAcceptDifferentBanknotes() {
        Map<Banknote, Integer> container = new TreeMap<>();
        container.put(Banknote.FIVE, 5);
        container.put(Banknote.TEN, 5);
        container.put(Banknote.FIFTY, 5);
        container.put(Banknote.HUNDRED, 5);
        container.put(Banknote.TWO_HUNDRED, 5);
        container.put(Banknote.FIVE_HUNDRED, 5);
        container.put(Banknote.THOUSAND, 5);
        container.put(Banknote.TWO_THOUSAND, 5);
        container.put(Banknote.FIVE_THOUSAND, 5);

        atm.put(container);

        assertThat(atm.sum()).isEqualTo(88650);
    }

    @DisplayName("должен выдавать запрошенную сумму минимальным количеством банкнот")
    @Test
    void shouldGiveRequestedAmountByMinimalNumberOfBanknotes() {
        Map<Banknote, Integer> expected = Map.of(
                Banknote.TEN, 1,
                Banknote.FIVE_HUNDRED, 1,
                Banknote.TWO_THOUSAND, 2,
                Banknote.FIVE_THOUSAND, 1
        );

        Map<Banknote, Integer> banknotes = atm.get(9510);

        assertThat(banknotes).isNotEmpty().isEqualTo(expected);
    }

    @DisplayName("должен выдавать ошибку если сумму нельзя выдать")
    @Test
    void shouldThrowExceptionIfAmountCannotBeGiven() {
        assertThatThrownBy(() -> atm.get(1)).isInstanceOf(AtmWithdrawException.class);
        assertThatThrownBy(() -> atm.get(50000)).isInstanceOf(AtmWithdrawException.class);
    }

    @DisplayName("должен выдавать сумму остатка денежных средств")
    @Test
    void shouldGiveSumOfAtmLeftAmount() {
        int sum = atm.sum();

        assertThat(sum).isEqualTo(44325);
    }
}
