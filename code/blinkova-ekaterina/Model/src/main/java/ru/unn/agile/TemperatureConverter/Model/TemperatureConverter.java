package ru.unn.agile.TemperatureConverter.Model;

public abstract class TemperatureConverter {
   public abstract double convert(final double input);
}

class Kelvin extends TemperatureConverter {
    private static final double CTOK = 273.0;

    @Override
    public double convert(final double input) {
        if (input >= Double.MAX_VALUE - CTOK) {
            throw new IllegalArgumentException();
        }
        return input + CTOK;
    }
}

class Fahrenheit extends TemperatureConverter {
        private static final double FIRSTCTOF = 1.8;
        private static final double SECONDCTOF = 32.0;

        @Override
        public double convert(final double input) {
            if (input >= (Double.MAX_VALUE - SECONDCTOF) / FIRSTCTOF) {
                throw new IllegalArgumentException();
            }
            return FIRSTCTOF * input + SECONDCTOF;
        }
    }

class Newton extends TemperatureConverter {
        private static final double CTON = 0.33;

        @Override
        public double convert(final double input) {
            return CTON * input;
        }
    }

