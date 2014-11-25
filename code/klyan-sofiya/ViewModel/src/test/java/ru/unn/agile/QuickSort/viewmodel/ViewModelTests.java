package ru.unn.agile.QuickSort.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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
        assertEquals("", viewModel.sortedArrayProperty().get());
        assertEquals("", viewModel.unsortedArrayProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenSortWithEmptyInput() {
        viewModel.sort();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenInputIsFilled() {
        setCorrectInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.unsortedArrayProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }
    
    @Test
    public void sortButtonIsDisabledInitially() {
        assertTrue(viewModel.sortingDisabledProperty().get());
    }

    @Test
    public void sortButtonIsDisabledWhenFormatIsBad() {
        
        viewModel.unsortedArrayProperty().set("trash");

        assertTrue(viewModel.sortingDisabledProperty().get());
    }
    
    @Test
    public void sortButtonIsEnabledWithCorrectInput() {
        setCorrectInputData();

        assertFalse(viewModel.sortingDisabledProperty().get());
    }

    @Test
    public void sortingHasCorrectResult() {
        setCorrectInputData();
        viewModel.sort();

        assertEquals("-234.5 -5.0 2.0 7.87 10.0", viewModel.sortedArrayProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setCorrectInputData();

        viewModel.sort();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.unsortedArrayProperty().set("bad data");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setCorrectInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    private void setCorrectInputData() {
        viewModel.unsortedArrayProperty().set("2 -5 7.87 -234.5 10");
    }
}
