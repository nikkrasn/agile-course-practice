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
        assertEquals("", viewModel.vectDimensionProperty().get());

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
        viewModel.vectDimensionProperty().set("adasda");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void checkWaitForAllParamsEntered() {
        viewModel.vectDimensionProperty().set("3");

        assertEquals(Status.WAITING.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void checkCalculationDisabled() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void checkCalculationDisabledWithWrongParams() {
        viewModel.vectDimensionProperty().set("sdaddas");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    public void setInput() {
        viewModel.vectDimensionProperty().set("2");

        viewModel.vectProbValProperty.add(new Pair<>("0.5", "1.0"));
        viewModel.vectProbValProperty.add(new Pair<>("0.5", "1.0"));
    }
}