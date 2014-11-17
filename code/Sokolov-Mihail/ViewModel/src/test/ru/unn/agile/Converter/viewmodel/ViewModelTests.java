package ru.unn.agile.Converter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetInitialValues() {
        assertEquals("", viewModel.getValue());
        assertEquals(ViewModel.MeasureOfArea.SquareMeter, viewModel.getMeasureOfAreaFrom());
        assertEquals(ViewModel.MeasureOfArea.SquareMeter, viewModel.getMeasureOfAreaTo());
        assertEquals(false, viewModel.isCalculateButtonEnabled());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void canGetMeasureOfAreaName() {
        String addName = ViewModel.MeasureOfArea.Are.toString();
        assertEquals("Are", addName);
    }

    @Test
    public void canGetNumberOfMeasuresOfArea() {
        int nOperations = ViewModel.MeasureOfArea.values().length;
        assertEquals(4, nOperations);
    }

    @Test
    public void canGetListOfMeasuresOfArea() {
        ViewModel.MeasureOfArea[] operations = ViewModel.MeasureOfArea.values();
        ViewModel.MeasureOfArea[] currentOperations = new ViewModel.MeasureOfArea[]{
                ViewModel.MeasureOfArea.SquareMeter,
                ViewModel.MeasureOfArea.SquareKilometer,
                ViewModel.MeasureOfArea.Hectare,
                ViewModel.MeasureOfArea.Are};

        assertArrayEquals(currentOperations, operations);
    }

    @Test
    public void canCompareMeasuresOfAreaByName() {
        assertEquals(ViewModel.MeasureOfArea.SquareMeter, ViewModel.MeasureOfArea.SquareMeter);
        assertNotEquals(ViewModel.MeasureOfArea.SquareMeter, ViewModel.MeasureOfArea.Are);
    }

    @Test
    public void isCalculateButtonEnabledWhenFieldsFilledGood() {
        fillFieldsGood();
        viewModel.processKeyInTextField();
        assertEquals(true, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWhenWrongInput() {
        fillFieldsWrongInput();
        viewModel.processKeyInTextField();
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWithNullInput() {
        fillFieldsNullInput();
        viewModel.processKeyInTextField();
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWhenInputIsTooMuch() {
        fillFieldsInputIsTooMuch();
        viewModel.processKeyInTextField();
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isResultTextBoxReturnsRightStringWhenInputIsGood() {
        fillFieldsGood();
        viewModel.processKeyInTextField();
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void isResultTextBoxReturnsRightStringWhenInputIsNull() {
        fillFieldsNullInput();
        viewModel.processKeyInTextField();
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void isResultTextBoxReturnsRightStringWhenInputIsWrong() {
        fillFieldsWrongInput();
        viewModel.processKeyInTextField();
        assertEquals("Wrong input", viewModel.getResult());
    }

    @Test
    public void isResultTextBoxReturnsRightStringWhenInputIsTooMuch() {
        fillFieldsInputIsTooMuch();
        viewModel.processKeyInTextField();
        assertEquals("Input is too much", viewModel.getResult());
    }

    @Test
    public void isResultTextBoxReturnsRightStringWhenResultIsTooMuch() {
        fillFieldsResultIsTooMuch();

        viewModel.processKeyInTextField();
        viewModel.convert();

        assertEquals("Result is too much", viewModel.getResult());
    }

    @Test
    public void canSetMeasureOfAreaFrom() {
        viewModel.setMeasureOfAreaFrom(ViewModel.MeasureOfArea.SquareMeter);
        assertEquals(ViewModel.MeasureOfArea.SquareMeter, viewModel.getMeasureOfAreaFrom());

        viewModel.setMeasureOfAreaFrom(ViewModel.MeasureOfArea.SquareKilometer);
        assertEquals(ViewModel.MeasureOfArea.SquareKilometer, viewModel.getMeasureOfAreaFrom());

        viewModel.setMeasureOfAreaFrom(ViewModel.MeasureOfArea.Are);
        assertEquals(ViewModel.MeasureOfArea.Are, viewModel.getMeasureOfAreaFrom());

        viewModel.setMeasureOfAreaFrom(ViewModel.MeasureOfArea.Hectare);
        assertEquals(ViewModel.MeasureOfArea.Hectare, viewModel.getMeasureOfAreaFrom());
    }

    @Test
    public void canSetMeasureOfAreaTo() {
        viewModel.setMeasureOfAreaTo(ViewModel.MeasureOfArea.SquareMeter);
        assertEquals(ViewModel.MeasureOfArea.SquareMeter, viewModel.getMeasureOfAreaTo());

        viewModel.setMeasureOfAreaTo(ViewModel.MeasureOfArea.SquareKilometer);
        assertEquals(ViewModel.MeasureOfArea.SquareKilometer, viewModel.getMeasureOfAreaTo());

        viewModel.setMeasureOfAreaTo(ViewModel.MeasureOfArea.Are);
        assertEquals(ViewModel.MeasureOfArea.Are, viewModel.getMeasureOfAreaTo());

        viewModel.setMeasureOfAreaTo(ViewModel.MeasureOfArea.Hectare);
        assertEquals(ViewModel.MeasureOfArea.Hectare, viewModel.getMeasureOfAreaTo());
    }

    @Test
    public void isDefaultMeasureOfAreaToSquareMeter() {
        assertEquals(ViewModel.MeasureOfArea.SquareMeter, viewModel.getMeasureOfAreaTo());
    }

    @Test
    public void isDefaultMeasureOfAreaFromSquareMeter() {
        assertEquals(ViewModel.MeasureOfArea.SquareMeter, viewModel.getMeasureOfAreaFrom());
    }

    @Test
    public void canPerformAreaConvertAction() {
        viewModel.setValue("12000000");
        viewModel.setMeasureOfAreaFrom(ViewModel.MeasureOfArea.SquareMeter);
        viewModel.setMeasureOfAreaTo(ViewModel.MeasureOfArea.SquareKilometer);

        viewModel.convert();

        assertEquals("12.0", viewModel.getResult());
    }

    private void fillFieldsGood() {
        viewModel.setValue("123");
        viewModel.setMeasureOfAreaFrom(ViewModel.MeasureOfArea.SquareMeter);
        viewModel.setMeasureOfAreaTo(ViewModel.MeasureOfArea.SquareMeter);
    }

    private void fillFieldsNullInput() {
        viewModel.setValue("");
        viewModel.setMeasureOfAreaFrom(ViewModel.MeasureOfArea.SquareMeter);
        viewModel.setMeasureOfAreaTo(ViewModel.MeasureOfArea.SquareMeter);
    }

    private void fillFieldsWrongInput() {
        viewModel.setValue("123dfg");
        viewModel.setMeasureOfAreaFrom(ViewModel.MeasureOfArea.SquareMeter);
        viewModel.setMeasureOfAreaTo(ViewModel.MeasureOfArea.SquareMeter);
    }

    private void fillFieldsInputIsTooMuch() {
        viewModel.setValue("12E5555");
        viewModel.setMeasureOfAreaFrom(ViewModel.MeasureOfArea.SquareMeter);
        viewModel.setMeasureOfAreaTo(ViewModel.MeasureOfArea.SquareMeter);
    }

    private void fillFieldsResultIsTooMuch() {
        viewModel.setValue("12E307");
        viewModel.setMeasureOfAreaFrom(ViewModel.MeasureOfArea.SquareKilometer);
        viewModel.setMeasureOfAreaTo(ViewModel.MeasureOfArea.SquareMeter);
    }
}
