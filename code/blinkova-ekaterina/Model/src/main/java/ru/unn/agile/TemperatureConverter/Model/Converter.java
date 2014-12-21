package ru.unn.agile.TemperatureConverter.Model;

public class Converter {
 public TemperatureConverter createConverter(final Scale scale) {
        switch (scale) {
            case FAHRENHEIT:
                return new Fahrenheit();
            case KELVIN:
                return new Kelvin();
            case NEWTON:
                return new Newton();
            default:
                throw new IllegalArgumentException("You can use only Fahrenheit, Kelvin or Newton");
        }
    }

    public enum Scale {
        KELVIN("Kelvin"),
        FAHRENHEIT("Fahrenheit"),
        NEWTON("Newton");
        private final String name;

        private Scale(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
