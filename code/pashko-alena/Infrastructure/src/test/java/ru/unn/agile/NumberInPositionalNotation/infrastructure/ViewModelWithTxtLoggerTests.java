package ru.unn.agile.NumberInPositionalNotation.infrastructure;

import ru.unn.agile.NumberInPositionalNotation.viewmodel.ViewModel;
import ru.unn.agile.NumberInPositionalNotation.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
                new TxtLogger("./Tests_view_model_with_txt_logger.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
