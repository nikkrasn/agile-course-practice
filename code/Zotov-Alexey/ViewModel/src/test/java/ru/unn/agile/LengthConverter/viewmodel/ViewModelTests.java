package ru.unn.agile.LengthConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.LengthConverter.Model.LengthConverter.*;

import static org.junit.Assert.*;

public class ViewModelTests {

    private ViewModel viewModel;
    public static final int ANY = 7777;

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
}
