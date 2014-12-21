package ru.unn.agile.ConverterWeight.Infrastructure;

import ru.unn.agile.ConverterWeight.viewmodel.ViewModel;
import ru.unn.agile.ConverterWeight.viewmodel.ViewModelTests;

public class ViewModelWithLoggerTxtTests extends ViewModelTests {
    @Override
    public void setUp() {
        LoggerTxt realLogger =
            new LoggerTxt("./ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
