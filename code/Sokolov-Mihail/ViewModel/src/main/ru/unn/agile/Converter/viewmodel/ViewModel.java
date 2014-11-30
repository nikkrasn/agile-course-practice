package ru.unn.agile.Converter.viewmodel;

import ru.unn.agile.Converter.Model.AreaConverter;
import ru.unn.agile.Converter.Model.AreaConverter.Measures;

public class ViewModel {
    private String input;
    private Measures measureFrom;
    private Measures measureTo;
    private String result;
    private boolean isCalculateButtonEnabled;
    private double dinput;

    public ViewModel() {
        input = "";
        measureFrom = Measures.SquareMeter;
        measureTo = Measures.SquareMeter;
        result = "";

        isCalculateButtonEnabled = false;
    }

    public void processKeyInTextField() {
        parseInput();
    }

    public void setInput(final String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public Measures getMeasureOfAreaFrom() {
        return measureFrom;
    }

    public void setMeasureOfAreaFrom(final Measures measure) {
        this.measureFrom = measure;
    }

    public Measures getMeasureOfAreaTo() {
        return measureTo;
    }

    public void setMeasureOfAreaTo(final Measures measure) {
        this.measureTo = measure;
    }

    public String getResult() {
        return result;
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    public void convert() {
        if (!parseInput()) {
            return;
        }

        try {
            double dresult = AreaConverter.fromTo(dinput, measureFrom, measureTo);
            result = Double.toString(dresult);
        } catch (IllegalArgumentException e) {
            result = "Result is too much";
        }
    }

    private boolean parseInput() {
        try {
            if (!input.isEmpty()) {
                dinput = Double.parseDouble(input);
                if (Double.isInfinite(dinput)) {
                    throw new IllegalArgumentException("Input is too much");
                }
            }
            isCalculateButtonEnabled = !input.isEmpty();
            result = "";
        } catch (NumberFormatException e) {
            result = "Wrong input";
            isCalculateButtonEnabled = false;
            return false;
        } catch (IllegalArgumentException e) {
            result = e.getMessage();
            isCalculateButtonEnabled = false;
            return false;
        }

        return isCalculateButtonEnabled;
    }
}
