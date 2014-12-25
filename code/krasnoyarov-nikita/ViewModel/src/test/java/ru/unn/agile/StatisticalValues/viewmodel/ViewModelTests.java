package ru.unn.agile.StatisticalValues.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.StatisticalValues.model.Operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        setUpViewModelAndLogger(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        ProbabilityValuePair first = viewModel.getProbabilityValuePair().get(0);

        assertEquals("1", viewModel.getVectDimension());
        assertTrue(first.equals(new ProbabilityValuePair("0.0", "0.0")));
        assertEquals(Operation.EXPECTED_VALUE, viewModel.operationProperty().get());
        assertEquals("", viewModel.getResultProperty().get());
        assertEquals(Status.READY.toString(), viewModel.getOperationStatusProperty().get());
    }

    @Test
    public void checkStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.vectDimensionProperty().set("");
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), viewModel.getOperationStatusProperty().get());
    }

    @Test
    public void checkStatusReadyWhenFieldsAreFill() {
        setInput();

        assertEquals(Status.READY.toString(), viewModel.getOperationStatusProperty().get());
    }

    @Test
    public void checkBadFormat() {
        viewModel.getProbabilityValuePair().set(0, new ProbabilityValuePair("0.5", "ssa"));

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getOperationStatusProperty().get());
    }

    @Test
    public void checkBadFormatNotAllParamsEntered() {
        setInput();
        viewModel.getProbabilityValuePair().set(0, new ProbabilityValuePair("0.5", ""));

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getOperationStatusProperty().get());
    }

    @Test
    public void checkCalculationEnabledForFirstTime() {
        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void checkCalculationDisabledWithWrongParams() {
        viewModel.getProbabilityValuePair().set(0, new ProbabilityValuePair("0.5", "asadada"));

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void checkCalculationWithIncompleteInput() {
        viewModel.getProbabilityValuePair().set(0, new ProbabilityValuePair("0.5", ""));

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void checkCalculationWithCorrectInput() {
        setInput();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void checkCanSetVarianceOperation() {
        viewModel.operationProperty().set(Operation.VARIANCE);

        assertEquals(Operation.VARIANCE, viewModel.operationProperty().get());
    }

    @Test
    public void checkCanCalculateAndSetSuccessStatus() {
        setInput();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.getOperationStatusProperty().get());
    }

    @Test
    public void checkExpectedValueCalculateCorrectResult() {
        setInput();

        viewModel.calculate();

        assertEquals("1.0", viewModel.getResultProperty().get());
    }

    @Test
    public void checkVarianceCalculateCorrectResult() {
        setInput();

        viewModel.operationProperty().set(Operation.VARIANCE);
        viewModel.calculate();

        assertEquals("0.0", viewModel.getResultProperty().get());
    }

    @Test
    public void checkInitialMomentCalculateCorrectResult() {
        setInput();

        viewModel.operationProperty().set(Operation.INITIAL_MOMENT);
        viewModel.calculate();

        assertEquals("1.0", viewModel.getResultProperty().get());
    }

    @Test
    public void checkLoggerEmptyInitially() {
        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void checkLogMessageThenDimensionChanged() {
        viewModel.vectDimensionProperty().set("3");
        assertEquals("Dimension changed to: 3", viewModel.getLoggedMessageText(0));
    }

    @Test
    public void checkLogMessageThenProbabilityAndValuesChanged() {
        viewModel.getProbabilityValuePair().set(0, new ProbabilityValuePair("1.0", "1.0"));

        assertEquals("Probability and values changed to: \n"
                + "Probabilities\n[1.0]\nValues\n[1.0]", viewModel.getLoggedMessageText(0));
    }

    @Test
    public void canLogExpectedValueOperationChange() {
        viewModel.operationProperty().set(Operation.VARIANCE);
        viewModel.operationProperty().set(Operation.EXPECTED_VALUE);
        assertEquals("Operation changed to: Expected value", viewModel.getLoggedMessageText(1));
    }

    @Test
    public void canLogVarianceOperationChange() {
        viewModel.operationProperty().set(Operation.VARIANCE);
        assertEquals("Operation changed to: Variance", viewModel.getLoggedMessageText(0));
    }

    @Test
    public void canLogInitialMomentOperationChange() {
        viewModel.operationProperty().set(Operation.INITIAL_MOMENT);
        assertEquals("Operation changed to: Initial moment", viewModel.getLoggedMessageText(0));
    }

    @Test
    public void canLogExpectedValueOperationResult() {
        setInput();
        viewModel.operationProperty().set(Operation.EXPECTED_VALUE);

        viewModel.calculate();

        assertEquals("Calculated operation: Expected value for:"
                + "\nProbabilities\n[0.5, 0.5]\nValues\n[1.0, 1.0]\nCalculated result: 1.0",
                viewModel.getLoggedMessageText(4));
    }

    @Test
    public void canLogVarianceOperationResult() {
        setInput();
        viewModel.operationProperty().set(Operation.VARIANCE);

        viewModel.calculate();

        assertEquals("Calculated operation: Variance for:"
                + "\nProbabilities\n[0.5, 0.5]\nValues\n[1.0, 1.0]\nCalculated result: 0.0",
                viewModel.getLoggedMessageText(5));
    }

    @Test
    public void canLogInitialMomentOperationResult() {
        setInput();
        viewModel.operationProperty().set(Operation.INITIAL_MOMENT);

        viewModel.calculate();

        assertEquals("Calculated operation: Initial moment for:"
                + "\nProbabilities\n[0.5, 0.5]\nValues\n[1.0, 1.0]\nCalculated result: 1.0",
                viewModel.getLoggedMessageText(5));
    }

    public void setInput() {
        viewModel.vectDimensionProperty().set("2");

        viewModel.getProbabilityValuePair().set(0, new ProbabilityValuePair("0.5", "1.0"));
        viewModel.getProbabilityValuePair().set(1, new ProbabilityValuePair("0.5", "1.0"));
    }

    public void setUpViewModelAndLogger(final SimpleLogger logger) {
        viewModel = new ViewModel();
        viewModel.setLog(logger);
    }
}
