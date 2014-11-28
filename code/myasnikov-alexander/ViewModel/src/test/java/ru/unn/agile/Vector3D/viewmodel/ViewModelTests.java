package ru.unn.agile.Vector3D.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import ru.unn.agile.ComplexNumber.model.ComplexNumber.Operation;

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

        assertEquals("0", viewModel.getVector2CoordinateX().get());
        assertEquals("0", viewModel.getVector2CoordinateY().get());
        assertEquals("0", viewModel.getVector2CoordinateZ().get());

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
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.getStatusProperty().get());
    }



    private void setInputData() {
        viewModel.getVector1CoordinateX().set("1");
        viewModel.getVector1CoordinateY().set("2");
        viewModel.getVector1CoordinateZ().set("3");

        viewModel.getVector2CoordinateX().set("4");
        viewModel.getVector2CoordinateY().set("5");
        viewModel.getVector2CoordinateZ().set("6");
    }
}
