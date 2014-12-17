package ru.unn.agile.ComplexNumber.infrastructure_lab3;

import ru.unn.agile.ComplexNumber.viewmodel_lab3.ViewModel;
import ru.unn.agile.ComplexNumber.viewmodel_lab3.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./ViewModel_with_TxtLogger_Tests-lab3.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
