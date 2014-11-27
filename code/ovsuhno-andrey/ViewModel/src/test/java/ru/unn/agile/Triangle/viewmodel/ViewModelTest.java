package ru.unn.agile.Triangle.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Triangle.Model.Triangle.Operation;

import static org.junit.Assert.*;

public class ViewModelTest {
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
        assertEquals("", viewModel.aXProperty().get());
        assertEquals("", viewModel.aYProperty().get());
        assertEquals("", viewModel.bXProperty().get());
        assertEquals("", viewModel.bYProperty().get());
        assertEquals("", viewModel.cXProperty().get());
        assertEquals("", viewModel.cYProperty().get());
        assertEquals(Operation.PERIMETER, viewModel.operationProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.compute();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.aXProperty().set("bad");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.aXProperty().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void computeButtonIsDisabledInitially() {
        assertTrue(viewModel.computationDisabledProperty().get());
    }

    @Test
    public void computeButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.aXProperty().set("bad");

        assertTrue(viewModel.computationDisabledProperty().get());
    }

    @Test
    public void computeButtonIsDisabledWithIncompleteInput() {
        viewModel.aXProperty().set("1");

        assertTrue(viewModel.computationDisabledProperty().get());
    }

    @Test
    public void computeButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.computationDisabledProperty().get());
    }

    @Test
    public void canSetPerimeterOperation() {
        viewModel.operationProperty().set(Operation.PERIMETER);
        assertEquals(Operation.PERIMETER, viewModel.operationProperty().get());
    }

    @Test
    public void perimeterIsDefaultOperation() {
        assertEquals(Operation.PERIMETER, viewModel.operationProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.compute();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.aXProperty().set("bad");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void perimeterComputationHasCorrectResult() {
        viewModel.aXProperty().set("1");
        viewModel.aYProperty().set("1");
        viewModel.bXProperty().set("1");
        viewModel.bYProperty().set("4");
        viewModel.cXProperty().set("2");
        viewModel.cYProperty().set("1");

        viewModel.compute();

        assertEquals("7.16", viewModel.resultProperty().get());
    }

    @Test
    public void lengthsComputationHasCorrectResult() {
        viewModel.aXProperty().set("1");
        viewModel.aYProperty().set("2");
        viewModel.bXProperty().set("1");
        viewModel.bYProperty().set("4");
        viewModel.cXProperty().set("2");
        viewModel.cYProperty().set("2");
        viewModel.operationProperty().set(Operation.LENGTHS);

        viewModel.compute();

        assertEquals("2.00 2.24 1.00", viewModel.resultProperty().get());
    }

    @Test
    public void spaceComputationHasCorrectResult() {
        viewModel.aXProperty().set("1");
        viewModel.aYProperty().set("1");
        viewModel.bXProperty().set("1");
        viewModel.bYProperty().set("3");
        viewModel.cXProperty().set("2");
        viewModel.cYProperty().set("1");
        viewModel.operationProperty().set(Operation.SPACE);

        viewModel.compute();

        assertEquals("1.00", viewModel.resultProperty().get());
    }

    @Test
    public void anglesComputationHasCorrectResult() {
        viewModel.aXProperty().set("1");
        viewModel.aYProperty().set("1");
        viewModel.bXProperty().set("1");
        viewModel.bYProperty().set("4");
        viewModel.cXProperty().set("4");
        viewModel.cYProperty().set("1");
        viewModel.operationProperty().set(Operation.ANGLES);

        viewModel.compute();

        assertEquals("0.00 0.71 0.71", viewModel.resultProperty().get());
    }

    private void setInputData() {
        viewModel.aXProperty().set("1");
        viewModel.aYProperty().set("2");
        viewModel.bXProperty().set("3");
        viewModel.bYProperty().set("4");
        viewModel.cXProperty().set("3");
        viewModel.cYProperty().set("2");
    }
}
