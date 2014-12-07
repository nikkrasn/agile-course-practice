package ru.unn.agile.Queue.viewmodel;

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
        assertEquals("", viewModel.getTxtToAdd());
        assertEquals("Empty", viewModel.getElement());
    }
    @Test

    public void isStatusWaitingWhenAddingWithEmptyField() {
        viewModel.add();
        assertEquals(State.AWAITING.toString(), viewModel.getState());
    }

    @Test
    public void isStatusReadyWhileFieldIsNotEmpty() {
        setInput("1");

        assertEquals(State.READY.toString(), viewModel.getState());
    }

    @Test
    public void canReportAboutBadFormat() {
        setInput("z");

        assertEquals(State.BAD_INPUT.toString(), viewModel.getState());
    }

    @Test
    public void areAddButtonsDisabledInitially() {
        assertTrue(viewModel.getIsAddingDisabled());
    }

    @Test
    public void areAddButtonsDisabledWhenFormatIsBad() {
        setInput("z");

        assertTrue(viewModel.getIsAddingDisabled());
    }

    @Test
    public void areAddButtonsEnabledWithCorrectInput() {
        setInput("1");

        assertFalse(viewModel.getIsAddingDisabled());
    }

    @Test
    public void isStatusEmptyIfToRemoveFromEmptyQueue() {
        viewModel.remove();
        assertEquals(State.EMPTY.toString(), viewModel.getState());
    }

    @Test
    public void canRemove() {
        String item = enqueueOne();

        viewModel.remove();

        assertEquals(item, viewModel.getTxtToAdd());
        assertEquals("Empty", viewModel.getElement());
    }

    private String enqueueOne() {
        String item = "1";
        setInput(item);
        viewModel.add();
        return item;
    }

    private void setInput(final String input) {
        viewModel.txtToAddProperty().set(input);
    }
}
