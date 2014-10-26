package ru.unn.agile.CurrencyConverter.Provider;

import ru.unn.agile.CurrencyConverter.Model.Currency;

import static ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes.*;

public class FixedCurrencyProvider implements ICurrencyProvider {
    private static final int LIST_SIZE = 3;
    private static final double USD_RATE = 41.8101;
    private static final double EUR_RATE = 52.9065;
    private static final double RUB_RATE = 1;

    @Override
    public final Currency[] getActualCurrencyRates() {
        Currency[] fixedCurrencyRates = new Currency[LIST_SIZE];

        fixedCurrencyRates[USD.getIndex()] =
            new Currency(USD.getNumCode(), "USD", "Доллар США", 1, USD_RATE);
        fixedCurrencyRates[EUR.getIndex()] =
            new Currency(EUR.getNumCode(), "EUR", "Евро", 1, EUR_RATE);
        fixedCurrencyRates[RUB.getIndex()] =
            new Currency(RUB.getNumCode(), "RUB", "Российский рубль", 1, RUB_RATE);

        return fixedCurrencyRates;
    }
}
