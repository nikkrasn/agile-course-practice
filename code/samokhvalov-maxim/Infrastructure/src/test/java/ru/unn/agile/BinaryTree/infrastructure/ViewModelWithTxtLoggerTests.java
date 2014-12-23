package ru.unn.agile.BinaryTree.infrastructure;

import ru.unn.agile.BinaryTree.viewmodel.ViewModel;
import ru.unn.agile.BinaryTree.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger mainLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new ViewModel(mainLogger));
    }
}
