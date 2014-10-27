package ru.unn.agile.CurrencyConverter.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CurrencyTest {
    private Currency validCurrency;

    @Before
    public void init() {
        validCurrency = new Currency(840, "USD", "Доллар США", 1, 41.8101);
    }

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
        new Currency(840, "USD", "Доллар США", -10, 41.8101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionOnNullValue() {
        new Currency(840, "USD", "Доллар США", 1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionOnNegativeValue() {
        new Currency(840, "USD", "Доллар США", 1, -10);
    }

    @Test
    public void currencyCompareWithNullReturnsFalse() {
        assertFalse(validCurrency.equals(null));
    }

    @Test
    public void currencyCompareWithItselfReturnsTrue() {
        assertTrue(validCurrency.equals(validCurrency));
    }

    @Test
    public void currencyCompareWithAnotherReturnsFalse() {
        Currency anotherValidCurrency = new Currency(978, "EUR", "Евро", 1, 52.9065);
        assertFalse(validCurrency.equals(anotherValidCurrency));
    }
}
