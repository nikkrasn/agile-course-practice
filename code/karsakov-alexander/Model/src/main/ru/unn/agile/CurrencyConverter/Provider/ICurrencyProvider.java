package ru.unn.agile.CurrencyConverter.Provider;

import ru.unn.agile.CurrencyConverter.Model.Currency;
import java.util.ArrayList;

public interface ICurrencyProvider {
    ArrayList<Currency> getActualCurrencyRates();
}
