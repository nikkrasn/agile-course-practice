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

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.vectorsDimensionProperty().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.vectorsDimensionProperty().set("lalala");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.vectorsDimensionProperty().set("1");
        viewModel.vectorsValuesProperty.add(new Pair<>("1.0", ""));

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetL1Operation() {
        viewModel.operationProperty().set(Metrics.Operation.METRIC_L1);
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.operationProperty().get());
    }

    @Test
    public void l1IsDefaultOperation() {
        assertEquals(Metrics.Operation.METRIC_L1, viewModel.operationProperty().get());
    }

    @Test
    public void operationL1HasCorrectResult() {
        setInputData();

        viewModel.calculate();

        assertEquals("3.0", viewModel.resultProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.vectorsDimensionProperty().set("lalala");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void operationL2HasCorrectResult() {
        setInputData();
        viewModel.operationProperty().set(Metrics.Operation.METRIC_L2);

        viewModel.calculate();

        assertEquals("3.0", viewModel.resultProperty().get());
    }

    @Test
    public void operationL3HasCorrectResult() {
        setInputData();
        viewModel.operationProperty().set(Metrics.Operation.METRIC_L3);

        viewModel.calculate();

        assertEquals("3.0", viewModel.resultProperty().get());
    }

    @Test
    public void operationL4HasCorrectResult() {
        setInputData();
        viewModel.operationProperty().set(Metrics.Operation.METRIC_L4);

        viewModel.calculate();

        assertEquals("3.0", viewModel.resultProperty().get());
    }

    @Test
    public void operationLInfHasCorrectResult() {
        setInputData();
        viewModel.operationProperty().set(Metrics.Operation.METRIC_LINF);

        viewModel.calculate();

        assertEquals("1.0", viewModel.resultProperty().get());
    }

    private void setInputData() {
        viewModel.vectorsDimensionProperty().set("3");

        viewModel.vectorsValuesProperty.add(new Pair<>("1.0f", "0.0f"));
        viewModel.vectorsValuesProperty.add(new Pair<>("2.0f", "1.0f"));
        viewModel.vectorsValuesProperty.add(new Pair<>("3.0f", "2.0f"));
    }
}
