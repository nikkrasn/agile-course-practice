package ru.unn.agile.Converter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Converter.Model.AreaConverter;

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
        assertEquals(AreaConverter.MeasureOfArea.SquareMeter, viewModel.getMeasureOfAreaFrom());
        assertEquals(AreaConverter.MeasureOfArea.SquareMeter, viewModel.getMeasureOfAreaTo());
        assertEquals(false, viewModel.isCalculateButtonEnabled());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void canGetMeasureOfAreaName() {
        String addName = AreaConverter.MeasureOfArea.Are.toString();
        assertEquals("Are", addName);
    }

    @Test
    public void canGetNumberOfMeasuresOfArea() {
        int nOperations = AreaConverter.MeasureOfArea.values().length;
        assertEquals(4, nOperations);
    }

    @Test
    public void canGetListOfMeasuresOfArea() {
        AreaConverter.MeasureOfArea[] operations = AreaConverter.MeasureOfArea.values();
        AreaConverter.MeasureOfArea[] currentOperations = new AreaConverter.MeasureOfArea[]{
                AreaConverter.MeasureOfArea.SquareMeter,
                AreaConverter.MeasureOfArea.SquareKilometer,
                AreaConverter.MeasureOfArea.Hectare,
                AreaConverter.MeasureOfArea.Are};

        assertArrayEquals(currentOperations, operations);
    }

    @Test
    public void canCompareMeasuresOfAreaByName() {
        assertEquals(AreaConverter.MeasureOfArea.SquareMeter,
                     AreaConverter.MeasureOfArea.SquareMeter);
        assertNotEquals(AreaConverter.MeasureOfArea.SquareMeter,
                        AreaConverter.MeasureOfArea.Are);
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
        viewModel.setMeasureOfAreaFrom(AreaConverter.MeasureOfArea.SquareMeter);
        assertEquals(AreaConverter.MeasureOfArea.SquareMeter,
                     viewModel.getMeasureOfAreaFrom());

        viewModel.setMeasureOfAreaFrom(AreaConverter.MeasureOfArea.SquareKilometer);
        assertEquals(AreaConverter.MeasureOfArea.SquareKilometer,
                     viewModel.getMeasureOfAreaFrom());

        viewModel.setMeasureOfAreaFrom(AreaConverter.MeasureOfArea.Are);
        assertEquals(AreaConverter.MeasureOfArea.Are,
                     viewModel.getMeasureOfAreaFrom());

        viewModel.setMeasureOfAreaFrom(AreaConverter.MeasureOfArea.Hectare);
        assertEquals(AreaConverter.MeasureOfArea.Hectare,
                     viewModel.getMeasureOfAreaFrom());
    }

    @Test
    public void canSetMeasureOfAreaTo() {
        viewModel.setMeasureOfAreaTo(AreaConverter.MeasureOfArea.SquareMeter);
        assertEquals(AreaConverter.MeasureOfArea.SquareMeter,
                     viewModel.getMeasureOfAreaTo());

        viewModel.setMeasureOfAreaTo(AreaConverter.MeasureOfArea.SquareKilometer);
        assertEquals(AreaConverter.MeasureOfArea.SquareKilometer,
                     viewModel.getMeasureOfAreaTo());

        viewModel.setMeasureOfAreaTo(AreaConverter.MeasureOfArea.Are);
        assertEquals(AreaConverter.MeasureOfArea.Are,
                     viewModel.getMeasureOfAreaTo());

        viewModel.setMeasureOfAreaTo(AreaConverter.MeasureOfArea.Hectare);
        assertEquals(AreaConverter.MeasureOfArea.Hectare,
                     viewModel.getMeasureOfAreaTo());
    }

    @Test
    public void isDefaultMeasureOfAreaToSquareMeter() {
        assertEquals(AreaConverter.MeasureOfArea.SquareMeter,
                     viewModel.getMeasureOfAreaTo());
    }

    @Test
    public void isDefaultMeasureOfAreaFromSquareMeter() {
        assertEquals(AreaConverter.MeasureOfArea.SquareMeter,
                viewModel.getMeasureOfAreaFrom());
    }

    @Test
    public void canPerformAreaConvertAction() {
        viewModel.setValue("12000000");
        viewModel.setMeasureOfAreaFrom(AreaConverter.MeasureOfArea.SquareMeter);
        viewModel.setMeasureOfAreaTo(AreaConverter.MeasureOfArea.SquareKilometer);

        viewModel.convert();

        assertEquals("12.0", viewModel.getResult());
    }

    private void fillFieldsGood() {
        viewModel.setValue("123");
        viewModel.setMeasureOfAreaFrom(AreaConverter.MeasureOfArea.SquareMeter);
        viewModel.setMeasureOfAreaTo(AreaConverter.MeasureOfArea.SquareMeter);
    }

    private void fillFieldsNullInput() {
        viewModel.setValue("");
        viewModel.setMeasureOfAreaFrom(AreaConverter.MeasureOfArea.SquareMeter);
        viewModel.setMeasureOfAreaTo(AreaConverter.MeasureOfArea.SquareMeter);
    }

    private void fillFieldsWrongInput() {
        viewModel.setValue("123dfg");
        viewModel.setMeasureOfAreaFrom(AreaConverter.MeasureOfArea.SquareMeter);
        viewModel.setMeasureOfAreaTo(AreaConverter.MeasureOfArea.SquareMeter);
    }

    private void fillFieldsInputIsTooMuch() {
        viewModel.setValue("12E5555");
        viewModel.setMeasureOfAreaFrom(AreaConverter.MeasureOfArea.SquareMeter);
        viewModel.setMeasureOfAreaTo(AreaConverter.MeasureOfArea.SquareMeter);
    }

    private void fillFieldsResultIsTooMuch() {
        viewModel.setValue("12E307");
        viewModel.setMeasureOfAreaFrom(AreaConverter.MeasureOfArea.SquareKilometer);
        viewModel.setMeasureOfAreaTo(AreaConverter.MeasureOfArea.SquareMeter);
    }
}
