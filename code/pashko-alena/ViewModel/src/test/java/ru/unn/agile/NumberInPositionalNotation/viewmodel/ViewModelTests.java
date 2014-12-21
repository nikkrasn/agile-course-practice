package ru.unn.agile.NumberInPositionalNotation.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.NumberInPositionalNotation.Model.Notation;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeLogger());
        }
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
    public void canUseConstructorWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
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

    @Test
    public void canThrowNullLoggerException() {
        try {
            new ViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessage() {
        setInputData();
        viewModel.convert();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.CONVERT_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputNotation() {
        viewModel.inputNumberProperty().set("1");

        viewModel.convert();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.inputNumberProperty().get() + ".*"));
    }

    @Test
    public void argumentsInfoFormattedProperly() {
        setInputData();

        viewModel.convert();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": Input Number = " + viewModel.inputNumberProperty().get()
                + "; Input Notation = " + viewModel.inputNotationProperty().get().toString()
                + "; Output Notation = " + viewModel.outputNotationProperty().get().toString()
                + "."));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData();

        viewModel.convert();
        viewModel.convert();
        viewModel.convert();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canChangeInNotationInLog() {
        setInputData();
        String position = "Input";

        viewModel.onNotationChanged(Notation.BINARY, Notation.HEXADECIMAL, position);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.NOTATION_WAS_CHANGED + position + ": "
                + "HEXADECIMAL.*"));
    }

    @Test
    public void canChangeOutNotationInLog() {
        setInputData();
        String position = "Output";

        viewModel.onNotationChanged(Notation.OCTAL, Notation.DECIMAL, position);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.NOTATION_WAS_CHANGED + position + ": "
                + "DECIMAL.*"));
    }

    @Test
    public void notationIsNotLoggedIfNotChanged() {
        String position = "Input";

        viewModel.onNotationChanged(Notation.BINARY, Notation.HEXADECIMAL, position);
        viewModel.onNotationChanged(Notation.HEXADECIMAL, Notation.HEXADECIMAL, position);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void convertIsNotCalledWhenButtonIsDisabled() {
        viewModel.convert();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void argumentsAreCorrectlyLogged() {
        setInputData();

        viewModel.changedArguments(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        System.out.print(message);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.inputNumberProperty().get() + "; "
                + viewModel.inputNotationProperty().get().toString() + "; "
                + viewModel.outputNotationProperty().get().toString()  + "\\]"));
    }

    @Test
    public void doNotLogSameParametersTwice() {
        viewModel.inputNumberProperty().set("85");
        viewModel.changedArguments(Boolean.TRUE, Boolean.FALSE);
        viewModel.inputNumberProperty().set("85");
        viewModel.changedArguments(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }
}
