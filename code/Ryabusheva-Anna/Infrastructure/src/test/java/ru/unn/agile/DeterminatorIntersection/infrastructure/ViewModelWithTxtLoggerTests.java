package ru.unn.agile.DeterminatorIntersection.infrastructure;

import ru.unn.agile.DeterminatorIntersection.viewmodel.*;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
                new TxtLogger("./ViewModel_with_TxtLogger_Tests-lab3.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
