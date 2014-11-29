package ru.unn.agile.Stack.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @Test
    public void checkDefaultValues() {
        assertEquals("", viewModel.topProperty().get());
        assertEquals("Push me!", viewModel.pushProperty().get());
        assertFalse(viewModel.isEmptyProperty().get());
    }
}
