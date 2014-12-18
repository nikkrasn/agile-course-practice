package ru.unn.agile.Matrix;

public class ViewModelWithTxtLoggerTests extends ViewModelTest {
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
