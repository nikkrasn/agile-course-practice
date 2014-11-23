package ru.unn.agile.TemperatureConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.TemperatureConverter.viewmodel.ViewModel.Status;
import ru.unn.agile.TemperatureConverter.viewmodel.ViewModel.Scale;
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
        assertEquals("", viewModel.getInputValue());
        assertEquals("", viewModel.getResult());
        assertEquals(Status.WAITING, viewModel.getStatus());
        assertEquals(Scale.FAHRENHEIT, viewModel.getScale());
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void canSetSomeInputValue() {
        viewModel.setInputValue("1.0");
        assertEquals("1.0", viewModel.getInputValue());
    }

    @Test
    public void canSetResult() {
        viewModel.setResult("1.0");
        assertEquals("1.0", viewModel.getResult());
    }

    @Test
    public void canGetScaleName() {
        String fahrenheitName = Scale.FAHRENHEIT.toString();
        assertEquals("Fahrenheit", fahrenheitName);
    }

    @Test
    public void canSetFahrenheitScale() {
        viewModel.setScale(Scale.FAHRENHEIT);
        assertEquals(Scale.FAHRENHEIT, viewModel.getScale());
    }

    @Test
    public void canSetKelvinScale() {
        viewModel.setScale(Scale.KELVIN);
        assertEquals(Scale.KELVIN, viewModel.getScale());
    }

    @Test
    public void canSetNewtonScale() {
        viewModel.setScale(Scale.NEWTON);
        assertEquals(Scale.NEWTON, viewModel.getScale());
    }

    @Test
    public void canCompareScalesByName() {
        assertEquals(Scale.NEWTON, Scale.NEWTON);
        assertNotEquals(Scale.NEWTON, Scale.FAHRENHEIT);
    }

    @Test
    public void canCreateListOfScales() {
        ViewModel.Scale[] scales = ViewModel.Scale.values();
        ViewModel.Scale[] realScales = new Scale[]{
                Scale.FAHRENHEIT,
                Scale.KELVIN,
                Scale.NEWTON};
        assertArrayEquals(realScales, scales);
    }

    @Test
    public void canConvertToFahrenheit() {
        viewModel.setInputValue("1.0");
        viewModel.setScale(Scale.FAHRENHEIT);
        viewModel.convert();
        assertEquals("33.8", viewModel.getResult());
    }

    @Test
    public void canConvertToKelvin() {
        viewModel.setInputValue("1.0");
        viewModel.setScale(Scale.KELVIN);
        viewModel.convert();
        assertEquals("274.0", viewModel.getResult());
    }

    @Test
    public void canConvertToNewton() {
        viewModel.setInputValue("1.0");
        viewModel.setScale(Scale.NEWTON);
        viewModel.convert();
        assertEquals("0.33", viewModel.getResult());
    }

    @Test
    public void ifBadInputValueThenStatusWrongFormat() {
        viewModel.setInputValue("q");
        viewModel.convert();
        assertEquals(Status.WRONG_FORMAT, viewModel.getStatus());
  }

    @Test
    public void ifGoodInputValueThenStatusSuccess() {
        viewModel.setInputValue("1.0");
        viewModel.convert();
        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void ifGoodInputValueThenStatusReady() {
        viewModel.setInputValue("1.0");
        viewModel.processKeyInTextField();
        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isConvertButtonEnableWithCorrectInput() {
        viewModel.setInputValue("1.0");
        viewModel.processKeyInTextField();
        assertEquals(true, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isConvertButtonDisableWithIncorrectInput() {
        viewModel.setInputValue("q");
        viewModel.processKeyInTextField();
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }
}
