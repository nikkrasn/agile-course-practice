package ru.unn.agile.BitArray.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.BitArray.model.BitArray.Operation;

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
        assertEquals("", viewModel.array1Property().get());
        assertEquals("", viewModel.array2Property().get());
        assertEquals(Operation.AND, viewModel.operation1Property().get());
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
    public void canReportBadFormat() {
        viewModel.array1Property().set("daasa");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.array1Property().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.array1Property().set("trash");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.array1Property().set("1111");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetAndOperation() {
        viewModel.operation1Property().set(Operation.AND);
        assertEquals(Operation.AND, viewModel.operation1Property().get());
    }

    @Test
    public void addIsDefaultOperation() {
        assertEquals(Operation.AND, viewModel.operation1Property().get());
    }

    @Test
    public void operationAndHasCorrectResult() {
        viewModel.array1Property().set("1111");
        viewModel.array2Property().set("0000");

        viewModel.calculate();

        assertEquals("0000", viewModel.resultProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.array1Property().set("#selfie");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void operationXorHasCorrectResult() {
        viewModel.array1Property().set("1111");
        viewModel.array2Property().set("1010");
        viewModel.operation1Property().set(Operation.XOR);

        viewModel.calculate();

        assertEquals("0101", viewModel.resultProperty().get());
    }

    @Test
    public void operationOrHasCorrectResult() {
        viewModel.array1Property().set("1010");
        viewModel.array2Property().set("0110");
        viewModel.operation1Property().set(Operation.OR);

        viewModel.calculate();

        assertEquals("1110", viewModel.resultProperty().get());
    }

    private void setInputData() {
        viewModel.array1Property().set("1101");
        viewModel.array2Property().set("1011");
    }
}
