package ru.unn.agile.AreaConverter.Infrastructure;

import ru.unn.agile.AreaConverter.viewmodel.ViewModel;
import ru.unn.agile.AreaConverter.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLoggerAreaConverter realLogger =
            new TxtLoggerAreaConverter("./ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
