package ru.unn.agile.TemperatureConverter.viewmodel;

import ru.unn.agile.TemperatureConverter.Model.TemperatureConverter;

public class ViewModel {
    private String inputValue;
    private String result;
    private String status;
    private Scale scale;
    private boolean isConvertButtonEnable;

    public ViewModel() {
        inputValue = "";
        result = "";
        status = Status.WAITING;
        scale = Scale.FAHRENHEIT;
        isConvertButtonEnable = false;
    }

    public String getInputValue() {
        return inputValue;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    public void setInputValue(final String inputValue) {
        this.inputValue = inputValue;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    public Scale getScale() {
        return scale;
    }

    public boolean isConvertButtonEnabled() {
        return isConvertButtonEnable;
    }

    public void setScale(final Scale scale) {
        this.scale = scale;
    }

    public void convert() {
        if (!parseInput()) {
           return;
       }

        TemperatureConverter converter = new TemperatureConverter();
        double temperature;

        double input = Double.parseDouble(inputValue);

        switch (scale) {
            case FAHRENHEIT:
                temperature = converter.celsiusToFahrenheit(input);
                break;
            case KELVIN:
                temperature = converter.celsiusToKelvin(input);
                break;
            case NEWTON:
                temperature = converter.celsiusToNewton(input);
                break;
            default:
                throw new IllegalArgumentException("You can use only Fahrenheit, Kelvin or Newton");
        }

        result = Double.toString(temperature);
        status = Status.SUCCESS;
    }

    public void processKeyInTextField() {
        parseInput();
    }


    private boolean isInputValueNotEmpty() {
        return !inputValue.isEmpty();
    }

    private boolean parseInput() {
        try {
            if (isInputValueNotEmpty()) {
                Double.parseDouble(inputValue);
            }
        } catch (Exception e) {
            status = Status.WRONG_FORMAT;
            isConvertButtonEnable = false;
            return false;
        }

        isConvertButtonEnable = isInputValueNotEmpty();

        if (isConvertButtonEnable) {
            status = Status.READY;
        } else {
            status = Status.WAITING;
        }
        return isConvertButtonEnable;
    }

    public final class Status {
       public static final String WAITING = "Please, enter value";
       public static final String READY = "Press 'Convert'";
       public static final String WRONG_FORMAT = "Wrong input values";
       public static final String SUCCESS = "Success";

        private Status() { }
    }

    public enum Scale {
        FAHRENHEIT("Fahrenheit"),
        KELVIN("Kelvin"),
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
