package ru.unn.agile.ComplexNumber.infrastructure_lab3_legacy;

import ru.unn.agile.ComplexNumber.viewmodel_lab3_legacy.ViewModel;
import ru.unn.agile.ComplexNumber.viewmodel_lab3_legacy.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./ViewModelWithTxtLoggerTests-lab3-legacy.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
