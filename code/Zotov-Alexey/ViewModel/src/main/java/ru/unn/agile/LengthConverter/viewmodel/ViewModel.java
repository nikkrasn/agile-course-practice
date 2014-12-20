package ru.unn.agile.LengthConverter.viewmodel;

import ru.unn.agile.LengthConverter.Model.LengthConverter;
import ru.unn.agile.LengthConverter.Model.LengthConverter.Measure;

import java.util.List;


public class ViewModel {
    private String inputValue;
    private Measure inputMeasure;
    private Measure outputMeasure;
    private String result;
    private boolean isConvertButtonEnabled;
    private boolean isInputChanged;
    private ILogger logger;
    public static final int ENTER = 10;

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
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

    private void logInputParams() {
        if (!isInputChanged) {
            return;
        }
        logger.log(editingFinishedLogMessage());
        isInputChanged = false;
    }

    private String editingFinishedLogMessage() {
        String message = LogMessages.EDITING_FINISHED
                + "Input value is: "
                + inputValue + "; ";
        return message;
    }

    public final class LogMessages {
        public static final String CONVERT_WAS_PRESSED = "Convert. ";
        public static final String INPUT_MEASURE_WAS_CHANGED = "Input Measure was changed to ";
        public static final String OUTPUT_MEASURE_WAS_CHANGED = "Output Measure was changed to ";
        public static final String EDITING_FINISHED = "Updated input. ";

        private LogMessages() { }
    }

    public void focusLost() {
        logInputParams();
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public boolean isConvertButtonEnabled() {
        return isConvertButtonEnabled;
    }

    public void convert() {
        logger.log(calculateLogMessage());
        if (!parseInput()) {
            return;
        }
        double input = Double.parseDouble(inputValue);
        double output;
        try {
            output = LengthConverter.convertFromTo(inputMeasure, outputMeasure, input);
            result = String.valueOf(output);
            logger.log("result is too huge");
        } catch (IllegalArgumentException e) {
            result = "result is too huge";
            logger.log("result is too huge");
        }
    }

    private String calculateLogMessage() {
        String message =
                LogMessages.CONVERT_WAS_PRESSED + "Input Value = "
                        + inputValue + "; InputMeasure = " + inputMeasure.toString()
                        + "; Output Measure = " + outputMeasure.toString() + ";";

        return message;
    }

    public Measure getInputMeasure() {
        return inputMeasure;
    }

    public Measure getOutputMeasure() {
        return outputMeasure;
    }

    public void setInputMeasure(final Measure inputMeasure) {
        if (this.inputMeasure != inputMeasure) {
            logger.log(LogMessages.INPUT_MEASURE_WAS_CHANGED + inputMeasure.toString());
            this.inputMeasure = inputMeasure;
        }
    }

    public void setOutputMeasure(final Measure outputMeasure) {
        if (this.outputMeasure != outputMeasure) {
            logger.log(LogMessages.OUTPUT_MEASURE_WAS_CHANGED + outputMeasure.toString());
            this.outputMeasure = outputMeasure;
        }
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
        isInputChanged = true;
    }

    private void enterPressed() {
        logInputParams();
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
                   throw new IllegalArgumentException("input is too huge");
               }
            }
        } catch (NumberFormatException e) {
            isConvertButtonEnabled = false;
            result = "wrong input";
            return false;
        } catch (IllegalArgumentException e) {
            isConvertButtonEnabled = false;
            result = e.getMessage();
            return false;
        }
        isConvertButtonEnabled = !inputValue.isEmpty();
        return isConvertButtonEnabled;
    }
}
