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
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        Pairs first = viewModel.getVectorsProbValues().get(0);

        assertEquals("1", viewModel.getVectDimension());
        assertTrue(first.equals(new Pairs("0.0", "0.0")));
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
        viewModel.getVectorsProbValues().set(0, new Pairs("0.5", "ssa"));

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getOperationStatusProperty().get());
    }

    @Test
    public void checkBadFormatNotAllParamsEntered() {
        setInput();
        viewModel.getVectorsProbValues().set(0, new Pairs("0.5", ""));

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getOperationStatusProperty().get());
    }

    @Test
    public void checkCalculationEnabledForFirstTime() {
        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void checkCalculationDisabledWithWrongParams() {
        viewModel.getVectorsProbValues().set(0, new Pairs("0.5", "asadada"));

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void checkCalculationWithIncompleteInput() {
        viewModel.getVectorsProbValues().set(0, new Pairs("0.5", ""));

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

    public void setInput() {
        viewModel.vectDimensionProperty().set("2");

        viewModel.getVectorsProbValues().set(0, new Pairs("0.5", "1.0"));
        viewModel.getVectorsProbValues().set(1, new Pairs("0.5", "1.0"));
    }
}
