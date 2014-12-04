package ru.unn.agile.Metrics.viewmodel;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Metrics.Model.Metrics;

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
        assertEquals("1", viewModel.vectorsDimensionProperty().get());
        assertTrue(viewModel.vectorsValuesProperty().get().get(0).equals(
                new Components("0.0f", "0.0f")));
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.currentOperationProperty().get());
        assertEquals("", viewModel.metricResultProperty().get());
        assertEquals(CurrentStatus.READY.toString(), viewModel.currentStatusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.vectorsDimensionProperty().set("");
        viewModel.calculate();
        assertEquals(CurrentStatus.WAITING.toString(), viewModel.currentStatusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(CurrentStatus.READY.toString(), viewModel.currentStatusProperty().get());
    }

    @Test
    public void canReportBadFormatDimension() {
        setInputData();
        viewModel.vectorsDimensionProperty().set("invalid");

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.currentStatusProperty().get());
    }

    @Test
    public void canReportBadFormatVectorsValues() {
        setInputData();
        viewModel.vectorsValuesProperty().get().set(0, new Components("invalid", "invalid"));

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.currentStatusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncorrectVectors() {
        setInputData();
        viewModel.vectorsValuesProperty().get().set(0, new Components("invalid", "invalid"));

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void isBadStatusWithIncorrectVectors() {
        setInputData();
        viewModel.vectorsValuesProperty().get().set(0, new Components("0.0f", "invalid"));

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.currentStatusProperty().get());
    }

    @Test
    public void statusIsReadyAfterInitialDimensionChange() {
        viewModel.vectorsDimensionProperty().set("1");

        assertEquals(CurrentStatus.READY.toString(), viewModel.currentStatusProperty().get());
    }

    @Test
    public void canChangeVectorsValuesByDimensionChange() {
        viewModel.vectorsDimensionProperty().set("1");

        assertTrue(viewModel.vectorsValuesProperty().get().get(0).equals(
                new Components("0.0f", "0.0f")));

        viewModel.vectorsDimensionProperty().set("2");

        assertTrue(viewModel.vectorsValuesProperty().get().get(0).equals(
                new Components("0.0f", "0.0f")));
        assertTrue(viewModel.vectorsValuesProperty().get().get(1).equals(
                new Components("0.0f", "0.0f")));
    }

    @Test
    public void calculateButtonIsEnabledInitially() {
        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.vectorsDimensionProperty().set("invalid");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        setInputData();
        viewModel.vectorsValuesProperty().get().set(0, new Components("1.0", ""));

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        viewModel.vectorsValuesProperty().get().set(0, new Components("invalid", "invalid"));
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetL1Operation() {
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L1);
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.currentOperationProperty().get());
    }

    @Test
    public void l1IsDefaultOperation() {
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.currentOperationProperty().get());
    }

    @Test
    public void operationL1HasCorrectResult() {
        setInputData();

        viewModel.calculate();

        assertEquals("3.0", viewModel.metricResultProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(CurrentStatus.SUCCESS.toString(), viewModel.currentStatusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        setInputData();
        viewModel.vectorsDimensionProperty().set("invalid");

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.currentStatusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(CurrentStatus.READY.toString(), viewModel.currentStatusProperty().get());
    }

    @Test
    public void operationL2HasCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L2);

        viewModel.calculate();

        assertEquals("3.0", viewModel.metricResultProperty().get());
    }

    @Test
    public void operationL3HasCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L3);

        viewModel.calculate();

        assertEquals("3.0", viewModel.metricResultProperty().get());
    }

    @Test
    public void operationL4HasCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L4);

        viewModel.calculate();

        assertEquals("3.0", viewModel.metricResultProperty().get());
    }

    @Test
    public void operationLInfHasCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_LINF);

        viewModel.calculate();

        assertEquals("1.0", viewModel.metricResultProperty().get());
    }

    private void setInputData() {
        viewModel.vectorsDimensionProperty().set("3");

        viewModel.vectorsValuesProperty().get().set(0, new Components("1.0f", "0.0f"));
        viewModel.vectorsValuesProperty().get().set(1, new Components("2.0f", "1.0f"));
        viewModel.vectorsValuesProperty().get().set(2, new Components("3.0f", "2.0f"));
    }
}
