package ru.unn.agile.CurrencyConverter.Provider;

import ru.unn.agile.CurrencyConverter.Model.Currency;

public interface ICurrencyProvider {
    Currency[] getActualCurrencyRates();
}
