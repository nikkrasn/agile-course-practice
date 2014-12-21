package ru.unn.agile.TemperatureConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.TemperatureConverter.Model.Converter;
import ru.unn.agile.TemperatureConverter.viewmodel.ViewModel.Status;
import ru.unn.agile.TemperatureConverter.Model.Converter.Scale;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static ru.unn.agile.TemperatureConverter.viewmodel.RegexMatcher.matchesPattern;

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
       Converter.Scale[] scales = Converter.Scale.values();
        Converter.Scale[] realScales = new Scale[]{
                Scale.KELVIN,
                Scale.FAHRENHEIT,
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
        viewModel.parseInput();
        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isConvertButtonEnableWithCorrectInput() {
        viewModel.setInputValue("1.0");
        viewModel.parseInput();
        assertEquals(true, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isConvertButtonDisableWithIncorrectInput() {
        viewModel.setInputValue("q");
        viewModel.parseInput();
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isStatusWaitingWithEmptyInputValue() {
        viewModel.setInputValue("");
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isConvertButtonDisableWithEmptyInput() {
        viewModel.setInputValue("");
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isConvertButtonDisableInTheBeginning() {
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isFahrenheitDefaultScale() {
        assertEquals(Scale.FAHRENHEIT, viewModel.getScale());
    }

    @Test
    public void canWorkWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void viewModelConstructorThrowsExceptionWhenLoggerIsNull() {
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
    public void beginWithEmptyLog() {
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isConvertAddSomethingToLog() {
        viewModel.convert();
        List<String> log = viewModel.getLog();
        assertNotEquals(0, log.size());
    }

    @Test
    public void isLogContainsMessageAboutConvert() {
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessage.CONVERT_PRESSED + ".*"));
    }

    @Test
    public void isLogContainsInputData() {
        viewModel.setInputValue("1");
        viewModel.setScale(Scale.FAHRENHEIT);
        viewModel.convert();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + viewModel.getInputValue()
                + ".*" + viewModel.getScale() + ".*"
        ));
    }

    @Test
    public void isScaleFahrenheitAddToLog() {
        viewModel.setScale(Scale.FAHRENHEIT);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*Fahrenheit.*"));
    }

    @Test
    public void isScaleKelvinAddToLog() {
        viewModel.setScale(Scale.KELVIN);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*Kelvin.*"));
    }

    @Test
    public void isScaleNewtonAddToLog() {
        viewModel.setScale(Scale.NEWTON);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*Newton.*"));
    }

    @Test
    public void canPutSomeLogMessages() {
        viewModel.setInputValue("1");
        viewModel.setScale(Scale.FAHRENHEIT);
        viewModel.convert();
        viewModel.convert();
        assertEquals(2, viewModel.getLog().size());
    }

    @Test
    public void isMessageChangeScaleAddToLog() {
        viewModel.setScale(Scale.KELVIN);
        String message = viewModel.getLog().get(0);
        assertThat(message,
                matchesPattern(".*" + ViewModel.LogMessage.SCALE_CHANGED + "Kelvin.*"));
    }

    @Test
    public void isMessageChangeScaleWithAnotherScaleAddToLog() {
        viewModel.setScale(Scale.NEWTON);
        String message = viewModel.getLog().get(0);
        assertThat(message,
                matchesPattern(".*" + ViewModel.LogMessage.SCALE_CHANGED + "Newton.*"));
    }

    @Test
    public void isNotAddSameInputValue() {
        viewModel.setInputValue("1");
        viewModel.setInputValue("1");
        viewModel.setInputValue("1");
        viewModel.focusLost();
        viewModel.focusLost();
        viewModel.focusLost();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessage.EDITING_FINISHED + ".*"));
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void isEditingFinishLogged() {
        viewModel.setInputValue("1");
        viewModel.focusLost();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessage.EDITING_FINISHED + ".*"));
    }

    @Test
    public void isCorrectFinishLogMessage() {
        viewModel.setInputValue("1");
        viewModel.focusLost();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessage.EDITING_FINISHED
                + "Input Value: \\("
                + viewModel.getInputValue() + "\\)"));
    }
}
