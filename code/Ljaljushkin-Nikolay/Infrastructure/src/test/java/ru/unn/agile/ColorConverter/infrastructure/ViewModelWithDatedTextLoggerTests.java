package ru.unn.agile.ColorConverter.infrastructure;

import ru.unn.agile.ColorConverter.viewmodel.ViewModel;
import ru.unn.agile.ColorConverter.viewmodel.ViewModelTests;

public class ViewModelWithDatedTextLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        DatedTextLogger realLogger =
                new DatedTextLogger("./ViewModelWithDatedTextLoggerTests.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
