package ru.unn.agile.StatisticalValues.infrastructure;

import ru.unn.agile.StatisticalValues.viewmodel.SimpleLogger;
import ru.unn.agile.StatisticalValues.viewmodel.ViewModel;
import ru.unn.agile.StatisticalValues.viewmodel.ViewModelTests;

public class ViewModelWithSimpleTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        SimpleLogger logger = new SimpleTxtLogger("ViewModelSimpleTxtLoggerTests.log");
        super.setViewModel(new ViewModel(logger));
    }
}
