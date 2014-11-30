package ru.unn.agile.NumberInPositionalNotation.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.NumberInPositionalNotation.Model.Notation;

import static org.junit.Assert.*;
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
        assertEquals("", viewModel.inputNumberProperty().get());
        assertEquals(Notation.BINARY, viewModel.inputNotationProperty().get());
        assertEquals(Notation.DECIMAL, viewModel.outputNotationProperty().get());
        assertEquals("", viewModel.outputNumberProperty().get());
        assertEquals(InputStatus.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenConvertWithEmptyFields() {
        viewModel.convert();

        assertEquals(InputStatus.WAITING.toString(), viewModel.statusProperty().get());
    }

    private void setInputData() {
        viewModel.inputNumberProperty().set("1");
        viewModel.inputNotationProperty().set(Notation.BINARY);
        viewModel.outputNotationProperty().set(Notation.DECIMAL);
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(InputStatus.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.inputNumberProperty().set("f");

        assertEquals(InputStatus.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.inputNotationProperty().set(Notation.OCTAL);
        viewModel.outputNotationProperty().set(Notation.BINARY);

        assertEquals(InputStatus.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertButtonIsDisabledInitially() {
        assertTrue(viewModel.convertDisabledProperty().get());
    }

    @Test
    public void convertButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.inputNumberProperty().set("12y");

        assertTrue(viewModel.convertDisabledProperty().get());
    }

    @Test
    public void convertButtonIsDisabledWithIncompleteInput() {
        viewModel.inputNotationProperty().set(Notation.HEXADECIMAL);
        viewModel.outputNotationProperty().set(Notation.OCTAL);

        assertTrue(viewModel.convertDisabledProperty().get());
    }

    @Test
    public void convertButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.convertDisabledProperty().get());
    }

    @Test
    public void canSetInputNotation() {
        viewModel.inputNotationProperty().set(Notation.OCTAL);
        assertEquals(Notation.OCTAL, viewModel.inputNotationProperty().get());
    }

    @Test
    public void canSetOutputNotation() {
        viewModel.outputNotationProperty().set(Notation.DECIMAL);
        assertEquals(Notation.DECIMAL, viewModel.outputNotationProperty().get());
    }

    @Test
    public void binaryIsDefaultInputNotation() {
        assertEquals(Notation.BINARY, viewModel.inputNotationProperty().get());
    }

    @Test
    public void decimalIsDefaultOutputNotation() {
        assertEquals(Notation.DECIMAL, viewModel.outputNotationProperty().get());
    }

    @Test
    public void covertHasCorrectResult() {
        viewModel.inputNumberProperty().set("1.1");
        viewModel.inputNotationProperty().set(Notation.BINARY);
        viewModel.outputNotationProperty().set(Notation.DECIMAL);

        viewModel.convert();

        assertEquals("1.5", viewModel.outputNumberProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.convert();

        assertEquals(InputStatus.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.inputNumberProperty().set("smthng");

        assertEquals(InputStatus.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(InputStatus.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertToHexadecimalHasCorrectResult() {
        viewModel.inputNumberProperty().set("10.7");
        viewModel.inputNotationProperty().set(Notation.OCTAL);
        viewModel.outputNotationProperty().set(Notation.HEXADECIMAL);

        viewModel.convert();

        assertEquals("8.e", viewModel.outputNumberProperty().get());
    }
}
