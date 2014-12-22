package ru.unn.agile.Vector3D.infrastructure;

import ru.unn.agile.Vector3D.viewmodel.ViewModel;
import ru.unn.agile.Vector3D.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./ViewModel_with_TxtLogger_Tests.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
