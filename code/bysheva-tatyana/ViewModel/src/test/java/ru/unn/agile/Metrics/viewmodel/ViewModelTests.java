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
        assertEquals("1", viewModel.getVectorsDimensionProperty());
        Components firstElement = viewModel.getVectorsValuesProperty().get(0);

        assertTrue(firstElement.equals(new Components("0.0f", "0.0f")));
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.getCurrentOperationProperty());
        assertEquals("", viewModel.getMetricResultProperty());
        assertEquals(CurrentStatus.READY.toString(), viewModel.getCurrentStatusProperty());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.vectorsDimensionProperty().set("");
        viewModel.calculate();
        assertEquals(CurrentStatus.WAITING.toString(), viewModel.getCurrentStatusProperty());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(CurrentStatus.READY.toString(), viewModel.getCurrentStatusProperty());
    }

    @Test
    public void canReportBadFormatDimension() {
        setInputData();
        viewModel.vectorsDimensionProperty().set("invalid");

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.getCurrentStatusProperty());
    }

    @Test
    public void canReportBadFormatVectorsValues() {
        setInputData();
        viewModel.getVectorsValuesProperty().set(0, new Components("invalid", "invalid"));

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.getCurrentStatusProperty());
    }

    @Test
    public void calculateButtonIsDisabledWithIncorrectVectors() {
        setInputData();
        viewModel.getVectorsValuesProperty().set(0, new Components("invalid", "invalid"));

        assertTrue(viewModel.getCalculationDisabledProperty());
    }

    @Test
    public void isStatusBadFormatWithIncorrectVectors() {
        setInputData();
        viewModel.getVectorsValuesProperty().set(0, new Components("0.0f", "invalid"));

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.getCurrentStatusProperty());
    }

    @Test
    public void statusIsReadyAfterInitialDimensionChange() {
        viewModel.vectorsDimensionProperty().set("1");

        assertEquals(CurrentStatus.READY.toString(), viewModel.getCurrentStatusProperty());
    }

    @Test
    public void canChangeVectorsValuesByDimensionChange() {
        viewModel.vectorsDimensionProperty().set("1");
        Components firstElement = viewModel.getVectorsValuesProperty().get(0);

        assertTrue(firstElement.equals(new Components("0.0f", "0.0f")));

        viewModel.vectorsDimensionProperty().set("2");
        firstElement = viewModel.getVectorsValuesProperty().get(0);
        Components secondElement = viewModel.getVectorsValuesProperty().get(1);

        assertTrue(firstElement.equals(new Components("0.0f", "0.0f")));
        assertTrue(secondElement.equals(new Components("0.0f", "0.0f")));
    }

    @Test
    public void calculateButtonIsEnabledInitially() {
        assertFalse(viewModel.getCalculationDisabledProperty());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.vectorsDimensionProperty().set("invalid");

        assertTrue(viewModel.getCalculationDisabledProperty());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        setInputData();
        viewModel.getVectorsValuesProperty().set(0, new Components("1.0", ""));

        assertTrue(viewModel.getCalculationDisabledProperty());
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        viewModel.getVectorsValuesProperty().set(0, new Components("invalid", "invalid"));
        setInputData();

        assertFalse(viewModel.getCalculationDisabledProperty());
    }

    @Test
    public void canSetL1Operation() {
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L1);
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.getCurrentOperationProperty());
    }

    @Test
    public void isL1DefaultOperation() {
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.getCurrentOperationProperty());
    }

    @Test
    public void hasL1OperationCorrectResult() {
        setInputData();

        viewModel.calculate();

        assertEquals("3.0", viewModel.getMetricResultProperty());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(CurrentStatus.SUCCESS.toString(), viewModel.getCurrentStatusProperty());
    }

    @Test
    public void canSetBadFormatMessage() {
        setInputData();
        viewModel.vectorsDimensionProperty().set("invalid");

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.getCurrentStatusProperty());
    }

    @Test
    public void isReadyStatusWhenSetProperData() {
        setInputData();

        assertEquals(CurrentStatus.READY.toString(), viewModel.getCurrentStatusProperty());
    }

    @Test
    public void hasL2OperationCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L2);

        viewModel.calculate();

        assertEquals("3.0", viewModel.getMetricResultProperty());
    }

    @Test
    public void hasL3OperationCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L3);

        viewModel.calculate();

        assertEquals("3.0", viewModel.getMetricResultProperty());
    }

    @Test
    public void hasL4OperationCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L4);

        viewModel.calculate();

        assertEquals("3.0", viewModel.getMetricResultProperty());
    }

    @Test
    public void hasLInfOperationCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_LINF);

        viewModel.calculate();

        assertEquals("1.0", viewModel.getMetricResultProperty());
    }

    private void setInputData() {
        viewModel.vectorsDimensionProperty().set("3");

        viewModel.getVectorsValuesProperty().set(0, new Components("1.0f", "0.0f"));
        viewModel.getVectorsValuesProperty().set(1, new Components("2.0f", "1.0f"));
        viewModel.getVectorsValuesProperty().set(2, new Components("3.0f", "2.0f"));
    }
}
