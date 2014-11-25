package ru.unn.agile.Converter.viewmodel;

import ru.unn.agile.Converter.Model.LengthConverter;
import ru.unn.agile.Converter.Model.LengthConverter.*;

public class ViewModel {
    private String inputValue;
    private Measure inputMeasure;
    private Measure outputMeasure;
    private String result;
    private boolean isConvertButtonEnabled;
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

    public boolean isConvertButtonEnabled() {
        return isConvertButtonEnabled;
    }

    public void convert() {
        if (!parseInput()) {
            return;
        }
        double input = Double.parseDouble(inputValue);
        double output = 0;
        try {
            output = LengthConverter.convertFromTo(inputMeasure, outputMeasure, input);
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

    private void enterPressed() {
        if (isConvertButtonEnabled()) {
            convert();
        }
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
        isConvertButtonEnabled = !inputValue.isEmpty();
        return isConvertButtonEnabled;
    }
}
