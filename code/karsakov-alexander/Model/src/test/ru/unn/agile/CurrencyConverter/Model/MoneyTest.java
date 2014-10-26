package ru.unn.agile.CurrencyConverter.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoneyTest {
    private Currency usd;
    private Currency eur;
    private Money tenBucks;
    private final double delta = 0.0001;

    @Before
    public void init() {
        usd = new Currency(840, "USD", "Доллар США", 1, 41.8101);
        eur = new Currency(978, "EUR", "Евро", 1, 52.9065);
        tenBucks = new Money(usd, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moneyThrowsExceptionOnNullCurrency() {
        new Money(null, 41.8101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moneyConstructorThrowsExceptionOnNegativeAmount() {
        new Money(usd, -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moneySetAmountThrowsExceptionOnNegativeAmount() {
        tenBucks.setAmount(-10);
    }

    @Test
    public void moneyIsInCurrencyTheSameCurrencyReturnsTrue() {
        assertTrue(tenBucks.isInCurrency(usd));
    }

    @Test
    public void moneyIsInCurrencyAnotherCurrencyReturnsFalse() {
        assertFalse(tenBucks.isInCurrency(eur));
    }

    @Test
    public void moneyIsInCurrencyNullCurrencyReturnsFalse() {
        assertFalse(tenBucks.isInCurrency(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void moneyThrowsExceptionOnConvertToNullCurrency() {
        tenBucks.convertToCurrency(null);
    }

    @Test
    public void moneyConvertToIsCorrect() {
        double expectedAmount = tenBucks.getAmount() * (eur.getNominal() / eur.getValue())
                                * (usd.getValue() / usd.getNominal());

        Money someEuros = tenBucks.convertToCurrency(eur);

        assertEquals(someEuros.getAmount(), expectedAmount, delta);
    }

    @Test
    public void moneyZeroConvertToIsZero() {
        Money noDollars = new Money(usd, 0);

        Money noEuros = noDollars.convertToCurrency(eur);

        assertEquals(noEuros.getAmount(), 0, delta);
    }
}
