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
    public void canReportBadInputWithIncorrectValue() {
        viewModel.setTextInput("n");
        viewModel.calculate();
        assertEquals(Status.BAD_INPUT, viewModel.getStatus());
    }

    @Test
    public void canCalculateCorrectMatrix() {
        ViewModel view = new ViewModel();
        view.setTextInput("7 8\n-0.1 4");
        view.calculate();
        assertEquals(view.getResult(), "28.8");
    }

    @Test
    public void isStatusCalculatedIfCorrectInput() {
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
        ViewModel view = new ViewModel();
        view.setTextInput("    7  8   \n\n\n  -1          0.5    ");
        view.calculate();
        assertEquals(view.getResult(), "11.5");
    }

    @Test
    public void canConvertIncorrectStringToHigherOrderMatrix() {
        viewModel.setTextInput("  1   0 0 0\n\n    1 0 1 7\n 1 1 1 0  \n 0");
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
