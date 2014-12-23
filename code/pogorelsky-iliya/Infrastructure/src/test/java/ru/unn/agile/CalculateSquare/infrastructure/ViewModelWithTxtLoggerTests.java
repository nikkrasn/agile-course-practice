package ru.unn.agile.CalculateSquare.infrastructure;

import ru.unn.agile.CalculateSquare.viewmodel.ViewModel;
import ru.unn.agile.CalculateSquare.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TextLog realLogger =
            new TextLog("./ViewModelTxtLogTest.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
