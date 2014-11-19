package ru.unn.agile.Matrix;

import java.util.regex.*;

public class ViewModel {
    private String textInput;
    private String result;
    private String status;
    private boolean isCalculateButtonEnabled;
    public static final int SHIFT = 16;
    public static final int ANY_KEY = 12345;

    public ViewModel() {
        textInput = "";
        result = "";
        status = Status.EMPTY_INPUT;
        isCalculateButtonEnabled = false;
    }

    public void processKeyInTextField(final int keyCode) {
        parseInput();

        if (keyCode == SHIFT) {
            shiftPressed();
        }
    }

    private void shiftPressed() {

        if (isCalculateButtonEnabled()) {
            calculate();
        }
    }

    public void calculate() {
        if (!parseInput()) {
            return;
        }
        double[][] array = Converter.stringToArray(textInput);
        SquareMatrix mat = new SquareMatrix(array);
        result = String.valueOf(MatrixDeterminant.calculation(mat));
        status = Status.CALCULATED;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    public String getTextInput() {
        return textInput;
    }

    public final class Status {
        public static final String EMPTY_INPUT = "Enter the data and press 'Calculate'.";
        public static final String READY = "Press 'Calculate' or SHIFT";
        public static final String BAD_INPUT = "Bad input";
        public static final String CALCULATED = "Calculation was carried out";
        private Status() {
        }
    }

    private static final class Patterns {
        public static final Pattern ONLY_NEW_LINES = Pattern.compile("[\n]+");
        public static final Pattern ONLY_INDENTS = Pattern.compile(" +");
        private Patterns() {
        }
    }

    public void setTextInput(final String text) {
        if (text.equals(this.textInput)) {
            return;
        }
        this.textInput = text;
    }

    private boolean isInputEmpty() {
        Matcher m1 = Patterns.ONLY_NEW_LINES.matcher(textInput);
        Matcher m2 = Patterns.ONLY_INDENTS.matcher(textInput);
        return  !textInput.isEmpty() && !m1.matches() && !m2.matches();
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    private boolean parseInput() {
        try {
            if (isInputEmpty()) {
                double[][] array = Converter.stringToArray(textInput);
            }
        } catch (Exception e) {
            status = Status.BAD_INPUT;
            isCalculateButtonEnabled = false;
            return false;
        }
        isCalculateButtonEnabled = isInputEmpty();
        if (isCalculateButtonEnabled) {
            status = Status.READY;
        } else {
            status = Status.EMPTY_INPUT;
        }
        return isCalculateButtonEnabled;
    }
}

