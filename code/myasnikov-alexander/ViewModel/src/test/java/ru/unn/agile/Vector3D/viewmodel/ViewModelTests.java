package ru.unn.agile.Vector3D.viewmodel;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.List;

public class ViewModelTests {

    private ViewModel viewModel;

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeLogger());
        }
    }

    @After
    public void setDown() {
        viewModel = null;
    }

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
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

        assertEquals(String.format("(%.3f, %.3f, %.3f)", 0.0, 3.0, 0.0),
                viewModel.getResult());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorViewModelException() {
        new ViewModel(null);
    }

    @Test
    public void isEmptyLogBeforeCalculation() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void isProperLogMessageAfterCalculation() {
        setInputTwoVectors();
        viewModel.operationProperty().set(VectorOperation.CROSSPRODUCT);

        viewModel.calculate();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches("(.*)" + LogMessages.CALCULATE_WAS_PRESSED + "(.*)"));
    }

    @Test
    public void isProperInputArgumentsInLogAfterCalculation() {
        setInputOneVectors();
        viewModel.operationProperty().set(VectorOperation.NORMALAZE);

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches("(.*)" + viewModel.getVector1CoordinateX().get()
                                 + "(.*)" + viewModel.getVector1CoordinateX().get()
                                 + "(.*)" + viewModel.getVector1CoordinateZ().get() + "(.*)"));
    }


    @Test
    public void isProperOperatorInLogAfterCalculation() {
        setInputTwoVectors();
        viewModel.operationProperty().set(VectorOperation.CROSSPRODUCT);

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches("(.*)" + VectorOperation.CROSSPRODUCT.toString() + "(.*)"));
    }

    @Test
    public void isProperOperatorInLogAfterOperationChange() {
        setInputOneVectors();

        viewModel.onOperationChanged(VectorOperation.NORM, VectorOperation.NORMALAZE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches("(.*)" + LogMessages.OPERATION_WAS_CHANGED
                + VectorOperation.NORMALAZE.toString() +  "(.*)"));
    }

    @Test
    public void isCorrectArgumentsInLog() {
        setInputTwoVectors();
        viewModel.operationProperty().set(VectorOperation.CROSSPRODUCT);

        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.getVector1CoordinateX().get() + "; "
                + viewModel.getVector1CoordinateY().get() + "; "
                + viewModel.getVector1CoordinateZ().get() + "; "
                + viewModel.getVector2CoordinateX().get() + "; "
                + viewModel.getVector2CoordinateY().get() + "; "
                + viewModel.getVector2CoordinateZ().get() + "\\]"));
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
