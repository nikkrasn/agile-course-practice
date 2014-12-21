package ru.unn.agile.AreaConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.AreaConverter.Model.AreaConverter.Measures;
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
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetInitialValues() {
        assertEquals("", viewModel.getInput());
        assertEquals(Measures.SquareMeter, viewModel.getMeasureOfAreaFrom());
        assertEquals(Measures.SquareMeter, viewModel.getMeasureOfAreaTo());
        assertEquals(false, viewModel.isCalculateButtonEnabled());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void canGetMeasureOfAreaName() {
        String addName = Measures.Are.toString();
        assertEquals("Are", addName);
    }

    @Test
    public void canGetNumberOfMeasuresOfArea() {
        int nOperations = Measures.values().length;
        assertEquals(4, nOperations);
    }

    @Test
    public void canGetListOfMeasuresOfArea() {
        Measures[] operations = Measures.values();
        Measures[] currentOperations = new Measures[]{
                Measures.SquareMeter,
                Measures.SquareKilometer,
                Measures.Hectare,
                Measures.Are};

        assertArrayEquals(currentOperations, operations);
    }

    @Test
    public void canCompareMeasuresOfAreaByName() {
        assertEquals(Measures.SquareMeter, Measures.SquareMeter);
        assertNotEquals(Measures.SquareMeter, Measures.Are);
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
        viewModel.setMeasureOfAreaFrom(Measures.SquareMeter);
        assertEquals(Measures.SquareMeter, viewModel.getMeasureOfAreaFrom());

        viewModel.setMeasureOfAreaFrom(Measures.SquareKilometer);
        assertEquals(Measures.SquareKilometer, viewModel.getMeasureOfAreaFrom());

        viewModel.setMeasureOfAreaFrom(Measures.Are);
        assertEquals(Measures.Are, viewModel.getMeasureOfAreaFrom());

        viewModel.setMeasureOfAreaFrom(Measures.Hectare);
        assertEquals(Measures.Hectare, viewModel.getMeasureOfAreaFrom());
    }

    @Test
    public void canSetMeasureOfAreaTo() {
        viewModel.setMeasureOfAreaTo(Measures.SquareMeter);
        assertEquals(Measures.SquareMeter, viewModel.getMeasureOfAreaTo());

        viewModel.setMeasureOfAreaTo(Measures.SquareKilometer);
        assertEquals(Measures.SquareKilometer, viewModel.getMeasureOfAreaTo());

        viewModel.setMeasureOfAreaTo(Measures.Are);
        assertEquals(Measures.Are, viewModel.getMeasureOfAreaTo());

        viewModel.setMeasureOfAreaTo(Measures.Hectare);
        assertEquals(Measures.Hectare, viewModel.getMeasureOfAreaTo());
    }

    @Test
    public void isDefaultMeasureOfAreaToSquareMeter() {
        assertEquals(Measures.SquareMeter, viewModel.getMeasureOfAreaTo());
    }

    @Test
    public void isDefaultMeasureOfAreaFromSquareMeter() {
        assertEquals(Measures.SquareMeter, viewModel.getMeasureOfAreaFrom());
    }

    @Test
    public void canPerformAreaConvertAction() {
        viewModel.setInput("12000000");
        viewModel.setMeasureOfAreaFrom(Measures.SquareMeter);
        viewModel.setMeasureOfAreaTo(Measures.SquareKilometer);

        viewModel.convert();

        assertEquals("12.0", viewModel.getResult());
    }

    private void fillFieldsGood() {
        viewModel.setInput("123");
        viewModel.setMeasureOfAreaFrom(Measures.SquareMeter);
        viewModel.setMeasureOfAreaTo(Measures.SquareMeter);
    }

    private void fillFieldsNullInput() {
        viewModel.setInput("");
        viewModel.setMeasureOfAreaFrom(Measures.SquareMeter);
        viewModel.setMeasureOfAreaTo(Measures.SquareMeter);
    }

    private void fillFieldsWrongInput() {
        viewModel.setInput("123dfg");
        viewModel.setMeasureOfAreaFrom(Measures.SquareMeter);
        viewModel.setMeasureOfAreaTo(Measures.SquareMeter);
    }

    private void fillFieldsInputIsTooMuch() {
        viewModel.setInput("12E5555");
        viewModel.setMeasureOfAreaFrom(Measures.SquareMeter);
        viewModel.setMeasureOfAreaTo(Measures.SquareMeter);
    }

    private void fillFieldsResultIsTooMuch() {
        viewModel.setInput("12E307");
        viewModel.setMeasureOfAreaFrom(Measures.SquareKilometer);
        viewModel.setMeasureOfAreaTo(Measures.SquareMeter);
    }
}
