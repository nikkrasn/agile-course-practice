package ru.unn.agile.StatisticalValues.infrastructure;

import ru.unn.agile.StatisticalValues.viewmodel.ViewModelTests;

public class ViewModelWithSimpleTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        super.setUpViewModelAndLogger(new SimpleTxtLogger("ViewModelSimpleTxtLoggerTests.log"));
    }
}
