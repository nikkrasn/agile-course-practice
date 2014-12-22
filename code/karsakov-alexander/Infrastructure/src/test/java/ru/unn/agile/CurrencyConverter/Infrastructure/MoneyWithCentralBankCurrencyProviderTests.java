package ru.unn.agile.CurrencyConverter.Infrastructure;

import org.junit.Before;
import ru.unn.agile.CurrencyConverter.Model.MoneyTests;

public class MoneyWithCentralBankCurrencyProviderTests extends MoneyTests {
    @Before
    public void init() {
        setExternalCurrencyProvider(new CentralBankCurrencyProvider());
        super.init();
    }
}
