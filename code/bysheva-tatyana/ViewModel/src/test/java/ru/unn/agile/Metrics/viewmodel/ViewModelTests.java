package ru.unn.agile.Metrics.viewmodel;

import static org.junit.Assert.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
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
        final ObservableList<Pair<Float, Float>> vectorsValues =
                FXCollections.observableArrayList();
        assertEquals("3", viewModel.vectorsDimensionProperty().get());

        vectorsValues.add(new Pair<>(0.0f, 0.0f));
        vectorsValues.add(new Pair<>(0.0f, 0.0f));
        vectorsValues.add(new Pair<>(0.0f, 0.0f));

        assertTrue(viewModel.vectorsValuesProperty().equals(vectorsValues));
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.operationProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }
}
