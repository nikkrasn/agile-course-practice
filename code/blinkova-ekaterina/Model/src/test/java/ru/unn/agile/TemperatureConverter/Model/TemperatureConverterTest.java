package ru.unn.agile.TemperatureConverter.Model;

import org.junit.Test;
import ru.unn.agile.TemperatureConverter.Model.Converter.Scale;
import static org.junit.Assert.assertEquals;

public class TemperatureConverterTest {
    private static final double DELTA = 1e-10;
    private Converter converter = new Converter();

    @Test
    public void celsiusToFahrenheitZero() {
        TemperatureConverter convertToScale = converter.createConverter(Scale.FAHRENHEIT);
        double temperature = convertToScale.convert(0.0);
        assertEquals(32.0, temperature, DELTA);
    }

    @Test
    public void celsiusToFahrenheitSimpleNumber() {
        TemperatureConverter convertToScale = converter.createConverter(Scale.FAHRENHEIT);
        double temperature = convertToScale.convert(1.0);
        assertEquals(33.8, temperature, DELTA);
    }

    @Test
    public void celsiusToFahrenheitAnotherSimpleNumber() {
        TemperatureConverter convertToScale = converter.createConverter(Scale.FAHRENHEIT);
        double temperature = convertToScale.convert(-1.0);
        assertEquals(30.2, temperature, DELTA);
    }

    @Test
    public void celsiusToKelvinZero() {
        TemperatureConverter convertToScale = converter.createConverter(Scale.KELVIN);
        double temperature = convertToScale.convert(0.0);
        assertEquals(273.0, temperature, DELTA);
    }

    @Test
    public void celsiusToKelvinSimpleNumber() {
        TemperatureConverter convertToScale = converter.createConverter(Scale.KELVIN);
        double temperature = convertToScale.convert(1.0);
        assertEquals(274.0, temperature, DELTA);
    }

    @Test
    public void celsiusToKelvinAnotherSimpleNumber() {
        TemperatureConverter convertToScale = converter.createConverter(Scale.KELVIN);
        double temperature = convertToScale.convert(-1.0);
        assertEquals(272.0, temperature, DELTA);
    }

    @Test
    public void celsiusToNewtonZero() {
        TemperatureConverter convertToScale = converter.createConverter(Scale.NEWTON);
        double temperature = convertToScale.convert(0.0);
        assertEquals(0.0, temperature, DELTA);
    }

    @Test
    public void celsiusToNewtonSimpleNumber() {
        TemperatureConverter convertToScale = converter.createConverter(Scale.NEWTON);
        double temperature = convertToScale.convert(1.0);
        assertEquals(0.33, temperature, DELTA);
    }

    @Test
    public void celsiusToNewtonAnotherSimpleNumber() {
        TemperatureConverter convertToScale = converter.createConverter(Scale.NEWTON);
        double temperature = convertToScale.convert(-1.0);
        assertEquals(-0.33, temperature, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void maxDoubleCelsiusToFahrenheit() {
        TemperatureConverter convertToScale = converter.createConverter(Scale.FAHRENHEIT);
        double temperature = convertToScale.convert(Double.MAX_VALUE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void maxDoubleCelsiusToKelvin() {
        TemperatureConverter convertToScale = converter.createConverter(Scale.KELVIN);
        double temperature = convertToScale.convert(Double.MAX_VALUE);
    }
}
