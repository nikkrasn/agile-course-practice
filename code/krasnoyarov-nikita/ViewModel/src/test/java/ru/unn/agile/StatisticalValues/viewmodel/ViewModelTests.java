package ru.unn.agile.StatisticalValues.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.StatisticalValues.model.StatisticalValues;

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
        final ObservableList<Pair<String, String>> vectProbVal =
                FXCollections.observableArrayList();

        assertTrue(viewModel.vectProbValProperty.equals(vectProbVal));
        assertEquals(StatisticalValues.Operation.EXPECTED_VALUE, viewModel.operationProperty().get());
        assertEquals("", viewModel.getResultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void checkStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void checkStatusReadyWhenFieldsAreFill() {
        setInput();

        assertEquals(Status.READY.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void checkBadFormat() {
        viewModel.vectProbValProperty.add(new Pair<>("0.5", "ssa"));

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void checkWaitForAllParamsEntered() {
        assertEquals(Status.WAITING.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void checkCalculationDisabled() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void checkCalculationDisabledWithWrongParams() {
        viewModel.vectProbValProperty.add(new Pair<>("0.5", "asadada"));

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void checkCalculationWithIncompleteInput() {
        viewModel.vectProbValProperty.add(new Pair<>("0.5", ""));

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void checkCalculationWithCorrectInput() {
        setInput();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void checkCanSetVarianceOperation() {
        viewModel.operationProperty().set(StatisticalValues.Operation.VARIANCE);

        assertEquals(StatisticalValues.Operation.VARIANCE, viewModel.operationProperty().get());
    }

    @Test
    public void checkCanCalculateAndSetSuccessStatus() {
        setInput();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.getStatusProperty().get());
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

        viewModel.operationProperty().set(StatisticalValues.Operation.VARIANCE);
        viewModel.calculate();

        assertEquals("0.0", viewModel.getResultProperty().get());
    }

    @Test
    public void checkInitialMomentCalculateCorrectResult() {
        setInput();

        viewModel.operationProperty().set(StatisticalValues.Operation.INITIAL_MOMENT);
        viewModel.calculate();

        assertEquals("1.0", viewModel.getResultProperty().get());
    }

    public void setInput() {
        viewModel.vectProbValProperty.add(new Pair<>("0.5", "1.0"));
        viewModel.vectProbValProperty.add(new Pair<>("0.5", "1.0"));
    }
}