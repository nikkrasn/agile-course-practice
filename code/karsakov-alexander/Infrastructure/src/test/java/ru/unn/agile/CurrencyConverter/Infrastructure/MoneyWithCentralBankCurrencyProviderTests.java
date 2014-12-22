package ru.unn.agile.CurrencyConverter.Infrastructure;

import ru.unn.agile.CurrencyConverter.Model.MoneyTests;

public class MoneyWithCentralBankCurrencyProviderTests extends MoneyTests {
    @Override
    public void init() {
        setExternalCurrencyProvider(new CentralBankCurrencyProvider());
        super.init();
    }
}
