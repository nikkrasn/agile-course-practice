package ru.unn.agile.CurrencyConverter.Infrastructure;

import ru.unn.agile.CurrencyConverter.Provider.FixedCurrencyProviderTests;

public class CentralBankCurrencyProviderTests extends FixedCurrencyProviderTests {
    @Override
    public void init() {
        setExternalProvider(new CentralBankCurrencyProvider());
    }
}
