package ru.unn.agile.Matrix;

import java.util.regex.*;

public class ViewModel {
    private String textInput = "";
    private String result = "";
    private String status = Status.EMPTY_INPUT.toString();
    private boolean isCalculateButtonEnabled = false;
    public static final int SHIFT = 16;
    public static final int ANY_KEY = 12345;

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    public void processKeyInTextField(final int keyCode) {
        parseInput();
        if (keyCode == SHIFT) {
            calculate();
        }
    }

    public void calculate() {
        if (!parseInput()) {
            return;
        }
        SquareMatrix mat = new SquareMatrix(Converter.stringToArray(textInput));
        MatrixDeterminant determinant = new MatrixDeterminant(mat);
        result = String.valueOf(determinant.getSum());
        status = Status.CALCULATED.toString();
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

    public void setTextInput(final String text) {
        if (text.equals(textInput)) {
            return;
        }
        textInput = text;
    }

    private boolean isInputNonempty() {
        return  !textInput.isEmpty() && !textInput.matches("[\n ]+");
    }

    private boolean parseInput() {
        try {
            if (isInputNonempty()) {
                Converter.stringToArray(textInput);
                isCalculateButtonEnabled = true;
                status = Status.READY.toString();
            } else {
                isCalculateButtonEnabled = false;
                status = Status.EMPTY_INPUT.toString();
            }
            return isCalculateButtonEnabled;
        } catch (Exception e) {
            status = Status.BAD_INPUT.toString();
            isCalculateButtonEnabled = false;
            return false;
        }
    }

    enum  Status {
        EMPTY_INPUT ("Enter the data and press 'Calculate'."),
        READY ("Press 'Calculate' or SHIFT"),
        BAD_INPUT ("Bad input"),
        CALCULATED ("Calculation was carried out");

        private final String name;
        Status(final String name) {
            this.name = name;
        }
        public String toString() {
            return name;
        }
    }

}
