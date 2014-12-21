package ru.unn.agile.TemperatureConverter.infrastructure;

import ru.unn.agile.TemperatureConverter.viewmodel.ViewModel;
import ru.unn.agile.TemperatureConverter.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger trueLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new ViewModel(trueLogger));
    }
}
