package ru.unn.agile.CurrencyConverter.Model;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CurrencyConverter.Provider.FixedCurrencyProvider;
import ru.unn.agile.CurrencyConverter.Provider.ICurrencyProvider;

import static org.junit.Assert.*;
import static ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes.EUR;
import static ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes.USD;

public class CurrencyTest {
    private Currency[] validCurrencies;

    @Before
    public void init() {
        ICurrencyProvider provider = new FixedCurrencyProvider();
        validCurrencies = provider.getActualCurrencyRates();
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyBuilderThrowsExceptionOnNullNumCode() {
        new Currency.Builder().numCode(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyBuilderThrowsExceptionOnNegativeNumCode() {
        new Currency.Builder().numCode(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyBuilderThrowsExceptionOnNullCharCode() {
        new Currency.Builder().charCode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyBuilderThrowsExceptionOnNullName() {
        new Currency.Builder().name(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyBuilderThrowsExceptionOnNullNominal() {
        new Currency.Builder().nominal(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyBuilderThrowsExceptionOnNegativeNominal() {
        new Currency.Builder().nominal(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyBuilderThrowsExceptionOnNegativeValue() {
        new Currency.Builder().value(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionIfNumCodeIsNotSpecified() {
        new Currency.Builder().charCode("USD")
                              .name("Доллар США")
                              .nominal(1)
                              .value(41.8101)
                              .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionIfCharCodeIsNotSpecified() {
        new Currency.Builder().numCode(840)
                              .name("Доллар США")
                              .nominal(1)
                              .value(41.8101)
                              .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionIfNameIsNotSpecified() {
        new Currency.Builder().numCode(840)
                              .charCode("USD")
                              .nominal(1)
                              .value(41.8101)
                              .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionIfNominalIsNotSpecified() {
        new Currency.Builder().numCode(840)
                              .charCode("USD")
                              .name("Доллар США")
                              .value(41.8101)
                              .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyThrowsExceptionIfValueIsNotSpecified() {
        new Currency.Builder().numCode(840)
                              .charCode("USD")
                              .name("Доллар США")
                              .nominal(1)
                              .build();
    }

    @Test
    public void currencyCompareWithNullReturnsFalse() {
        Currency validCurrency = validCurrencies[USD.getIndex()];

        assertFalse(validCurrency.equals(null));
    }

    @Test
    public void currencyCompareWithItselfReturnsTrue() {
        Currency validCurrency = validCurrencies[USD.getIndex()];

        assertTrue(validCurrency.equals(validCurrency));
    }

    @Test
    public void currencyCompareWithAnotherReturnsFalse() {
        Currency validCurrency = validCurrencies[USD.getIndex()];
        Currency anotherValidCurrency = validCurrencies[EUR.getIndex()];

        assertFalse(validCurrency.equals(anotherValidCurrency));
    }
}
