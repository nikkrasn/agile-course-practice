package ru.unn.agile.CurrencyConverter.Provider;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CurrencyConverter.Model.Currency;
import ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class FixedCurrencyProviderTests {
    private ICurrencyProvider provider;

    private static boolean checkCurrencyRatesContainsAllCurrencyInCurrencyIndex(
        final ArrayList<Currency> currencyRates) {
        for (CurrencyIndexes index : CurrencyIndexes.values()) {
            boolean isCurrencyFound = false;
            for (final Currency currency : currencyRates) {
                if (currency.getNumCode() == index.getNumCode()) {
                    isCurrencyFound = true;
                }
            }
            if (!isCurrencyFound) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkCurrencyRatesContainsUniqueValues(
            final ArrayList<Currency> currencyRates) {
        HashSet<Currency> uniqueCurrencies = new HashSet<Currency>(currencyRates);
        return uniqueCurrencies.size() == currencyRates.size();
    }

    public void setExternalProvider(ICurrencyProvider provider) {
        this.provider = provider;
    }

    @Before
    public void init() {
        provider = new FixedCurrencyProvider();
    }

    @Test
    public void fixedCurrencyProviderReturnsNotEmptyList() {
        ArrayList<Currency> currencyRates = provider.getActualCurrencyRates();

        assertTrue(currencyRates.size() > 0);
    }

    @Test
    public void fixedCurrencyProviderReturnsAtLeastThreeCurrency() {
        ArrayList<Currency> currencyRates = provider.getActualCurrencyRates();

        assertTrue(currencyRates.size() >= 3);
    }

    @Test
    public void fixedCurrencyProviderReturnsAllCurrencyInCurrencyIndex() {
        ArrayList<Currency> currencyRates = provider.getActualCurrencyRates();

        assertTrue(checkCurrencyRatesContainsAllCurrencyInCurrencyIndex(currencyRates));
    }

    @Test
    public void fixedCurrencyProviderReturnsUniqueCurrency() {
        ArrayList<Currency> currencyRates = provider.getActualCurrencyRates();

        assertTrue(checkCurrencyRatesContainsUniqueValues(currencyRates));
    }
}
