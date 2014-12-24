package ru.unn.agile.Metrics.viewmodel;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Metrics.Model.Metrics;

import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel = new ViewModel();

    public void setLogger(final ILogger logger) {
        viewModel.setLogger(logger);
    }

    @Before
    public void setUp() {
        viewModel.setLogger(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("1", viewModel.getVectorsDimension());
        Components firstElement = viewModel.getVectorsValues().get(0);

        assertTrue(firstElement.equals(new Components("0.0f", "0.0f")));
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.getCurrentOperation());
        assertEquals("", viewModel.getMetricResult());
        assertEquals(CurrentStatus.READY.toString(), viewModel.getCurrentStatus());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.vectorsDimensionProperty().set("");
        viewModel.calculate();
        assertEquals(CurrentStatus.WAITING.toString(), viewModel.getCurrentStatus());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(CurrentStatus.READY.toString(), viewModel.getCurrentStatus());
    }

    @Test
    public void canReportBadFormatDimension() {
        setInputData();
        viewModel.vectorsDimensionProperty().set("invalid");

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.getCurrentStatus());
    }

    @Test
    public void canReportBadFormatVectorsValues() {
        setInputData();
        viewModel.getVectorsValues().set(0, new Components("invalid", "invalid"));

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.getCurrentStatus());
    }

    @Test
    public void calculateButtonIsDisabledWithIncorrectVectors() {
        setInputData();
        viewModel.getVectorsValues().set(0, new Components("invalid", "invalid"));

        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void isStatusBadFormatWithIncorrectVectors() {
        setInputData();
        viewModel.getVectorsValues().set(0, new Components("0.0f", "invalid"));

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.getCurrentStatus());
    }

    @Test
    public void statusIsReadyAfterInitialDimensionChange() {
        viewModel.vectorsDimensionProperty().set("1");

        assertEquals(CurrentStatus.READY.toString(), viewModel.getCurrentStatus());
    }

    @Test
    public void canChangeVectorsValuesByDimensionChange() {
        viewModel.vectorsDimensionProperty().set("1");
        Components firstElement = viewModel.getVectorsValues().get(0);

        assertTrue(firstElement.equals(new Components("0.0f", "0.0f")));

        viewModel.vectorsDimensionProperty().set("2");
        firstElement = viewModel.getVectorsValues().get(0);
        Components secondElement = viewModel.getVectorsValues().get(1);

        assertTrue(firstElement.equals(new Components("0.0f", "0.0f")));
        assertTrue(secondElement.equals(new Components("0.0f", "0.0f")));
    }

    @Test
    public void calculateButtonIsEnabledInitially() {
        assertFalse(viewModel.getCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.vectorsDimensionProperty().set("invalid");

        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        setInputData();
        viewModel.getVectorsValues().set(0, new Components("1.0", ""));

        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        viewModel.getVectorsValues().set(0, new Components("invalid", "invalid"));
        setInputData();

        assertFalse(viewModel.getCalculationDisabled());
    }

    @Test
    public void canSetL1Operation() {
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L1);
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.getCurrentOperation());
    }

    @Test
    public void isL1DefaultOperation() {
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.getCurrentOperation());
    }

    @Test
    public void hasL1OperationCorrectResult() {
        setInputData();

        viewModel.calculate();

        assertEquals("3.0", viewModel.getMetricResult());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(CurrentStatus.SUCCESS.toString(), viewModel.getCurrentStatus());
    }

    @Test
    public void canSetBadFormatMessage() {
        setInputData();
        viewModel.vectorsDimensionProperty().set("invalid");

        assertEquals(CurrentStatus.BAD_FORMAT.toString(), viewModel.getCurrentStatus());
    }

    @Test
    public void isReadyStatusWhenSetProperData() {
        setInputData();

        assertEquals(CurrentStatus.READY.toString(), viewModel.getCurrentStatus());
    }

    @Test
    public void hasL2OperationCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L2);

        viewModel.calculate();

        assertEquals("3.0", viewModel.getMetricResult());
    }

    @Test
    public void hasL3OperationCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L3);

        viewModel.calculate();

        assertEquals("3.0", viewModel.getMetricResult());
    }

    @Test
    public void hasL4OperationCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L4);

        viewModel.calculate();

        assertEquals("3.0", viewModel.getMetricResult());
    }

    @Test
    public void hasLInfOperationCorrectResult() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_LINF);

        viewModel.calculate();

        assertEquals("1.0", viewModel.getMetricResult());
    }

    @Test
    public void isLogEmptyInitially() {
        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void hasLogDimensionChangeMessage() {
        viewModel.vectorsDimensionProperty().set("2");
        assertEquals("Vectors Dimension changed to: 2", viewModel.getLogMessageText(0));
    }

    @Test
    public void hasLogMetricChangeMessage() {
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_L2);
        assertEquals("Metric changed to: L2", viewModel.getLogMessageText(0));
    }

    @Test
    public void hasLogCalculationMessage() {
        viewModel.calculate();
        assertEquals("Calculate L1 Metric for vectors:\n[0.0f]\n[0.0f]\nResult: 0.0",
                viewModel.getLogMessageText(0));
    }

    @Test
    public void hasLogVectorsChangeMessage() {
        viewModel.getVectorsValues().set(0, new Components("1.0f", "0.0f"));
        assertEquals("Vectors changed to: \n[1.0f]\n[0.0f]", viewModel.getLogMessageText(0));
    }

    @Test
    public void hasLogVectorsChangeMessageAfterDimensionChange() {
        viewModel.vectorsDimensionProperty().set("2");
        assertEquals("Vectors changed to: \n[0.0f,0.0f]\n[0.0f,0.0f]",
                viewModel.getLogMessageText(1));
    }

    @Test
    public void isCalculateMessageCorrectAfterParametersChange() {
        setInputData();
        viewModel.currentOperationProperty().set(Metrics.Operation.METRIC_LINF);

        viewModel.calculate();
        assertEquals("Calculate LINF Metric for vectors:\n[1.0f,2.0f,3.0f]\n[0.0f,1.0f,2.0f]\n"
                        + "Result: 1.0",
                viewModel.getLogMessageText(6));
    }

    private void setInputData() {
        viewModel.vectorsDimensionProperty().set("3");

        viewModel.getVectorsValues().set(0, new Components("1.0f", "0.0f"));
        viewModel.getVectorsValues().set(1, new Components("2.0f", "1.0f"));
        viewModel.getVectorsValues().set(2, new Components("3.0f", "2.0f"));
    }
}
