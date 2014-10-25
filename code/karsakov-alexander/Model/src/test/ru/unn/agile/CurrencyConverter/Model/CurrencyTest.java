package ru.unn.agile.CurrencyConverter.Model;

import org.junit.Test;

public class CurrencyTest {
    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionOnNullCharCode() {
        new Currency(840, null, "Доллар США", 1, 41.8101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionOnNullName() {
        new Currency(840, "USD", null, 1, 41.8101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionOnNullNominal() {
        new Currency(840, "USD", "Доллар США", 0, 41.8101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionOnNegativeNominal() {
        new Currency(840, "USD", "Доллар США", -1, 41.8101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionOnNullValue() {
        new Currency(840, "USD", "Доллар США", 1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionOnNegativeValue() {
        new Currency(840, "USD", "Доллар США", 1, -1);
    }
}
