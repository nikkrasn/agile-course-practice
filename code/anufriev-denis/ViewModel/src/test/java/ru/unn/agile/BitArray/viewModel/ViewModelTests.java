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
        assertEquals("", viewModel.bitArray1StrValue().get());
        assertEquals("", viewModel.bitArray2StrValue().get());
        assertEquals(Operation.AND, viewModel.bitOperation1().get());
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
        setInputDataForTwoArrays();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.bitArray1StrValue().set("daasa");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.bitArray1StrValue().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputDataForTwoArrays();
        viewModel.bitArray1StrValue().set("trash");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.bitArray1StrValue().set("1111");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputDataForTwoArrays();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetAndOperation() {
        viewModel.bitOperation1().set(Operation.AND);
        assertEquals(Operation.AND, viewModel.bitOperation1().get());
    }

    @Test
    public void addIsDefaultOperation() {
        assertEquals(Operation.AND, viewModel.bitOperation1().get());
    }

    @Test
    public void operationAndHasCorrectResult() {
        viewModel.bitArray1StrValue().set("1111");
        viewModel.bitArray2StrValue().set("0000");

        viewModel.calculate();

        assertEquals("0000", viewModel.resultProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputDataForTwoArrays();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.bitArray1StrValue().set("#selfie");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputDataForTwoArrays();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void operationXorHasCorrectResult() {
        viewModel.bitArray1StrValue().set("1111");
        viewModel.bitArray2StrValue().set("1010");
        viewModel.bitOperation1().set(Operation.XOR);

        viewModel.calculate();

        assertEquals("0101", viewModel.resultProperty().get());
    }

    @Test
    public void operationOrHasCorrectResult() {
        viewModel.bitArray1StrValue().set("1010");
        viewModel.bitArray2StrValue().set("0110");
        viewModel.bitOperation1().set(Operation.OR);

        viewModel.calculate();

        assertEquals("1110", viewModel.resultProperty().get());
    }

    @Test
    public void canCalculate() {
        viewModel.bitArray1StrValue().set("1010");
        viewModel.bitArray2StrValue().set("0110");
        viewModel.bitArray3StrValue().set("0110");
        viewModel.bitOperation1().set(Operation.OR);
        viewModel.bitOperation2().set(Operation.XOR);

        viewModel.calculate();

        assertEquals("1000", viewModel.resultProperty().get());
    }

    private void setInputDataForTwoArrays() {
        viewModel.bitArray1StrValue().set("1101");
        viewModel.bitArray2StrValue().set("1011");
    }
}
