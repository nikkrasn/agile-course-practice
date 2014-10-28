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

        fixedCurrencyRates[USD.getIndex()] = Currency.builder().numCode(USD.getNumCode())
                                                                   .charCode("USD")
                                                                   .name("Доллар США")
                                                                   .nominal(1)
                                                                   .value(USD_RATE)
                                                                   .build();
        fixedCurrencyRates[EUR.getIndex()] = Currency.builder().numCode(EUR.getNumCode())
                                                                   .charCode("EUR")
                                                                   .name("Евро")
                                                                   .nominal(1)
                                                                   .value(EUR_RATE)
                                                                   .build();
        fixedCurrencyRates[RUB.getIndex()] = Currency.builder().numCode(RUB.getNumCode())
                                                                   .charCode("RUB")
                                                                   .name("Российский рубль")
                                                                   .nominal(1)
                                                                   .value(RUB_RATE)
                                                                   .build();
        return fixedCurrencyRates;
    }
}
