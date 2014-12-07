package ru.unn.agile.Deque.viewmodel;

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
        assertEquals("", viewModel.getTxtItem());
    }

    @Test
    public void isStatusWaitingWhenAddingWithEmptyField() {
        viewModel.addFirst();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWithNotEmptyField() {
        setInputData("1");

        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        setInputData("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
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
    public void isStatusEmptyIfToGetFromEmptyDeque() {
        viewModel.getFirst();
        assertEquals(Status.EMPTY.toString(), viewModel.getStatus());
    }

    @Test
    public void canGetFirst() {
        String item = addOneAsFirst();

        viewModel.getFirst();

        assertEquals(item, viewModel.getTxtItem());
    }

    @Test
    public void canGetLast() {
        String item = addOneAsLast();

        viewModel.getLast();

        assertEquals(item, viewModel.getTxtItem());
    }

    @Test
    public void canRemoveFirst() {
        String item = addOneAsFirst();

        viewModel.removeFirst();

        assertEquals(item, viewModel.getTxtItem());
        assertTrue(viewModel.isDequeEmpty());
    }

    @Test
    public void canRemoveLast() {
        String item = addOneAsLast();

        viewModel.removeLast();

        assertEquals(item, viewModel.getTxtItem());
        assertTrue(viewModel.isDequeEmpty());
    }

    private String addOneAsFirst() {
        String item = "1";
        setInputData(item);
        viewModel.addFirst();
        return item;
    }

    private String addOneAsLast() {
        String item = "1";
        setInputData(item);
        viewModel.addLast();
        return item;
    }

    private void setInputData(final String input) {
        viewModel.txtItemProperty().set(input);
    }
}
