package ru.unn.agile.Converter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
        assertEquals(ViewModel.Measure.METER, viewModel.getInputMeasure());
        assertEquals(ViewModel.Measure.METER, viewModel.getOutputMeasure());
        assertEquals("", viewModel.getResult());
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isConvertButtonDisabledInitially() {
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void canGetMeasureName() {
        String addName = ViewModel.Measure.METER.toString();
        assertEquals("Meter", addName);
    }

    @Test
    public void canGetNumberOfMeasures() {
        int nMeasures = ViewModel.Measure.values().length;
        assertEquals(4, nMeasures);
    }

    @Test
    public void canGetListOfMeasures() {
        ViewModel.Measure[] measures = ViewModel.Measure.values();
        ViewModel.Measure[] currentMeasures = new ViewModel.Measure[]{
                ViewModel.Measure.METER,
                ViewModel.Measure.KILOMETER,
                ViewModel.Measure.MILE,
                ViewModel.Measure.INCH
                };
        assertArrayEquals(currentMeasures, measures);
    }

    @Test
    public void canCompareMeasuresByName() {
        assertEquals(ViewModel.Measure.METER, ViewModel.Measure.METER);
        assertNotEquals(ViewModel.Measure.METER, ViewModel.Measure.KILOMETER);
        assertNotEquals(ViewModel.Measure.KILOMETER, ViewModel.Measure.INCH);
        assertNotEquals(ViewModel.Measure.INCH, ViewModel.Measure.MILE);
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

    @Test
    public void isConvertButtonDisabledWithTooLargeInput() {
        viewModel.setInputValue("10E308");
        viewModel.processKeyInTextField(ANY);
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

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
        assertEquals("плохой вход", viewModel.getResult());
    }

    @Test
    public void isResultTextRightWithTooLargeInput() {
        viewModel.setInputValue("10e308");
        viewModel.processKeyInTextField(ANY);
        assertEquals("слишком большой вход", viewModel.getResult());
    }

    @Test
    public void isResultTextRightWithTooLargeResult() {
        viewModel.setInputValue("10e307");
        viewModel.setInputMeasure(ViewModel.Measure.KILOMETER);
        viewModel.processKeyInTextField(ANY);
        viewModel.convert();
        assertEquals("получилось слишком много", viewModel.getResult());
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
    public void canSetInputOutputMeasure() {
        viewModel.setInputMeasure(ViewModel.Measure.METER);
        viewModel.setOutputMeasure(ViewModel.Measure.METER);
        assertEquals(ViewModel.Measure.METER, viewModel.getInputMeasure());
        assertEquals(ViewModel.Measure.METER, viewModel.getOutputMeasure());
        viewModel.setInputMeasure(ViewModel.Measure.KILOMETER);
        viewModel.setOutputMeasure(ViewModel.Measure.KILOMETER);
        assertEquals(ViewModel.Measure.KILOMETER, viewModel.getInputMeasure());
        assertEquals(ViewModel.Measure.KILOMETER, viewModel.getOutputMeasure());
        viewModel.setInputMeasure(ViewModel.Measure.MILE);
        viewModel.setOutputMeasure(ViewModel.Measure.MILE);
        assertEquals(ViewModel.Measure.MILE, viewModel.getInputMeasure());
        assertEquals(ViewModel.Measure.MILE, viewModel.getOutputMeasure());
        viewModel.setInputMeasure(ViewModel.Measure.INCH);
        viewModel.setOutputMeasure(ViewModel.Measure.INCH);
        assertEquals(ViewModel.Measure.INCH, viewModel.getInputMeasure());
        assertEquals(ViewModel.Measure.INCH, viewModel.getOutputMeasure());
    }

    @Test
    public void isDefaultMeasures() {
        assertEquals(ViewModel.Measure.METER, viewModel.getInputMeasure());
        assertEquals(ViewModel.Measure.METER, viewModel.getOutputMeasure());
    }

    @Test
    public void canPerformCalcAction() {
        viewModel.setInputValue("1");
        viewModel.setInputMeasure(ViewModel.Measure.KILOMETER);
        viewModel.setOutputMeasure(ViewModel.Measure.METER);
        viewModel.convert();
        assertEquals("1000.0", viewModel.getResult());
    }
}
