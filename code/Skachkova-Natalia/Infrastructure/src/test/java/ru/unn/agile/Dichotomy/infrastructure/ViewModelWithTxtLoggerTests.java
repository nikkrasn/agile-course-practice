package ru.unn.agile.Dichotomy.infrastructure;

import ru.unn.agile.Dichotomy.viewmodel.ViewModel;
import ru.unn.agile.Dichotomy.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
                new TxtLogger("./ViewModel_with_TxtLogger_Tests-lab3-dichotomy.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
