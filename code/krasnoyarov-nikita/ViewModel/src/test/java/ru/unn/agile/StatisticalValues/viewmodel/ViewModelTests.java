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
        final ObservableList<Pair<Double, Double>> vectValProb =
                FXCollections.observableArrayList();
        assertEquals("", viewModel.vectDimensionProperty().get());

        assertTrue(viewModel.vectValProbProperty.equals(vectValProb));
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

    public void setInput() {
        viewModel.vectDimensionProperty().set("2");

        viewModel.vectValProbProperty.add(new Pair<>(0.5, 1));
        viewModel.vectValProbProperty.add(new Pair<>(0.5, 1));
    }
}