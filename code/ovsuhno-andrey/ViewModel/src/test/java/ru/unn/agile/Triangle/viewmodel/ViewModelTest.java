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
        assertEquals("", viewModel.valuesProperty().get());
        assertEquals(CurrentStatus.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenComputeWithEmptyFields() {
        viewModel.compute();
        assertEquals(CurrentStatus.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setTestData();

        assertEquals(CurrentStatus.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.aXProperty().set("bad");

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.aXProperty().set("0");

        assertEquals(CurrentStatus.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void computeButtonIsDisabledInitially() {
        assertTrue(viewModel.computationDisabledProperty().get());
    }

    @Test
    public void computeButtonIsDisabledWhenFormatIsBad() {
        setTestData();
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
        setTestData();

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
        setTestData();

        viewModel.compute();

        assertEquals(CurrentStatus.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.aXProperty().set("bad");

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setTestData();

        assertEquals(CurrentStatus.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void perimeterComputationHasCorrectValues() {
        viewModel.aXProperty().set("2");
        viewModel.aYProperty().set("2");
        viewModel.bXProperty().set("4");
        viewModel.bYProperty().set("4");
        viewModel.cXProperty().set("4");
        viewModel.cYProperty().set("2");

        viewModel.compute();

        assertEquals("6.83", viewModel.valuesProperty().get());
    }

    @Test
    public void lengthsComputationHasCorrectValues() {
        viewModel.aXProperty().set("1");
        viewModel.aYProperty().set("2");
        viewModel.bXProperty().set("1");
        viewModel.bYProperty().set("4");
        viewModel.cXProperty().set("2");
        viewModel.cYProperty().set("2");
        viewModel.operationProperty().set(Operation.LENGTHS);

        viewModel.compute();

        assertEquals("2.00 2.24 1.00", viewModel.valuesProperty().get());
    }

    @Test
    public void spaceComputationHasCorrectValues() {
        viewModel.aXProperty().set("1");
        viewModel.aYProperty().set("1");
        viewModel.bXProperty().set("1");
        viewModel.bYProperty().set("3");
        viewModel.cXProperty().set("2");
        viewModel.cYProperty().set("1");
        viewModel.operationProperty().set(Operation.SPACE);

        viewModel.compute();

        assertEquals("1.00", viewModel.valuesProperty().get());
    }

    @Test
    public void anglesComputationHasCorrectValues() {
        viewModel.aXProperty().set("1");
        viewModel.aYProperty().set("1");
        viewModel.bXProperty().set("1");
        viewModel.bYProperty().set("4");
        viewModel.cXProperty().set("4");
        viewModel.cYProperty().set("1");
        viewModel.operationProperty().set(Operation.ANGLES);

        viewModel.compute();

        assertEquals("0.00 0.71 0.71", viewModel.valuesProperty().get());
    }

    private void setTestData() {
        viewModel.aXProperty().set("1");
        viewModel.aYProperty().set("2");
        viewModel.bXProperty().set("3");
        viewModel.bYProperty().set("4");
        viewModel.cXProperty().set("3");
        viewModel.cYProperty().set("2");
    }
}
