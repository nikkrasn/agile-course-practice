package ru.unn.agile.LengthConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.LengthConverter.Model.LengthConverter.*;
import static ru.unn.agile.LengthConverter.viewmodel.RegexMatcher.matchesPattern;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelTests {

    private ViewModel viewModel;
    public static final int ANY = 7777;
    public static final int ENTER = 10;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getInputValue());
        assertEquals(Measure.METER, viewModel.getInputMeasure());
        assertEquals(Measure.METER, viewModel.getOutputMeasure());
        assertEquals("", viewModel.getResult());
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isConvertButtonDisabledInitially() {
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void canGetMeasureName() {
        String addName = Measure.METER.toString();
        assertEquals("Meter", addName);
    }

    @Test
    public void canGetNumberOfMeasures() {
        int nMeasures = Measure.values().length;
        assertEquals(4, nMeasures);
    }

    @Test
    public void canGetListOfMeasures() {
        Measure[] measures = Measure.values();
        Measure[] currentMeasures = new Measure[]{
                Measure.METER,
                Measure.KILOMETER,
                Measure.MILE,
                Measure.INCH
                };
        assertArrayEquals(currentMeasures, measures);
    }

    @Test
    public void canCompareMeasuresByName() {
        assertEquals(Measure.METER, Measure.METER);
        assertNotEquals(Measure.METER, Measure.KILOMETER);
        assertNotEquals(Measure.KILOMETER, Measure.INCH);
        assertNotEquals(Measure.INCH, Measure.MILE);
    }

    @Test
    public void isConvertButtonEnabledWithCorrectInput() {
        viewModel.setInputValue("1");
        viewModel.processKeyInTextField(ANY);
        assertEquals(true, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isConvertButtonDisabledWhenFormatIsBad() {
        viewModel.setInputValue("1");
        viewModel.processKeyInTextField(ANY);
        assertEquals(true, viewModel.isConvertButtonEnabled());
        viewModel.setInputValue("trash");
        viewModel.processKeyInTextField(ANY);
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    /*@Test
    public void isConvertButtonDisabledWithTooLargeInput() {
        viewModel.setInputValue("10e308");
        viewModel.processKeyInTextField(ANY);
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }*/

    @Test
    public void isCalculateButtonDisabledWithEmptyInput() {
        viewModel.setInputValue("");
        viewModel.processKeyInTextField(ANY);
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isResultTextEmptyWithRightInput() {
        viewModel.setInputValue("1");
        viewModel.processKeyInTextField(ANY);
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void isResultTextEmptyWithEmptyInput() {
        viewModel.setInputValue("");
        viewModel.processKeyInTextField(ANY);
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void isResultTextRightWithIncorrectInput() {
        viewModel.setInputValue("qqq");
        viewModel.processKeyInTextField(ANY);
        assertEquals("wrong input", viewModel.getResult());
    }

    @Test
    public void isResultTextRightWithTooLargeInput() {
        viewModel.setInputValue("10e308");
        viewModel.processKeyInTextField(ANY);
        assertEquals("input is too huge", viewModel.getResult());
    }

    @Test
    public void isResultTextRightWithTooLargeResult() {
        viewModel.setInputValue("10e307");
        viewModel.setInputMeasure(Measure.KILOMETER);
        viewModel.processKeyInTextField(ANY);
        viewModel.convert();
        assertEquals("result is too huge", viewModel.getResult());
    }

    @Test
    public void canCleanResultTextIfParseIsOK() {
        viewModel.setInputValue("a");
        viewModel.processKeyInTextField(ANY);
        viewModel.setInputValue("1.0");
        viewModel.processKeyInTextField(ANY);
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void canSetInputMeasure() {
        viewModel.setInputMeasure(Measure.METER);
        assertEquals(Measure.METER, viewModel.getInputMeasure());
        viewModel.setInputMeasure(Measure.KILOMETER);
        assertEquals(Measure.KILOMETER, viewModel.getInputMeasure());
        viewModel.setInputMeasure(Measure.MILE);
        assertEquals(Measure.MILE, viewModel.getInputMeasure());
        viewModel.setInputMeasure(Measure.INCH);
        assertEquals(Measure.INCH, viewModel.getInputMeasure());
    }

    @Test
    public void canSetOutputMeasure() {
        viewModel.setOutputMeasure(Measure.METER);
        assertEquals(Measure.METER, viewModel.getOutputMeasure());
        viewModel.setOutputMeasure(Measure.KILOMETER);
        assertEquals(Measure.KILOMETER, viewModel.getOutputMeasure());
        viewModel.setOutputMeasure(Measure.MILE);
        assertEquals(Measure.MILE, viewModel.getOutputMeasure());
        viewModel.setOutputMeasure(Measure.INCH);
        assertEquals(Measure.INCH, viewModel.getOutputMeasure());
    }

    @Test
    public void isDefaultMeasures() {
        assertEquals(Measure.METER, viewModel.getInputMeasure());
        assertEquals(Measure.METER, viewModel.getOutputMeasure());
    }

    @Test
    public void canPerformCalcAction() {
        viewModel.setInputValue("1");
        viewModel.setInputMeasure(Measure.KILOMETER);
        viewModel.setOutputMeasure(Measure.METER);
        viewModel.convert();
        assertEquals("1000.0", viewModel.getResult());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);
        assertNotNull(viewModelLogged);
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception wasnt thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void isLogEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isConvertPuttingSomething() {
        viewModel.convert();
        List<String> log = viewModel.getLog();
        assertNotEquals(0, log.size());
    }

    @Test
    public void isLogContainsProperMessage() {
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message,
                matchesPattern(".*" + ViewModel.LogMessages.CONVERT_WAS_PRESSED + ".*"));
    }

    @Test
    public void isLogContainsInputValue() {
        viewModel.setInputValue("8521");
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + viewModel.getInputValue() + ".*"));
    }

    @Test
    public void isProperlyFormattingInfoAboutInputValue() {
        viewModel.setInputValue("8521");
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*Input Value = " + viewModel.getInputValue() + ".*"));
    }

    @Test
    public void isInputMeasureMentionedInTheLog() {
        viewModel.setInputMeasure(Measure.KILOMETER);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*Kilometer.*"));
    }

    @Test
    public void isOutputMeasureMentionedInTheLog() {
        viewModel.setOutputMeasure(Measure.MILE);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*Mile.*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        viewModel.setInputValue("8521");
        viewModel.convert();
        viewModel.convert();
        viewModel.convert();
        assertEquals(6, viewModel.getLog().size());
    }

    @Test
    public void canSeeInputMeasureChangeInLog() {
        viewModel.setInputMeasure(Measure.INCH);
        String message = viewModel.getLog().get(0);
        assertThat(message,
                matchesPattern(".*" + ViewModel.LogMessages.INPUT_MEASURE_WAS_CHANGED + "Inch.*"));
    }

    @Test
    public void isOperationNotLoggedWhenNotChanged() {
        viewModel.setInputMeasure(Measure.INCH);
        viewModel.setInputMeasure(Measure.INCH);
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void isEditingFinishLogged() {
        viewModel.setInputValue("8521");
        viewModel.focusLost();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessages.EDITING_FINISHED + ".*"));
    }

    @Test
    public void isLogInputsCalledOnEnter() {
        viewModel.setInputValue("8521");
        viewModel.processKeyInTextField(ENTER);
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessages.EDITING_FINISHED + ".*"));
    }

    @Test
    public void isCalculateNotCalledWhenButtonIsDisabled() {
        viewModel.processKeyInTextField(ENTER);
        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void doNotLogSameParametersTwice() {
        viewModel.setInputValue("8521");
        viewModel.setInputValue("8521");
        viewModel.focusLost();
        viewModel.focusLost();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessages.EDITING_FINISHED + ".*"));
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.setInputValue("8521");
        viewModel.setInputValue("8521");
        viewModel.setInputValue("8521");
        viewModel.focusLost();
        viewModel.focusLost();
        viewModel.focusLost();
        assertEquals(1, viewModel.getLog().size());
    }

}
