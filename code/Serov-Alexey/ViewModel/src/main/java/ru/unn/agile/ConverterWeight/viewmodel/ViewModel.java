package ru.unn.agile.ConverterWeight.viewmodel;

import ru.unn.agile.ConverterWeight.Model.ConverterWeight.*;

import java.util.List;

import static ru.unn.agile.ConverterWeight.Model.ConverterWeight.converter;

public class ViewModel {
    private String value;
    private String result;
    private String status;
    private UnitWeight valueUnit;
    private UnitWeight resultUnit;
    private boolean convertButton;
    private boolean isInputChanged;
    private ILogger logger;

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger is don't null");
        }

        value = "";
        result = "";
        status = Status.WAITING;
        valueUnit = UnitWeight.GRAMM;
        resultUnit = UnitWeight.GRAMM;
        convertButton = false;
        this.logger = logger;
    }

    public void editingParams() {
        if (isInputChanged) {
            logger.log(Messages.EDITING_FINISHED + "Input argument are: " + value);
            isInputChanged = false;
        }


    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        if (this.value != value) {
            this.value = value;
            isInputChanged = true;
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public UnitWeight getValueUnit() {
        return valueUnit;
    }

    public UnitWeight getResultUnit() {
        return resultUnit;
    }

    public boolean isConvertButton() {
        return convertButton;
    }

    public void setValueUnit(final UnitWeight valueUnit) {
        if (this.valueUnit != valueUnit) {
            logger.log(Messages.VALUE_UNIT_WEIGHT_WAS_CHANGED + valueUnit.toString());
            this.valueUnit = valueUnit;
        }
    }

    public void setResultUnit(final UnitWeight resultUnit) {
        if (this.resultUnit != resultUnit) {
            logger.log(Messages.RESULT_UNIT_WEIGHT_WAS_CHANGED + resultUnit.toString());
            this.resultUnit = resultUnit;
        }
    }

    public void convert() {
        logger.log(convertLogMessage());
        prepareForConvert();

        if (status == Status.READY && convertButton) {
            try {
                result = Double.toString(converter(valueUnit,
                                                   resultUnit,
                                                   Double.parseDouble(value)));
                status = Status.SUCCESS;
            } catch (Exception e) {
                status = Status.LARGE;
            }
        }
    }

    private String convertLogMessage() {
        String message = Messages.PRESSED_TO_CONVERT + "Value = " + value + "."
                        + " Value unit: " + valueUnit.toString()
                        + " convert to: "+ resultUnit.toString() + ".";
       return message;
    }

    public boolean isRightValue() {
        try {
            if (value.isEmpty()) {
                status = Status.WAITING;
            } else if (Double.parseDouble(value) < 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void prepareForConvert() {
        if (isRightValue()) {
            convertButton = true;
            status = Status.READY;
        } else {
            convertButton = false;
            status = Status.BAD_FORMAT;
        }
    }

    public final class Status {
        public static final String WAITING = "Please, input value";
        public static final String BAD_FORMAT = "Bad format";
        public static final String READY = "Please, press 'Convert'";
        public static final String SUCCESS = "Success";
        public static final String LARGE = "A large number in result";

        private Status() { }
    }

    public final class Messages {
        public static final String PRESSED_TO_CONVERT = "Converting. ";
        public static final String VALUE_UNIT_WEIGHT_WAS_CHANGED =
                                                "Value unit weight was changed to ";
        public static final String RESULT_UNIT_WEIGHT_WAS_CHANGED =
                                                "Value unit weight was changed to ";
        public static final String EDITING_FINISHED = "Updated input. ";
        public static final String CONVERTING_FINISHED = "Converting performed ";

        private Messages() { }
    }
}
