package ru.unn.agile.Triangle.Infrastructure;

import ru.unn.agile.Triangle.viewmodel.ViewModel;
import ru.unn.agile.Triangle.viewmodel.ViewModelTest;

public class ViewModelWithRecordsLoggerTest extends ViewModelTest {
    @Override
    public void setUp() {
        RecordsLogger viewLogger =
            new RecordsLogger("./ViewModel_with_RecordsLogger_Test.log");
        super.setExternalViewModel(new ViewModel(viewLogger));
    }
}
