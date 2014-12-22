package ru.unn.agile.QuickSort.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel testViewModel;

    public void setExternalViewModel(final ViewModel testViewModel) {
        this.testViewModel = testViewModel;
    }

    @Before
    public void setUp() {
        if (testViewModel == null) {
            testViewModel = new ViewModel(new FakeLogger());
        }
    }

    @After
    public void tearDown() {
        testViewModel = null;
    }


    @Test
    public void ableToSetDefaultValues() {
        assertEquals("", testViewModel.sortedArrayProperty().get());
        assertEquals("", testViewModel.unsortedArrayProperty().get());
        assertEquals(Status.WAITING.toString(), testViewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenSortWithEmptyInput() {
        testViewModel.sort();
        assertEquals(Status.WAITING.toString(), testViewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenInputIsFilled() {
        setCorrectInputData();

        assertEquals(Status.READY.toString(), testViewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        testViewModel.unsortedArrayProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), testViewModel.statusProperty().get());
    }

    @Test
    public void sortButtonIsDisabledInitially() {
        assertTrue(testViewModel.sortingDisabledProperty().get());
    }

    @Test
    public void sortButtonIsDisabledWhenFormatIsBad() {
        testViewModel.unsortedArrayProperty().set("trash");

        assertTrue(testViewModel.sortingDisabledProperty().get());
    }

    @Test
    public void sortButtonIsEnabledWithCorrectInput() {
        setCorrectInputData();

        assertFalse(testViewModel.sortingDisabledProperty().get());
    }

    @Test
    public void sortingHasCorrectResult() {
        setCorrectInputData();
        testViewModel.sort();

        assertEquals("-234.5 -5.0 2.0 7.87 10.0", testViewModel.sortedArrayProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setCorrectInputData();

        testViewModel.sort();

        assertEquals(Status.SUCCESS.toString(), testViewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        testViewModel.unsortedArrayProperty().set("bad data");

        assertEquals(Status.BAD_FORMAT.toString(), testViewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setCorrectInputData();

        assertEquals(Status.READY.toString(), testViewModel.statusProperty().get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenViewModelCreatedWithNullLogger() {
        new ViewModel(null);
    }

    @Test
    public void logIsEmptyWhenStarted() {
        List<String> log = testViewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterCalculation() {
        setCorrectInputData();

        testViewModel.sort();
        String message = testViewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.SORT_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        setCorrectInputData();

        testViewModel.sort();

        String message = testViewModel.getLog().get(0);
        assertTrue(message.matches(".*" + "2.0 -5.0 7.87 -234.5 10.0" + ".*"));
    }

    @Test
    public void canAddSeveralLogMessages() {
        setCorrectInputData();

        testViewModel.sort();
        testViewModel.sort();
        testViewModel.sort();

        assertEquals(3, testViewModel.getLog().size());
    }

    @Test
    public void inputIsCorrectlyLogged() {
        setCorrectInputData();

        testViewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = testViewModel.getLog().get(0);
        String properMatch =  LogMessages.INPUT_EDITING_FINISHED
                + "Input Array: 2.0 -5.0 7.87 -234.5 10.0";
        assertTrue(message.matches(".*" + properMatch));
    }

    @Test
    public void sortIsNotCalledWhenButtonIsDisabled() {
        testViewModel.sort();

        assertTrue(testViewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwice() {
        setCorrectInputData();
        testViewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        setCorrectInputData();
        testViewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, testViewModel.getLog().size());
    }

    private void setCorrectInputData() {
        testViewModel.unsortedArrayProperty().set("2.0 -5.0 7.87 -234.5 10.0");
    }
}
