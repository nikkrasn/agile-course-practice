package ru.unn.agile.NumberInPositionalNotation.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.NumberInPositionalNotation.Model.Notation;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel testModel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.testModel = viewModel;
    }

    @Before
    public void setUp() {
        if (testModel == null) {
            testModel = new ViewModel(new FakeLogger());
        }
    }

    @After
    public void tearDown() {
        testModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", testModel.inputNumberProperty().get());
        assertEquals(Notation.BINARY, testModel.inputNotationProperty().get());
        assertEquals(Notation.DECIMAL, testModel.outputNotationProperty().get());
        assertEquals("", testModel.outputNumberProperty().get());
        assertEquals(InputStatus.WAITING.toString(), testModel.statusProperty().get());
    }

    @Test
    public void canUseConstructorWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void statusIsWaitingWhenConvertWithEmptyFields() {
        testModel.convert();

        assertEquals(InputStatus.WAITING.toString(), testModel.statusProperty().get());
    }

    private void setInputData() {
        testModel.inputNumberProperty().set("1");
        testModel.inputNotationProperty().set(Notation.BINARY);
        testModel.outputNotationProperty().set(Notation.DECIMAL);
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(InputStatus.READY.toString(), testModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        testModel.inputNumberProperty().set("f");

        assertEquals(InputStatus.BAD_FORMAT.toString(), testModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        testModel.inputNotationProperty().set(Notation.OCTAL);
        testModel.outputNotationProperty().set(Notation.BINARY);

        assertEquals(InputStatus.WAITING.toString(), testModel.statusProperty().get());
    }

    @Test
    public void convertButtonIsDisabledInitially() {
        assertTrue(testModel.convertDisabledProperty().get());
    }

    @Test
    public void convertButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        testModel.inputNumberProperty().set("12y");

        assertTrue(testModel.convertDisabledProperty().get());
    }

    @Test
    public void convertButtonIsDisabledWithIncompleteInput() {
        testModel.inputNotationProperty().set(Notation.HEXADECIMAL);
        testModel.outputNotationProperty().set(Notation.OCTAL);

        assertTrue(testModel.convertDisabledProperty().get());
    }

    @Test
    public void convertButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(testModel.convertDisabledProperty().get());
    }

    @Test
    public void canSetInputNotation() {
        testModel.inputNotationProperty().set(Notation.OCTAL);
        assertEquals(Notation.OCTAL, testModel.inputNotationProperty().get());
    }

    @Test
    public void canSetOutputNotation() {
        testModel.outputNotationProperty().set(Notation.DECIMAL);
        assertEquals(Notation.DECIMAL, testModel.outputNotationProperty().get());
    }

    @Test
    public void binaryIsDefaultInputNotation() {
        assertEquals(Notation.BINARY, testModel.inputNotationProperty().get());
    }

    @Test
    public void decimalIsDefaultOutputNotation() {
        assertEquals(Notation.DECIMAL, testModel.outputNotationProperty().get());
    }

    @Test
    public void covertHasCorrectResult() {
        testModel.inputNumberProperty().set("1.1");
        testModel.inputNotationProperty().set(Notation.BINARY);
        testModel.outputNotationProperty().set(Notation.DECIMAL);

        testModel.convert();

        assertEquals("1.5", testModel.outputNumberProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        testModel.convert();

        assertEquals(InputStatus.SUCCESS.toString(), testModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        testModel.inputNumberProperty().set("smthng");

        assertEquals(InputStatus.BAD_FORMAT.toString(), testModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(InputStatus.READY.toString(), testModel.statusProperty().get());
    }

    @Test
    public void startLogIsEmpty() {
        List<String> log = testModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void convertToHexadecimalHasCorrectResult() {
        testModel.inputNumberProperty().set("10.7");
        testModel.inputNotationProperty().set(Notation.OCTAL);
        testModel.outputNotationProperty().set(Notation.HEXADECIMAL);

        testModel.convert();
        assertEquals("8.e", testModel.outputNumberProperty().get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void canThrowNullLoggerException() {
            new ViewModel(null);
    }

    @Test
    public void logContainsProperMessage() {
        setInputData();
        testModel.convert();
        String message = testModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.CONVERT_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputNotation() {
        testModel.inputNumberProperty().set("1");

        testModel.convert();

        String message = testModel.getLog().get(0);
        assertTrue(message.matches(".*" + testModel.inputNumberProperty().get() + ".*"));
    }

    @Test
    public void argumentsInfoFormattedProperly() {
        setInputData();

        testModel.convert();

        String message = testModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": Input Number = " + testModel.inputNumberProperty().get()
                + "; Input Notation = " + testModel.inputNotationProperty().get().toString()
                + "; Output Notation = " + testModel.outputNotationProperty().get().toString()
                + "."));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData();

        testModel.convert();
        testModel.convert();
        testModel.convert();

        assertEquals(3, testModel.getLog().size());
    }

    @Test
    public void canChangeInNotationInLog() {
        setInputData();
        String position = "Input";

        testModel.onNotationChanged(Notation.BINARY, Notation.HEXADECIMAL, position);

        String message = testModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.NOTATION_WAS_CHANGED + position + ": "
                + "HEXADECIMAL.*"));
    }

    @Test
    public void canChangeOutNotationInLog() {
        setInputData();
        String position = "Output";

        testModel.onNotationChanged(Notation.OCTAL, Notation.DECIMAL, position);

        String message = testModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.NOTATION_WAS_CHANGED + position + ": "
                + "DECIMAL.*"));
    }

    @Test
    public void notationIsNotLoggedIfNotChanged() {
        String position = "Input";

        testModel.onNotationChanged(Notation.BINARY, Notation.HEXADECIMAL, position);
        testModel.onNotationChanged(Notation.HEXADECIMAL, Notation.HEXADECIMAL, position);

        assertEquals(1, testModel.getLog().size());
    }

    @Test
    public void convertIsNotCalledWhenButtonIsDisabled() {
        testModel.convert();

        assertTrue(testModel.getLog().isEmpty());
    }

    @Test
    public void argumentsAreCorrectlyLogged() {
        setInputData();

        testModel.changedArguments(Boolean.TRUE, Boolean.FALSE);

        String message = testModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + testModel.inputNumberProperty().get() + "; "
                + testModel.inputNotationProperty().get().toString() + "; "
                + testModel.outputNotationProperty().get().toString()  + "\\]"));
    }

    @Test
    public void doNotLogSameParametersTwice() {
        testModel.inputNumberProperty().set("85");
        testModel.changedArguments(Boolean.TRUE, Boolean.FALSE);
        testModel.inputNumberProperty().set("85");
        testModel.changedArguments(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, testModel.getLog().size());
    }
}
