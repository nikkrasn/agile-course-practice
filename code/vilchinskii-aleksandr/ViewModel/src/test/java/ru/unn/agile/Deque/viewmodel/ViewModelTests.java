package ru.unn.agile.Deque.viewmodel;

import ru.unn.agile.Deque.viewmodel.ViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void canSetDefaultValue() {
        assertEquals("", viewModel.txtValueProperty().get());
    }

    @Test
    public void statusIsWaitingWhenAddingWithEmptyField() {
        viewModel.addFirst();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWithNotEmptyField() {
        viewModel.txtValueProperty().set("1");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }
}
