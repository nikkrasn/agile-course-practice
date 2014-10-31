package ru.unn.agile.CurrencyConverter.Provider;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CurrencyConverter.Model.Currency;
import ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FixedCurrencyProviderTest {
    private ICurrencyProvider provider;

    private static boolean checkCurrencyRatesContainsNumCode(
        final ArrayList<Currency> currencyRates, final int numCode) {
        for (final Currency currency : currencyRates) {
            if (currency.getNumCode() == numCode) {
                return true;
            }
        }
        return false;
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
    public void fixedCurrencyProviderReturnsRequiredCurrency() {
        ArrayList<Currency> currencyRates = provider.getActualCurrencyRates();

        for (CurrencyIndexes index : CurrencyIndexes.values()) {
            assertTrue(checkCurrencyRatesContainsNumCode(currencyRates, index.getNumCode()));
        }
    }

    @Test
    public void fixedCurrencyProviderReturnsUniqueCurrency() {
        ArrayList<Currency> currencyRates = provider.getActualCurrencyRates();

        for (int i = 0; i < currencyRates.size(); i++) {
            for (int j = 0; j < currencyRates.size(); j++) {
                if (i != j) {
                    assertFalse(currencyRates.get(i) == currencyRates.get(j));
                }
            }
        }
    }
}
