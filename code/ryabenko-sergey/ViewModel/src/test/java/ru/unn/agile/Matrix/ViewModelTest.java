package ru.unn.agile.Matrix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Matrix.ViewModel.SystemMessages;
import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void up() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }
    @After
    public void down() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getTextInput());
        assertEquals("", viewModel.getResult());

        assertEquals(SystemMessages.EMPTY_INPUT.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusEmptyInTheBeginning() {
        assertEquals(SystemMessages.EMPTY_INPUT.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusEmptyIfStringEmpty() {
        viewModel.calculate();

        assertEquals(SystemMessages.EMPTY_INPUT.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenArrayIsCorrect() {
        viewModel.setTextInput("1");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);

        assertEquals(SystemMessages.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusEmptyWhenArrayIsNull() {
        viewModel.setTextInput("");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);

        assertEquals(SystemMessages.EMPTY_INPUT.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusBadWhenArrayIsIncorrect() {
        viewModel.setTextInput("n");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);

        assertEquals(SystemMessages.BAD_INPUT.toString(), viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsTrue() {
        viewModel.setTextInput("1.0a \n 7");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);
        viewModel.setTextInput("1.0 \n 7");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);

        assertEquals(SystemMessages.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsFalse() {
        viewModel.setTextInput("1.0 \n 7");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);
        viewModel.setTextInput("1.0a \n 7");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);

        assertEquals(SystemMessages.BAD_INPUT.toString(), viewModel.getStatus());
    }

    @Test
    public void isCalculateButtonDisabledInitially() {
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonEnabledIfInputIsCorrect() {
        viewModel.setTextInput("1.0 \n 7");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);

        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledIfMatrixNonSquare() {
        viewModel.setTextInput("1.0 -7");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);

        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledIfInputIsIncorrect() {
        viewModel.setTextInput("n");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);

        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void canCalculateCorrectMatrix() {
        viewModel.setTextInput("7 8\n-0.1 4");
        viewModel.calculate();

        assertEquals(viewModel.getResult(), "28.8");
    }

    @Test
    public void canCalculateCorrectMatrixWithShiftKey() {
        viewModel.setTextInput("1");
        viewModel.processKeyInTextField(viewModel.SHIFT);

        assertEquals(SystemMessages.CALCULATED.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadInputWithShiftKey() {
        viewModel.setTextInput("n");
        viewModel.processKeyInTextField(viewModel.SHIFT);

        assertEquals(SystemMessages.BAD_INPUT.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusCalculatedIfWasCorrectInput() {
        viewModel.setTextInput("7 8\n-0.1 4");
        viewModel.calculate();

        assertEquals(SystemMessages.CALCULATED.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadInputWithNonSquareMatrix() {
        viewModel.setTextInput("7 8 0\n-0.1 4 0");
        viewModel.calculate();

        assertEquals(SystemMessages.BAD_INPUT.toString(), viewModel.getStatus());
    }

    @Test
    public void canConvertIncorrectStringToArray() {
        viewModel.setTextInput("    7  8   \n\n\n  -1          0.5    ");
        viewModel.calculate();

        assertEquals(viewModel.getResult(), "11.5");
    }

    @Test
    public void isIncorrectStringIfEmptyLineWithIndent() {
        viewModel.setTextInput("    7  8   \n \n\n  -1          0.5    ");
        viewModel.calculate();

        assertEquals(SystemMessages.BAD_INPUT.toString(), viewModel.getStatus());
    }

    @Test
    public void canConvertIncorrectStringToArrayWithIntegerValues() {
        viewModel.setTextInput("  -1   0 0 0\n\n    1 0 1 7\n 1 1 1 0  \n 0");
        viewModel.calculate();

        assertEquals(SystemMessages.CALCULATED.toString(), viewModel.getStatus());
    }

    @Test
    public void canConvertIncorrectStringToArrayWithDoubleValues() {
        viewModel.setTextInput("  1.0   0.8 0.8 -0.7\n\n    1.8 0.6 1.5 7.868\n 1.65 1.4 1.4 0.6"
               + "  \n 0.7 7.5 1.2 1.5");
        viewModel.calculate();

        assertEquals(SystemMessages.CALCULATED.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusEmptyIfStringWithNewLinesOnly() {
        viewModel.setTextInput("\n\n\n");
        viewModel.calculate();

        assertEquals(SystemMessages.EMPTY_INPUT.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusEmptyIfStringWithIndentsOnly() {
        viewModel.setTextInput("              ");
        viewModel.calculate();

        assertEquals(SystemMessages.EMPTY_INPUT.toString(), viewModel.getStatus());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);
        assertNotNull(viewModelLogged);
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
    public void startingWithLogEmpty() {
        List<String> log = viewModel.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void updateLogWhenCalculateWasPressed() {
        viewModel.calculate();

        List<String> log = viewModel.getLog();
        assertNotEquals(1, log.size());
    }

    @Test
    public void updateLogWhenFocusLost() {
        viewModel.setTextInput("7 8\n-0.1 4");
        viewModel.focusLost();
        viewModel.setTextInput("");
        viewModel.focusLost();

        String message = viewModel.getLog().get(1);
        assertTrue(message.matches(SystemMessages.UPDATE_INPUT + "\n"
                + SystemMessages.INVALID_INPUT));
    }

    @Test
    public void isCalculateLogMessagesIfInputCorrectly() {
        viewModel.setTextInput("7 8\n-0.1 4");
        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.equals(viewModel.getCalculateLogMessage()));
    }

    @Test
    public void isUpdatedInputLogMessagesIfInputIncorrectly() {
        viewModel.setTextInput("7 8 9\n-0.1 4");
        viewModel.calculate();
        viewModel.focusLost();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches("Updated input." + "\nMatrix is invalid."));
    }

    @Test
    public void canPutSeveralLogMessages() {
        viewModel.setTextInput("7 8\n-0.1 4");
        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void isUpdatedInputLogged() {
        viewModel.setTextInput("");
        viewModel.focusLost();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(SystemMessages.UPDATE_INPUT + "\n"
                + SystemMessages.INVALID_INPUT));
    }

    @Test
    public void isInputCorrectlyLoggedOnUpdatedInput() {
        viewModel.setTextInput("1");
        viewModel.focusLost();

        String message = viewModel.getLog().get(0);
        assertTrue(message.equals(viewModel.getUpdateLogMessage()));
    }

    @Test
    public void isLogInputsCalledOnShift() {
        viewModel.setTextInput("1");
        viewModel.processKeyInTextField(ViewModel.SHIFT);

        String message = viewModel.getLog().get(0);
        assertTrue(message.equals(viewModel.getCalculateLogMessage()));
    }

    @Test
    public void calculateIsNotCalledWhenButtonIsDisabled() {
        viewModel.calculate();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogInputTwiceWithPartialInput() {
        viewModel.setTextInput("7 8\n-0.1 4");
        viewModel.focusLost();
        viewModel.setTextInput("7 8\n-0.1 4");
        viewModel.focusLost();
        viewModel.setTextInput("7 8\n-0.1 4");
        viewModel.focusLost();

        assertEquals(1, viewModel.getLog().size());
    }

}
