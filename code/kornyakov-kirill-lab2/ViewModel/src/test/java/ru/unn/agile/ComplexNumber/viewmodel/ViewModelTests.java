package ru.unn.agile.ComplexNumber.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ComplexNumber.model.ComplexNumber.Operation;

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
        assertEquals("", viewModel.re1Property().get());
        assertEquals("", viewModel.im1Property().get());
        assertEquals("", viewModel.re2Property().get());
        assertEquals("", viewModel.im2Property().get());
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
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
        viewModel.re1Property().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.re1Property().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.re1Property().set("trash");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.re1Property().set("1");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetAddOperation() {
        viewModel.operationProperty().set(Operation.ADD);
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void addIsDefaultOperation() {
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() {
        viewModel.re1Property().set("1");
        viewModel.im1Property().set("4");
        viewModel.re2Property().set("-2");
        viewModel.im2Property().set("-2.5");

        viewModel.calculate();

        assertEquals("-1.0 + 1.5i", viewModel.resultProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.re1Property().set("#selfie");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void operationMulHasCorrectResult() {
        viewModel.re1Property().set("2");
        viewModel.im1Property().set("3");
        viewModel.re2Property().set("1");
        viewModel.im2Property().set("2");
        viewModel.operationProperty().set(Operation.MULTIPLY);

        viewModel.calculate();

        assertEquals("-4.0 + 7.0i", viewModel.resultProperty().get());
    }

    @Test
    public void operationAddWithNegativeNumbersHasCorrectResult() {
        viewModel.re1Property().set("1.2");
        viewModel.im1Property().set("2.3");
        viewModel.re2Property().set("-10.4");
        viewModel.im2Property().set("-20.5");
        viewModel.operationProperty().set(Operation.ADD);

        viewModel.calculate();

        assertEquals("-9.2 - 18.2i", viewModel.resultProperty().get());
    }

    private void setInputData() {
        viewModel.re1Property().set("1");
        viewModel.im1Property().set("2");
        viewModel.re2Property().set("3");
        viewModel.im2Property().set("4");
    }
}
