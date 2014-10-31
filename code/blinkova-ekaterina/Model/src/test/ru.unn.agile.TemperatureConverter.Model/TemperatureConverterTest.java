package ru.unn.agile.TemperatureConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TemperatureConverterTest {
    private final double delta = 0.001;
    @Test
    public void celsiusToFahrenheitZero() {
        TemperatureConverter convertation = createTemperatureConverter();
        double temperature = convertation.celsiusToFahrenheit(0.0);
        assertEquals(32.0, temperature, delta);
    }

    @Test
    public void celsiusToFahrenheitSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();
        double temperature = convertation.celsiusToFahrenheit(1.0);
        assertEquals(33.8, temperature, delta);
    }

    @Test
    public void celsiusToFahrenheitAnotherSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();
        double temperature = convertation.celsiusToFahrenheit(-1.0);
        assertEquals(30.2, temperature, delta);
    }

    @Test
    public void celsiusToKelvinZero() {
        TemperatureConverter convertation = createTemperatureConverter();
        double temperature = convertation.celsiusToKelvin(0.0);
        assertEquals(273.0, temperature, delta);
    }

    @Test
    public void celsiusToKelvinSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();
        double temperature = convertation.celsiusToKelvin(1.0);
        assertEquals(274.0, temperature, delta);
    }

    @Test
    public void celsiusToKelvinAnotherSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();
        double temperature = convertation.celsiusToKelvin(-1.0);
        assertEquals(272.0, temperature, delta);
    }

    @Test
    public void celsiusToNewtonZero() {
        TemperatureConverter convertation = createTemperatureConverter();
        double temperature = convertation.celsiusToNewton(0.0);
        assertEquals(0.0, temperature, delta);
    }

    @Test
    public void celsiusToNewtonSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();
        double temperature = convertation.celsiusToNewton(1.0);
        assertEquals(0.33, temperature, delta);
    }

    @Test
    public void celsiusToNewtonAnotherSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();
        double temperature = convertation.celsiusToNewton(-1.0);
        assertEquals(-0.33, temperature, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void maxDoubleCelsiusToFahrenheit() {
        TemperatureConverter convertation = createTemperatureConverter();
        double temperature = convertation.celsiusToFahrenheit(Double.MAX_VALUE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void maxDoubleCelsiusToKelvin() {
        TemperatureConverter convertation = createTemperatureConverter();
        double temperature = convertation.celsiusToKelvin(Double.MAX_VALUE);
    }

    private TemperatureConverter createTemperatureConverter() {
        return new TemperatureConverter();
    }
}
