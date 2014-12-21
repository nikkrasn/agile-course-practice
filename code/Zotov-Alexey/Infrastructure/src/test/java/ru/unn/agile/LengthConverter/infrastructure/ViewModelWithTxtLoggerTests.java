package ru.unn.agile.LengthConverter.infrastructure;

import ru.unn.agile.LengthConverter.viewmodel.ViewModel;
import ru.unn.agile.LengthConverter.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./ViewModelWithTxtLoggerTests-lab3-legacy.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
