package ru.unn.agile.Metrics.viewmodel;

import static org.junit.Assert.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Metrics.Model.Metrics;

import java.util.ArrayList;
import java.util.List;

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
        assertEquals("", viewModel.vectorsDimensionProperty().get());

        assertTrue(viewModel.vectorsValuesProperty.equals(vectorsValues));
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.operationProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormatDimension() {
        viewModel.vectorsDimensionProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormatVectorsValues() {
        viewModel.vectorsDimensionProperty().set("1");
        viewModel.vectorsValuesProperty.add(new Pair<>("a", "b"));

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    private void setInputData() {
        viewModel.vectorsDimensionProperty().set("3");

        viewModel.vectorsValuesProperty.add(new Pair<>("1.0f", "0.0f"));
        viewModel.vectorsValuesProperty.add(new Pair<>("2.0f", "1.0f"));
        viewModel.vectorsValuesProperty.add(new Pair<>("3.0f", "2.0f"));
    }
}
