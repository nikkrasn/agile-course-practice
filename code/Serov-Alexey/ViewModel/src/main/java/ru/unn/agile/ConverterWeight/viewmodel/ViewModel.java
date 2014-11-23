package ru.unn.agile.ConverterWeight.viewmodel;

import ru.unn.agile.ConverterWeight.Model.ConverterWeight;

public class ViewModel {
    private String value;
    private String result;
    private String status;
    private UnitWeight valueUnit;
    private UnitWeight resultUnit;
    private boolean convertButton;


    public ViewModel() {
        value = "";
        result = "";
        status = Status.WAITING;
        valueUnit = UnitWeight.GRAMM;
        resultUnit = UnitWeight.GRAMM;
        convertButton = false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
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
        this.valueUnit = valueUnit;
    }

    public void setResultUnit(final UnitWeight resultUnit) {
        this.resultUnit = resultUnit;
    }

    public void convert() {
        prepareForConvert();

        if (status == Status.READY && convertButton) {
            switch (valueUnit) {
                case GRAMM:
                    computationFromGramm();
                    break;
                case KILOGRAM:
                    computationFromKilogram();
                    break;
                case CENTNER:
                    computationFromCentner();
                    break;
                case TON:
                    computationFromTon();
                    break;
                default:
                    throw new IllegalArgumentException("Supported: GRAMM, KILOGRAM, CENTNER, TON");
            }
            status = Status.SUCCESS;
        }
    }

    public boolean isRightValue() {
        try {
            if (valueIsNotEmpty()) {
                Double.parseDouble(value);
            } else {
                status = Status.WAITING;
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

    private boolean valueIsNotEmpty() {
        return !value.isEmpty();
    }

    private void computationFromGramm() {
        ConverterWeight converter = new ConverterWeight();

        switch (resultUnit) {
            case GRAMM:
                result = getValue();
                break;
            case KILOGRAM:
                result = Double.toString(converter.grammToKilogram(value));
                break;
            case CENTNER:
                result = Double.toString(converter.grammToCentner(value));
                break;
            case TON:
                result = Double.toString(converter.grammToTon(value));
                break;
            default:
                throw new IllegalArgumentException("Supported: GRAMM, KILOGRAM, CENTNER, TON");
        }
    }

    private void computationFromKilogram() {
        ConverterWeight converter = new ConverterWeight();

        switch (resultUnit) {
            case GRAMM:
                result = Double.toString(converter.kilogramToGram(value));
                break;
            case KILOGRAM:
                result = getValue();
                break;
            case CENTNER:
                result = Double.toString(converter.kilogramToCentner(value));
                break;
            case TON:
                result = Double.toString(converter.kilogramToTon(value));
                break;
            default:
                throw new IllegalArgumentException("Supported: GRAMM, KILOGRAM, CENTNER, TON");
        }
    }

    private void computationFromCentner() {
        ConverterWeight converter = new ConverterWeight();

        switch (resultUnit) {
            case GRAMM:
                result = Double.toString(converter.centnerToGram(value));
                break;
            case KILOGRAM:
                result = Double.toString(converter.centnerToKilogram(value));
                break;
            case CENTNER:
                result = getValue();
                break;
            case TON:
                result = Double.toString(converter.centnerToTon(value));
                break;
            default:
                throw new IllegalArgumentException("Supported: GRAMM, KILOGRAM, CENTNER, TON");
        }
    }

    private void computationFromTon() {
        ConverterWeight converter = new ConverterWeight();

        switch (resultUnit) {
            case GRAMM:
                result = Double.toString(converter.tonToGram(value));
                break;
            case KILOGRAM:
                result = Double.toString(converter.tonToKilogram(value));
                break;
            case CENTNER:
                result = Double.toString(converter.tonToCentner(value));
                break;
            case TON:
                result = getValue();
                break;
            default:
                throw new IllegalArgumentException("Supported: GRAMM, KILOGRAM, CENTNER, TON");
        }
    }

    public final class Status {
        public static final String WAITING = "Please, input value";
        public static final String BAD_FORMAT = "Bad format";
        public static final String READY = "Please, press 'Convert'";
        public static final String SUCCESS = "Success";

        private Status() { }
    }

    public enum UnitWeight {
        GRAMM("Gramm"),
        KILOGRAM("Kilogram"),
        CENTNER("Centner"),
        TON("Ton");
        private final String name;

        private UnitWeight(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
