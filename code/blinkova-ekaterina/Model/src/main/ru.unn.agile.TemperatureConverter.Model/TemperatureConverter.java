package ru.unn.agile.TemperatureConverter.Model;




public class TemperatureConverter {
    private static final double FIRSTCTOF = 1.8;
    private static final double SECONDCTOF = 32.0;
    private static final double CTOK = 273.0;
    private static final double CTON = 0.33;

    public double celsiusToFahrenheit(final double input) {
        if (input >= (Double.MAX_VALUE - SECONDCTOF) / FIRSTCTOF) {
            throw new IllegalArgumentException();
        }
        return FIRSTCTOF * input + SECONDCTOF;
    }

    public double celsiusToKelvin(final double input) {
        if (input >= Double.MAX_VALUE - CTOK) {
            throw new IllegalArgumentException();
        }
        return input + CTOK;
    }

    public double celsiusToNewton(final double input) {
        return CTON * input;
    }

}
