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

        assertEquals("", viewModel.getResult());
        assertEquals(StatusOperation.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.getVector1CoordinateX().set("Bad value");

        assertEquals(StatusOperation.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.getVector1CoordinateZ().set("3");

        assertEquals(StatusOperation.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputTwoVectors();
        viewModel.operationProperty().setValue(VectorOperation.NORM);
        viewModel.calculate();

        assertEquals(StatusOperation.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canSetWaitingMessage() {
        viewModel.operationProperty().setValue(VectorOperation.DOTPRODUCT);
        setInputOneVectors();
        viewModel.calculate();

        assertEquals(StatusOperation.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputTwoVectors();

        assertEquals(StatusOperation.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void addIsDefaultOperation() {
        assertEquals(VectorOperation.NORM, viewModel.operationProperty().getValue());
    }

    @Test
    public void operationNormalazeWithNegativeNumbers() {
        viewModel.getVector1CoordinateX().set("-8.0");
        viewModel.getVector1CoordinateY().set("0.0");
        viewModel.getVector1CoordinateZ().set("-6.0");
        viewModel.operationProperty().set(VectorOperation.NORMALAZE);

        viewModel.calculate();

        assertEquals(String.format("(%.3f, %.3f, %.3f)", -0.8, 0.0, -0.6),
                viewModel.getResult());
    }

    @Test
    public void operationNormCorrectResult() {
        setInputTwoVectors();
        viewModel.operationProperty().set(VectorOperation.DOTPRODUCT);

        viewModel.calculate();

        assertEquals(String.format("%.3f", 4.0),
                viewModel.getResult());
    }

    @Test
    public void operationCrossProductCorrectResult() {
        setInputTwoVectors();
        viewModel.operationProperty().set(VectorOperation.CROSSPRODUCT);

        viewModel.calculate();

        assertEquals(String.format("(%.3f, %.3f, %.3f)", 0.0, 0.0, 1.0),
                viewModel.getResult());
    }

    private void setInputOneVectors() {
        viewModel.getVector1CoordinateX().set("0");
        viewModel.getVector1CoordinateY().set("0");
        viewModel.getVector1CoordinateZ().set("1");
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
