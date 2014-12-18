package ru.unn.agile.Matrix;

import java.util.List;

public class ViewModel {
    private ILogger logger;
    private String textInput = "";
    private String result = "";
    private String status = SystemMessages.EMPTY_INPUT.toString();
    private boolean isCalculateButtonEnabled = false;
    private boolean isInputChanged = true;
    public static final int SHIFT = 16;
    public static final int ANY_KEY = 12345;

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

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
        status = SystemMessages.CALCULATED.toString();
        logger.createLog(calculateLogMessage());
    }

    public void focusLost() {
        logInputParams();
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

    public String getUpdateLogMessage() {
        return updateLogMessage();
    }

    public String getCalculateLogMessage() {
        return calculateLogMessage();
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public void setTextInput(final String text) {
        if (text.equals(textInput)) {
            return;
        }
        textInput = text;
        isInputChanged = true;
    }

    private boolean isInputNonempty() {
        return  !textInput.isEmpty() && !textInput.matches("[\n ]+");
    }

    private boolean parseInput() {
        try {
            if (isInputNonempty()) {
                Converter.stringToArray(textInput);
                isCalculateButtonEnabled = true;
                status = SystemMessages.READY.toString();
            } else {
                isCalculateButtonEnabled = false;
                status = SystemMessages.EMPTY_INPUT.toString();
            }
            return isCalculateButtonEnabled;
        } catch (Exception e) {
            status = SystemMessages.BAD_INPUT.toString();
            isCalculateButtonEnabled = false;
            return false;
        }
    }

    private void logInputParams() {
        if (!isInputChanged) {
            return;
        }
        logger.createLog(updateLogMessage());
        isInputChanged = false;
    }

    private String createDefaultLogMessage() {
        String message;
        try {
            double[][] array = Converter.stringToArray(textInput);
            message = "\nMatrix: " + Converter.arrayToString(array);
        } catch (Exception e) {
            message = "\n" + SystemMessages.INVALID_INPUT;
        }
        return message;
    }

    private String updateLogMessage() {
        String message = SystemMessages.UPDATE_INPUT.toString()
                + createDefaultLogMessage();
        return message;
    }

    private String calculateLogMessage() {
        String message = SystemMessages.CALCULATED.toString()
                + createDefaultLogMessage() + " = " + getResult();
        return message;
    }

    enum SystemMessages {
        EMPTY_INPUT ("Enter the data and press 'Calculate'."),
        READY ("Press 'Calculate' or SHIFT."),
        BAD_INPUT ("Bad input."),
        CALCULATED ("Calculation was carried out."),
        UPDATE_INPUT("Updated input."),
        INVALID_INPUT("Matrix is invalid.");

        private final String name;
        SystemMessages(final String name) {
            this.name = name;
        }
        public String toString() {
            return name;
        }
    }
}
