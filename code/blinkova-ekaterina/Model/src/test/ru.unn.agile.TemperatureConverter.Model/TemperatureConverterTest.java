package ru.unn.agile.TemperatureConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;


import static org.junit.Assert.*;

public class TemperatureConverterTest {
    @Test
    public void CelsiusToFahrenheitZero() {
        TemperatureConverter convertation = createTemperatureConverter();

        double temperature = convertation.CelsiusToFahrenheit(0.0);

        assertEquals(32.0, temperature, 0.0);
    }

    @Test
    public void CelsiusToFahrenheitSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();

        double temperature = convertation.CelsiusToFahrenheit(1.0);

        assertEquals(33.8, temperature, 0.00001);
    }
    @Test
    public void CelsiusToFahrenheitAnotherSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();

        double temperature = convertation.CelsiusToFahrenheit(-1.0);

        assertEquals(30.2, temperature, 0.0);
    }
    @Test
    public void CelsiusToKelvinZero() {
        TemperatureConverter convertation = createTemperatureConverter();

        double temperature = convertation.CelsiusToKelvin(0.0);

        assertEquals(273.0, temperature, 0.0);
    }

    @Test
    public void CelsiusToKelvinSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();

        double temperature = convertation.CelsiusToKelvin(1.0);

        assertEquals(274.0, temperature, 0.0);
    }
    @Test
    public void CelsiusToKelvinAnotherSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();

        double temperature = convertation.CelsiusToKelvin(-1.0);

        assertEquals(272.0, temperature, 0.0);
    }

    @Test
    public void CelsiusToNewtonZero() {
        TemperatureConverter convertation = createTemperatureConverter();

        double temperature = convertation.CelsiusToNewton(0.0);

        assertEquals(0.0, temperature, 0.0);
    }

    @Test
    public void CelsiusToNewtonSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();

        double temperature = convertation.CelsiusToNewton(1.0);

        assertEquals(0.33, temperature, 0.0);
    }

    @Test
    public void CelsiusToNewtonAnotherSimpleNumber() {
        TemperatureConverter convertation = createTemperatureConverter();

        double temperature = convertation.CelsiusToNewton(-1.0);

        assertEquals(-0.33, temperature, 0.0);
    }



    private TemperatureConverter createTemperatureConverter() {
        return new TemperatureConverter();
    }


}
