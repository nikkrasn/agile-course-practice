package ru.unn.agile.DemandElasticity.infrastructure;

import ru.unn.agile.DemandElasticity.viewmodel.ViewModel;
import ru.unn.agile.DemandElasticity.viewmodel.ViewModelTest;

public class ViewModelWithLoggerToTxtTests extends ViewModelTest {
    @Override
    public void setUp() {
        LoggerToTxt loggerToRealFile =
                new LoggerToTxt("./ViewModel_with_LoggerToTxt_Tests.log");
        super.setTestedViewModel(new ViewModel(loggerToRealFile));
    }
}
