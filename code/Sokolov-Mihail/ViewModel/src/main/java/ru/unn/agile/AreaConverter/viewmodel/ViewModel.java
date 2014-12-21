package ru.unn.agile.AreaConverter.viewmodel;

import ru.unn.agile.AreaConverter.Model.AreaConverter;
import ru.unn.agile.AreaConverter.Model.AreaConverter.Measures;
import java.util.List;

public class ViewModel {
    private String input;
    private Measures measureFrom;
    private Measures measureTo;
    private String result;
    private boolean isCalculateButtonEnabled;
    private double dinput;
    private final ILogger logger;

    public ViewModel(final ILogger loggerA) {
        if (loggerA == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = loggerA;
        input = "";
        measureFrom = Measures.SquareMeter;
        measureTo = Measures.SquareMeter;
        result = "";
        isCalculateButtonEnabled = false;
    }

    public List<String> getLog() {
        return logger.getLog();
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
        if (measure != this.measureFrom) {
            logger.log(LogMessages.CHANGED_MEASURE_FROM + measure.toString());
            this.measureFrom = measure;
        }
    }

    public Measures getMeasureOfAreaTo() {
        return measureTo;
    }

    public void setMeasureOfAreaTo(final Measures measure) {
        if (measure != this.measureTo) {
            logger.log(LogMessages.CHANGED_MEASURE_TO + measure.toString());
            this.measureTo = measure;
        }
    }

    public String getResult() {
        return result;
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    public void focusLost() {
        logInputValueChanged();
    }

    public void convert() {
        if (!parseInput()) {
            logger.log(LogMessages.ERROR);
            return;
        }

        try {
            double dresult = AreaConverter.fromTo(dinput, measureFrom, measureTo);
            result = Double.toString(dresult);
            logSuccessConvertation();
        } catch (IllegalArgumentException e) {
            logger.log(LogMessages.RESULT_IS_TOO_MUCH);
            result = "Result is too much";
        }
    }

    private void logInputValueChanged() {
        logger.log(LogMessages.INPUT_CHANGED_TO + input);
        if (Double.isInfinite(dinput)) {
            logger.log(LogMessages.INPUT_IS_TOO_MUCH);
            return;
        }
        if (isCalculateButtonEnabled) {
            logger.log(LogMessages.INPUT_IS_CORRECT);
        } else {
            logger.log(LogMessages.INPUT_IS_INCORRECT);
        }
    }

    private void logSuccessConvertation() {
        String message = LogMessages.SUCCESS_CONVERTATION
                + "from " + input + " " + measureFrom.toString()
                + " to " + result + " " + measureTo.toString();
        logger.log(message);
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

    public final class LogMessages {
        public static final String RESULT_IS_TOO_MUCH = "Failed convertation. Result is too much ";
        public static final String INPUT_IS_TOO_MUCH = "Input is too much ";
        public static final String CHANGED_MEASURE_TO = "Changed measureTo to -> ";
        public static final String CHANGED_MEASURE_FROM = "Changed measureFrom to -> ";
        public static final String INPUT_CHANGED_TO = "Input changed to -> ";
        public static final String INPUT_IS_CORRECT = "Input is correct +";
        public static final String INPUT_IS_INCORRECT = "Input is incorrect -";
        public static final String SUCCESS_CONVERTATION = "Success convertation ";
        public static final String ERROR = "Error";

        private LogMessages() { }
    }
}
