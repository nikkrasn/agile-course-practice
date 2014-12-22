package ru.unn.agile.Stack.Infrastructure;

import ru.unn.agile.Stack.ViewModel.ViewModelTests;

public class ViewModelWithPlainTextLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        super.setLogger(new PlainTextLogger("ViewModel_with_PlainTextLogger_Tests.log"));
    }
}
