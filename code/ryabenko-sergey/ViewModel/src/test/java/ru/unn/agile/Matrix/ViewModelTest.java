package ru.unn.agile.Matrix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Matrix.ViewModel.Status;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;

    @Before
    public void up() {
        viewModel = new ViewModel();
    }
    @After
    public void down() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getTextInput());
        assertEquals("", viewModel.getResult());
        assertEquals(Status.EMPTY_INPUT, viewModel.getStatus());
    }

    @Test
    public void isStatusEmptyInTheBeginning() {
        assertEquals(Status.EMPTY_INPUT, viewModel.getStatus());
    }

    @Test
    public void isStatusEmptyIfStringEmpty() {
        viewModel.calculate();
        assertEquals(Status.EMPTY_INPUT, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenArrayIsCorrect() {
        viewModel.setTextInput("1");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);
        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusEmptyWhenArrayIsNull() {
        viewModel.setTextInput("");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);
        assertEquals(Status.EMPTY_INPUT, viewModel.getStatus());
    }

    @Test
    public void isStatusBadWhenArrayIsIncorrect() {
        viewModel.setTextInput("n");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);
        assertEquals(Status.BAD_INPUT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsTrue() {
        viewModel.setTextInput("1.0a \n 7");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);
        viewModel.setTextInput("1.0 \n 7");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);
        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsFalse() {
        viewModel.setTextInput("1.0 \n 7");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);
        viewModel.setTextInput("1.0a \n 7");
        viewModel.processKeyInTextField(viewModel.ANY_KEY);
        assertEquals(Status.BAD_INPUT, viewModel.getStatus());
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
        assertEquals(Status.CALCULATED, viewModel.getStatus());
    }

    @Test
    public void canReportBadInputWithShiftKey() {
        viewModel.setTextInput("n");
        viewModel.processKeyInTextField(viewModel.SHIFT);
        assertEquals(Status.BAD_INPUT, viewModel.getStatus());
    }

    @Test
    public void isStatusCalculatedIfWasCorrectInput() {
        viewModel.setTextInput("7 8\n-0.1 4");
        viewModel.calculate();
        assertEquals(Status.CALCULATED, viewModel.getStatus());
    }

    @Test
    public void canReportBadInputWithNonSquareMatrix() {
        viewModel.setTextInput("7 8 0\n-0.1 4 0");
        viewModel.calculate();
        assertEquals(Status.BAD_INPUT, viewModel.getStatus());
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
        assertEquals(Status.BAD_INPUT, viewModel.getStatus());
    }

    @Test
    public void canConvertIncorrectStringToArrayWithIntegerValues() {
        viewModel.setTextInput("  -1   0 0 0\n\n    1 0 1 7\n 1 1 1 0  \n 0");
        viewModel.calculate();
        assertEquals(Status.CALCULATED, viewModel.getStatus());
    }

    @Test
    public void canConvertIncorrectStringToArrayWithDoubleValues() {
        viewModel.setTextInput("  1.0   0.8 0.8 -0.7\n\n    1.8 0.6 1.5 7.868\n 1.65 1.4 1.4 0.6"
               + "  \n 0.7 7.5 1.2 1.5");
        viewModel.calculate();
        assertEquals(Status.CALCULATED, viewModel.getStatus());
    }

    @Test
    public void isStatusEmptyIfStringWithNewLinesOnly() {
        viewModel.setTextInput("\n\n\n");
        viewModel.calculate();
        assertEquals(Status.EMPTY_INPUT, viewModel.getStatus());
    }

    @Test
    public void isStatusEmptyIfStringWithIndentsOnly() {
        viewModel.setTextInput("              ");
        viewModel.calculate();
        assertEquals(Status.EMPTY_INPUT, viewModel.getStatus());
    }

}
