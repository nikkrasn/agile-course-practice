package ru.unn.agile.CurrencyConverter.Infrastructure;

import ru.unn.agile.CurrencyConverter.viewmodel.ViewModel;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModelTests;

public class ViewModelWithPlainTextLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        PlainTextLogger realLogger =
                new PlainTextLogger("./ViewModel_with_PlainTextLogger_Tests.log");
        super.setUp();
        setExternalViewModel(new ViewModel(realLogger));
    }
}