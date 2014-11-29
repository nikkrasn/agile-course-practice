package ru.unn.agile.Vector3D.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        assertEquals("", viewModel.getVector1CoordinateX().get());
        assertEquals("", viewModel.getVector1CoordinateY().get());
        assertEquals("", viewModel.getVector1CoordinateZ().get());

        assertEquals("", viewModel.getVector2CoordinateX().get());
        assertEquals("", viewModel.getVector2CoordinateY().get());
        assertEquals("", viewModel.getVector2CoordinateZ().get());

        assertEquals("", viewModel.getResult().get());
        assertEquals(Status.WAITING.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.getVector1CoordinateX().set("Bad value");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.getVector1CoordinateZ().set("3");

        assertEquals(Status.WAITING.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputTwoVectors();

        viewModel.setOperation(VectorOperation.NORM);
        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputTwoVectors();

        assertEquals(Status.READY.toString(), viewModel.getStatusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void addIsDefaultOperation() {
        assertEquals(VectorOperation.NORM, viewModel.getOperation());
    }

    @Test
    public void canSetNormalazeOperation() {
        viewModel.setOperation(VectorOperation.NORMALAZE);
        assertEquals(VectorOperation.NORMALAZE, viewModel.getOperation());
    }

    @Test
    public void operationNormalazeWithNegativeNumbers() {
        viewModel.getVector1CoordinateX().set("-8.0");
        viewModel.getVector1CoordinateY().set("0.0");
        viewModel.getVector1CoordinateZ().set("-6.0");
        viewModel.setOperation(VectorOperation.NORMALAZE);

        viewModel.calculate();

        assertEquals(String.format("(%f, %f, %f)", -0.8, 0.0, -0.6),
                viewModel.getResult().get().toString());
    }

    @Test
    public void operationNormCorrectResult() {
        setInputTwoVectors();
        viewModel.setOperation(VectorOperation.DOTPRODUCT);

        viewModel.calculate();

        assertEquals(String.format("%f", 4.0),
                viewModel.getResult().get().toString());
    }

    @Test
    public void operationCrossProductCorrectResult() {
        setInputTwoVectors();
        viewModel.setOperation(VectorOperation.CROSSPRODUCT);

        viewModel.calculate();

        assertEquals(String.format("(%f, %f, %f)", 0.0, 0.0, 1.0),
                viewModel.getResult().get().toString());
    }


    private void setInputTwoVectors() {
        viewModel.getVector1CoordinateX().set("0");
        viewModel.getVector1CoordinateY().set("0");
        viewModel.getVector1CoordinateZ().set("1");

        viewModel.getVector2CoordinateX().set("3.0");
        viewModel.getVector2CoordinateY().set("0");
        viewModel.getVector2CoordinateZ().set("4.0");
    }
}
