package ru.unn.agile.StatisticalValues.infrastructure;

import ru.unn.agile.StatisticalValues.viewmodel.ViewModelTests;
import ru.unn.agile.StatisticalValues.viewmodel.ViewModel;

public class ViewModelWithSimpleTxtLoggerTests extends ViewModelTests{
    @Override
    public void setUp() {
        super.setLog(new SimpleTxtLogger("ViewModelSimpleTxtLoggerTests.log"));
    }
}
