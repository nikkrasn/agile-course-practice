package ru.unn.agile.QuickSort.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeLogger());
        }
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

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterCalculation() {
        setCorrectInputData();
       
        viewModel.sort();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.SORT_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        setCorrectInputData();

        viewModel.sort();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + "2.0 -5.0 7.87 -234.5 10.0" + ".*"));
    }


    /*
    @Test
    public void argumentsInfoIssProperlyFormatted() {
        setCorrectInputData();

        viewModel.sort();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": Re1 = " + viewModel.re1Property().get()
                + "; Im1 = " + viewModel.im1Property().get()
                + "; Re2 = " + viewModel.re2Property().get()
                + "; Im2 = " + viewModel.im2Property().get() + ".*"));
    }

    @Test
    public void operationTypeIsMentionedInTheLog() {
        setCorrectInputData();

        viewModel.sort();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Add.*"));
    }
*/
    @Test
    public void canAddSeveralLogMessages() {
        setCorrectInputData();

        viewModel.sort();
        viewModel.sort();
        viewModel.sort();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void inputIsCorrectlyLogged() {
        setCorrectInputData();

        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        String match =  LogMessages.INPUT_EDITING_FINISHED
                + "Input Array: 2.0 -5 7.87 -234.5 10.0";
        assertTrue(message.matches(".*" + LogMessages.INPUT_EDITING_FINISHED
                + "Input Array: 2.0 -5.0 7.87 -234.5 10.0"));
    }

    @Test
    public void sortIsNotCalledWhenButtonIsDisabled() {
        viewModel.sort();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwice() {
        setCorrectInputData();
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        setCorrectInputData();
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }
    private void setCorrectInputData() {
        viewModel.unsortedArrayProperty().set("2.0 -5.0 7.87 -234.5 10.0");
    }
}
