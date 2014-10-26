package ru.unn.agile.CurrencyConverter.Provider;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CurrencyConverter.Model.Currency;

import static org.junit.Assert.*;
import static ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes.*;

public class FixedCurrencyProviderTest {
    private ICurrencyProvider provider;

    private static boolean checkCurrencyRatesContainsNumCode(final Currency[] currencyRates,
                                                             final int numCode) {
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
        Currency[] currencyRates = provider.getActualCurrencyRates();

        assertTrue(currencyRates.length > 0);
    }

    @Test
    public void fixedCurrencyProviderReturnsAtLeastThreeCurrency() {
        Currency[] currencyRates = provider.getActualCurrencyRates();

        assertTrue(currencyRates.length >= 3);
    }

    @Test
    public void fixedCurrencyProviderReturnsRequiredCurrency() {
        Currency[] currencyRates = provider.getActualCurrencyRates();

        assertTrue(checkCurrencyRatesContainsNumCode(currencyRates, USD.getNumCode()));
        assertTrue(checkCurrencyRatesContainsNumCode(currencyRates, EUR.getNumCode()));
        assertTrue(checkCurrencyRatesContainsNumCode(currencyRates, RUB.getNumCode()));
    }

    @Test
    public void fixedCurrencyProviderReturnsUniqueCurrency() {
        Currency[] currencyRates = provider.getActualCurrencyRates();

        for (int i = 0; i < currencyRates.length; i++) {
            for (int j = 0; j < currencyRates.length; j++) {
                if (i != j) {
                    assertFalse(currencyRates[i].isEqual(currencyRates[j]));
                }
            }
        }
    }
}
