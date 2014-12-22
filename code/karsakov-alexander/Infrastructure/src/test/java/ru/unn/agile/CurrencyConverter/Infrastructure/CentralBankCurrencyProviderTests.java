package ru.unn.agile.CurrencyConverter.Infrastructure;

import org.junit.Ignore;
import ru.unn.agile.CurrencyConverter.Provider.FixedCurrencyProviderTests;

@Ignore
public class CentralBankCurrencyProviderTests extends FixedCurrencyProviderTests {
    @Override
    public void init() {
        setExternalProvider(new CentralBankCurrencyProvider());
    }
}
