package ru.unn.agile.Dichotomy.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
//import ru.unn.agile.Dichotomy.Model.Dichotomy.Operation;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.stringArrayProperty().get());
        assertEquals("", viewModel.stringElementProperty().get());
    }
}
