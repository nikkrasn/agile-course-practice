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
            shiftPressed();
        }
    }

    public void calculate() {
        if (!parseInput()) {
            return;
        }
        Converter stringToArray = new Converter(textInput);
        SquareMatrix mat = new SquareMatrix(stringToArray.getData());
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

    private boolean isInputEmpty() {
        return  !textInput.isEmpty() && !textInput.matches("[\n]+") && !textInput.matches(" +");
    }

    private void shiftPressed() {
        if (isCalculateButtonEnabled()) {
            calculate();
        }
    }

    private boolean parseInput() {
        if (isInputEmpty()) {
            try {
                Converter stringToArray = new Converter(textInput);
            } catch (Exception e) {
                status = Status.BAD_INPUT.toString();
                isCalculateButtonEnabled = false;
                return false;
            }
            status = Status.READY.toString();
        } else {
            status = Status.EMPTY_INPUT.toString();
        }
        isCalculateButtonEnabled = isInputEmpty();
        return isCalculateButtonEnabled;
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
