package ru.unn.agile.TemperatureConverter.viewmodel;

import ru.unn.agile.TemperatureConverter.Model.TemperatureConverter;
import ru.unn.agile.TemperatureConverter.Model.Converter;
import ru.unn.agile.TemperatureConverter.Model.Converter.Scale;
import java.util.List;

public class ViewModel {
    private String inputValue;
    private String result;
    private String status;
    private Scale scale;
    private boolean isConvertButtonEnable;
    private boolean isInputDataChanged;
    private final ILogger logger;

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }

        this.logger = logger;
        inputValue = "";
        result = "";
        status = Status.WAITING;
        scale = Scale.FAHRENHEIT;
        isConvertButtonEnable = false;
        isInputDataChanged = true;
    }

    public void convert() {
        logger.log(createConvertLogMessage());

        if (!parseInput()) {
            return;
        }

        Converter converter = new Converter();
        TemperatureConverter convertToScale = converter.createConverter(scale);
        double temperature = convertToScale.convert(Double.parseDouble(inputValue));

        result = Double.toString(temperature);
        status = Status.SUCCESS;
    }

    public boolean parseInput() {
        try {
            if (inputValue.isEmpty()) {
                isConvertButtonEnable = false;
                status = Status.WAITING;
            } else {
                Double.parseDouble(inputValue);
                isConvertButtonEnable = true;
                status = Status.READY;
            }
            return isConvertButtonEnable;
        } catch (Exception e) {
            status = Status.WRONG_FORMAT;
            isConvertButtonEnable = false;
            return false;
        }
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
        if (this.inputValue != inputValue) {
            this.inputValue = inputValue;
            isInputDataChanged = true;
        }
    }

    public void setResult(final String result) {
        this.result = result;
    }

    public Scale getScale() {
        return scale;
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public void focusLost() {
        logInputData();
    }

    public boolean isConvertButtonEnabled() {
        return isConvertButtonEnable;
    }

    public void setScale(final Scale scale) {
        if (this.scale != scale) {
            logger.log(LogMessage.SCALE_CHANGED + scale.toString());
            this.scale = scale;
        }
    }

    private String editingFinishedLogMessage() {
        String message = LogMessage.EDITING_FINISHED
                + "Input Value: ("
                + inputValue + ")";

        return message;
    }

    private void logInputData() {
        if (!isInputDataChanged) {
            return;
        }

        logger.log(editingFinishedLogMessage());
        isInputDataChanged = false;
    }

    private String createConvertLogMessage() {
        String message =
                LogMessage.CONVERT_PRESSED
                        + " InputValue: " + inputValue + "."
                        + " Scale: " + scale.toString() + ".";

        return message;
    }

    public final class Status {
       public static final String WAITING = "Please, enter value";
       public static final String READY = "Press 'Convert'";
       public static final String WRONG_FORMAT = "Wrong input values";
       public static final String SUCCESS = "Success";

       private Status() { }
    }

    public final class LogMessage {
        public static final String CONVERT_PRESSED = "Convert. ";
        public static final String SCALE_CHANGED = "Scale was changed to ";
        public static final String EDITING_FINISHED = "Updated input. ";

        private LogMessage() { }
    }
}
