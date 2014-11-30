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
        setInputData("1");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        setInputData("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void areAddButtonsDisabledInitially() {
        assertTrue(viewModel.getIsAddingDisabled());
    }

    @Test
    public void areAddButtonsDisabledWhenFormatIsBad() {
        setInputData("a");

        assertTrue(viewModel.getIsAddingDisabled());
    }

    @Test
    public void areAddButtonsEnabledWithCorrectInput() {
        setInputData("1");

        assertFalse(viewModel.getIsAddingDisabled());
    }

    @Test
    public void areGetButtonsDisabledInitially() {
        assertTrue(viewModel.getIsGettingDisabled());
    }

    @Test
    public void areGetButtonsEnabledWhenDequeIsNotEmpty() {
        setInputData("1");

        viewModel.addFirst();

        assertFalse(viewModel.getIsGettingDisabled());
    }

    private void setInputData(String input) {
        viewModel.txtValueProperty().set(input);
    }
}
