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
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isStatusEmptyInTheBeginning() {
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
        viewModel.processKeyInTextField(12345);
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
    public void canCalculateCorrectMatrixWithShiftKey() {
        viewModel.setTextInput("1");
        viewModel.processKeyInTextField(16);
        assertEquals("1.0", viewModel.getResult());
    }

    @Test
    public void canGetEmptyStatusWithShiftKey() {
        viewModel.processKeyInTextField(viewModel.SHIFT);
        assertTrue(Status.EMPTY_INPUT == viewModel.getStatus()
                && viewModel.getResult() ==  "");
    }

    @Test
    public void canReportBadInputWithShiftKey() {
        viewModel.setTextInput("n");
        viewModel.processKeyInTextField(viewModel.SHIFT);
        assertTrue(Status.BAD_INPUT == viewModel.getStatus()
                && viewModel.getResult() ==  "");
    }

    @Test
    public void isStatusEmptyIfStringEmpty() {
        viewModel.calculate();
        assertTrue(Status.EMPTY_INPUT == viewModel.getStatus()
                && viewModel.getResult() == "");
    }

    @Test
    public void canCalculateCorrectMatrix() {
        viewModel.setTextInput("7 8\n-0.1 4");
        viewModel.calculate();
        assertEquals("28.8", viewModel.getResult());
    }

    @Test
    public void isStatusCalculatedIfStringIsCorrect() {
        viewModel.setTextInput("7 8\n-0.1 4");
        viewModel.calculate();
        assertTrue(Status.CALCULATED == viewModel.getStatus());
    }

    @Test
    public void canReportBadInputWithNonSquareMatrix() {
        viewModel.setTextInput("7 8 0\n-0.1 4 0");
        viewModel.calculate();
        assertTrue(Status.BAD_INPUT == viewModel.getStatus()
                && viewModel.getResult() ==  "");
    }

    @Test
    public void canConvertIncorrectStringToArray() {
        viewModel.setTextInput("    7  8   \n\n\n  -1          0.5    ");
        viewModel.calculate();
        assertEquals("11.5", viewModel.getResult());
    }

    @Test
    public void isIncorrectStringIfEmptyLineWithIndent() {
        viewModel.setTextInput("    7  8   \n \n\n  -1          0.5    ");
        viewModel.calculate();
        assertTrue(Status.BAD_INPUT == viewModel.getStatus()
                && viewModel.getResult() ==  "");
    }

    @Test
    public void isStatusEmptyIfStringWithNewLinesOnly() {
        viewModel.setTextInput("\n\n\n");
        viewModel.calculate();
        assertTrue(Status.EMPTY_INPUT == viewModel.getStatus()
                && viewModel.getResult() ==  "");
    }

    @Test
    public void isStatusEmptyIfStringWithIndentsOnly() {
        viewModel.setTextInput("              ");
        viewModel.calculate();
        assertTrue(Status.EMPTY_INPUT == viewModel.getStatus()
                && viewModel.getResult() ==  "");
    }

}
