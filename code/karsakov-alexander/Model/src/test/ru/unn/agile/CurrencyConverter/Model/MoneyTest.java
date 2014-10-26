package ru.unn.agile.CurrencyConverter.Model;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    private Currency testCurrency;

    @Before
    public void init() {
        testCurrency = new Currency(840, "USD", "Доллар США", 1, 41.8101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moneyThrowsExceptionOnNullCurrency() {
        new Money(null, 41.8101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moneyThrowsExceptionOnNullAmount() {
        new Money(testCurrency, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moneyThrowsExceptionOnNegativeAmount() {
        new Money(testCurrency, -10);
    }
}
