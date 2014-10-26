package ru.unn.agile.CurrencyConverter.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes.*;

public class FixedCurrencyProviderTest {
    private ICurrencyProvider provider;

    private static boolean checkCurrencyListContainsSpecificNumCode(final Currency[] currencyList,
                                                                    final int numCode) {
        for (final Currency currency : currencyList) {
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
        Currency[] currencyList = provider.getActualCurrency();

        assertTrue(currencyList.length > 0);
    }

    @Test
    public void fixedCurrencyProviderReturnsAtLeastThreeCurrency() {
        Currency[] currencyList = provider.getActualCurrency();

        assertTrue(currencyList.length >= 3);
    }

    @Test
    public void fixedCurrencyProviderReturnsRequiredCurrency() {
        Currency[] currencyList = provider.getActualCurrency();

        assertTrue(checkCurrencyListContainsSpecificNumCode(currencyList, USD.getNumCode()));
        assertTrue(checkCurrencyListContainsSpecificNumCode(currencyList, EUR.getNumCode()));
        assertTrue(checkCurrencyListContainsSpecificNumCode(currencyList, RUB.getNumCode()));
    }

    @Test
    public void fixedCurrencyProviderReturnsUniqueCurrency() {
        Currency[] currencyList = provider.getActualCurrency();

        for (int i = 0; i < currencyList.length; i++) {
            for (int j = 0; j < currencyList.length; j++) {
                if (i != j) {
                    assertFalse(currencyList[i].isEqual(currencyList[j]));
                }
            }
        }
    }
}
