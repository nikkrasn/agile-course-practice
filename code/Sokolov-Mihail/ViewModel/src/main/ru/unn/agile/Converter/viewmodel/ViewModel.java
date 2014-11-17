package ru.unn.agile.Converter.viewmodel;

import ru.unn.agile.Converter.Model.Converter;

public class ViewModel {
    private String value;
    private MeasureOfArea measureFrom;
    private MeasureOfArea measureTo;
    private String result;
    private boolean isCalculateButtonEnabled;

    private double dvalue;

    public ViewModel() {
        value = "";
        measureFrom = MeasureOfArea.SquareMeter;
        measureTo = MeasureOfArea.SquareMeter;
        result = "";

        isCalculateButtonEnabled = false;
    }

    public void processKeyInTextField() {
        parseInput();
    }

    public enum MeasureOfArea {
        SquareMeter("Square meter"),
        SquareKilometer("Square kilometer"),
        Hectare("Hectare"),
        Are("Are");

        private final String name;

        private MeasureOfArea(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public void setValue(final String value) {
        if (value.equals(this.value)) {
            return;
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public MeasureOfArea getMeasureOfAreaFrom() {
        return measureFrom;
    }

    public void setMeasureOfAreaFrom(final MeasureOfArea measure) {
        if (this.measureFrom != measure) {
            this.measureFrom = measure;
        }
    }

    public MeasureOfArea getMeasureOfAreaTo() {
        return measureTo;
    }

    public void setMeasureOfAreaTo(final MeasureOfArea measure) {
        if (this.measureTo != measure) {
            this.measureTo = measure;
        }
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
            double dresult = countResult();
            result = Double.toString(dresult);
        } catch (IllegalArgumentException e) {
            result = "Result is too much";
        }
    }

    private double countResult() {
        if (measureFrom == MeasureOfArea.SquareMeter) {
            if (measureTo == MeasureOfArea.SquareMeter) {
                return dvalue;
            }
            if (measureTo == MeasureOfArea.SquareKilometer) {
                return Converter.squareMeterToSquareKilometer(dvalue);
            }
            if (measureTo == MeasureOfArea.Hectare) {
                return Converter.squareMeterToHectare(dvalue);
            }
            if (measureTo == MeasureOfArea.Are) {
                return Converter.squareMeterToAre(dvalue);
            }
        }
        if (measureFrom == MeasureOfArea.SquareKilometer) {
            if (measureTo == MeasureOfArea.SquareMeter) {
                return Converter.squareKilometerToSquareMeter(dvalue);
            }
            if (measureTo == MeasureOfArea.SquareKilometer) {
                return dvalue;
            }
            if (measureTo == MeasureOfArea.Hectare) {
                return Converter.squareKilometerToHectare(dvalue);
            }
            if (measureTo == MeasureOfArea.Are) {
                return Converter.squareKilometerToAre(dvalue);
            }
        }
        if (measureFrom == MeasureOfArea.Hectare) {
            if (measureTo == MeasureOfArea.SquareMeter) {
                return Converter.hectareToSquareMeter(dvalue);
            }
            if (measureTo == MeasureOfArea.SquareKilometer) {
                return Converter.hectareToSquareKilometer(dvalue);
            }
            if (measureTo == MeasureOfArea.Hectare) {
                return dvalue;
            }
            if (measureTo == MeasureOfArea.Are) {
                return Converter.hectareToAre(dvalue);
            }
        }
        if (measureFrom == MeasureOfArea.Are) {
            if (measureTo == MeasureOfArea.SquareMeter) {
                return Converter.areToSquareMeter(dvalue);
            }
            if (measureTo == MeasureOfArea.SquareKilometer) {
                return Converter.areToSquareKilometer(dvalue);
            }
            if (measureTo == MeasureOfArea.Hectare) {
                return Converter.areToHectare(dvalue);
            }
            if (measureTo == MeasureOfArea.Are) {
                return dvalue;
            }
        }
        throw new IllegalArgumentException("Unsupported operation");
    }

    private boolean parseInput() {
        try {
            if (!value.isEmpty()) {
                dvalue = Double.parseDouble(value);
                if (Double.isInfinite(dvalue)) {
                    throw new IllegalArgumentException("Input is too much");
                }
            }
            isCalculateButtonEnabled = isInputAvailable();
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

    private boolean isInputAvailable() {
        return !value.isEmpty();
    }
}
