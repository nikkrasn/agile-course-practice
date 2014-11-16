package ru.unn.agile.Converter.viewmodel;

import ru.unn.agile.Converter.Model.LengthConverter;

public class ViewModel {
    private String inputValue;
    private Measure inputMeasure;
    private Measure outputMeasure;
    private String result;
    private boolean isConvertButtonEnabled;
    private boolean isInputChanged;
    public static final int ENTER = 10;

    public ViewModel() {
        inputValue = "";
        inputMeasure = Measure.METER;
        outputMeasure = Measure.METER;
        result = "";
        isConvertButtonEnabled = false;
    }

    public void processKeyInTextField(final int keyCode) {
        parseInput();
        if (keyCode == ENTER) {
            enterPressed();
        }
    }

    private void enterPressed() {
        if (isConvertButtonEnabled()) {
            convert();
        }
    }

    public boolean isConvertButtonEnabled() {
        return isConvertButtonEnabled;
    }

    private boolean isInputAvailable() {
        return !inputValue.isEmpty();
    }

    private boolean parseInput() {
        result = "";
        try {
            if (!inputValue.isEmpty()) {
                Double.parseDouble(inputValue);
               if (Double.isInfinite(Double.parseDouble(inputValue))) {
                   throw new IllegalArgumentException("слишком большой вход");
               }
            }
        } catch (NumberFormatException e) {
            isConvertButtonEnabled = false;
            result = "плохой вход";
            return false;
        }   catch (IllegalArgumentException e) {
            isConvertButtonEnabled = false;
            result = e.getMessage();
            return false;
        }
        isConvertButtonEnabled = isInputAvailable();
        return isConvertButtonEnabled;
    }

    public void convert() {
        if (!parseInput()) {
            return;
        }
        double input = Double.parseDouble(inputValue);
        double output = 0;
        try {
            switch (inputMeasure) {
                case METER:
                    if (outputMeasure == Measure.KILOMETER) {
                        output = LengthConverter.meterToKilometer(input);
                    } else if (outputMeasure == Measure.MILE) {
                        output = LengthConverter.meterToMile(input);
                    } else if (outputMeasure == Measure.INCH) {
                        output = LengthConverter.meterToInch(input);
                    } else { output = input; }
                    break;
                case KILOMETER:
                    if (outputMeasure == Measure.METER) {
                        output = LengthConverter.kilometerToMeter(input);
                    } else if (outputMeasure == Measure.MILE) {
                        output = LengthConverter.kilometerToMile(input);
                    } else if (outputMeasure == Measure.INCH) {
                        output = LengthConverter.kilometerToInch(input);
                    } else { output = input; }
                    break;
                case MILE:
                    if (outputMeasure == Measure.METER) {
                        output = LengthConverter.mileToMeter(input);
                    } else if (outputMeasure == Measure.KILOMETER) {
                        output = LengthConverter.mileToKilometer(input);
                    } else if (outputMeasure == Measure.INCH) {
                        output = LengthConverter.mileToInch(input);
                    } else { output = input; }
                    break;
                case INCH:
                    if (outputMeasure == Measure.METER) {
                        output = LengthConverter.inchToMeter(input);
                    } else if (outputMeasure == Measure.KILOMETER) {
                        output = LengthConverter.inchToKilometer(input);
                    } else if (outputMeasure == Measure.MILE) {
                        output = LengthConverter.inchToMile(input);
                    } else { output = input; }
                    break;
                default:
                    throw new IllegalArgumentException(); }
            result = String.valueOf(output);
        } catch (IllegalArgumentException e) {
            result = "получилось слишком много";
        }
    }

    public Measure getInputMeasure() {
        return inputMeasure;
    }

    public Measure getOutputMeasure() {
        return outputMeasure;
    }

    public void setInputMeasure(final Measure inputMeasure) {
            this.inputMeasure = inputMeasure;
    }

    public void setOutputMeasure(final Measure outputMeasure) {
        this.outputMeasure = outputMeasure;
    }

    public String getResult() {
        return result;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(final String inputValue) {
        if (inputValue.equals(this.inputValue)) {
            return;
        }
        this.inputValue = inputValue;
    }

    public enum Measure {
        METER("Meter"),
        KILOMETER("Kilometer"),
        MILE("Mile"),
        INCH("Inch");
        private final String name;
        private Measure(final String name) {
            this.name = name;
        }
        public String toString() {
            return name;
        }
    }

}
