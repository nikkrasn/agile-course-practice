package ru.unn.agile.NumberInPositionalNotation.Model;
import static ru.unn.agile.NumberInPositionalNotation.Model.Notations.*;

public class NumberInPositionalNotation {
    private final String value;
    private final int notation;

    public NumberInPositionalNotation(final String val, final int not) {
        this.value = val;
        this.notation = not;
    }

    public NumberInPositionalNotation(final String val) {
        this.value = val;
        this.notation = DECIMAL_NOTATION.getBase();
    }

    public NumberInPositionalNotation() {
        this.value = "0";
        this.notation = DECIMAL_NOTATION.getBase();
    }

    @Override
    public int hashCode() {
        final int shift = 32;
        int result = shift + (value == null ? 0 : value.hashCode());
        result = (shift - 1) * result + (int) (notation ^ (notation >>> shift));
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        NumberInPositionalNotation number = (NumberInPositionalNotation) object;
        return number.getNotation() == getNotation() && number.getValue().equals(getValue());
    }

    public String getValue() {
        return value;
    }

    public int getNotation() {
        return notation;
    }

    public NumberInPositionalNotation convertFromDecimal(final int notation) {
        if (checkInputData(notation) && getNotation() == DECIMAL_NOTATION.getBase()) {
            String [] parts = getValue().split("\\.");
            int indexOfPoint = getValue().indexOf('.');
            int integerPart = Integer.parseInt(parts[0]);
            String fractionalPart = "0";
            if (indexOfPoint >= 0) {
                fractionalPart = parts[1];
            }
            int lengthOfFractionalPart = fractionalPart.length();
            int fractionalPartNumber = (int) (Integer.parseInt(fractionalPart)
                    * Math.pow(notation, lengthOfFractionalPart
            )  / Math.pow(DECIMAL_NOTATION.getBase(), lengthOfFractionalPart));
            String integerPartResult = parts[0];
            String fractionalPartResult = fractionalPart;

            Notations not = Notations.create(notation);
            switch (not) {
                case BINARY_NOTATION: integerPartResult = Integer.toBinaryString(integerPart);
                    fractionalPartResult = Integer.toBinaryString(fractionalPartNumber);
                    break;
                case OCTAL_NOTATION: integerPartResult = Integer.toOctalString(integerPart);
                    fractionalPartResult = Integer.toOctalString(fractionalPartNumber);
                    break;
                case HEXADECIMAL_NOTATION: integerPartResult = Integer.toHexString(integerPart);
                    fractionalPartResult = Integer.toHexString(fractionalPartNumber);
                    break;
                default:
                    break;
            }

            int lengthOfFract = fractionalPartResult.length();
            while (lengthOfFract > 1 && fractionalPartResult.substring(lengthOfFract - 1,
                    lengthOfFract).equals("0")) {
                fractionalPartResult =
                        fractionalPartResult.substring(0, fractionalPartResult.length() - 1);
                lengthOfFractionalPart--;
                lengthOfFract--;
            }
            for (int i = 0; i < lengthOfFractionalPart - lengthOfFract; i++) {
                fractionalPartResult = "0" + fractionalPartResult;
            }
            fractionalPartResult = "0." + fractionalPartResult;
            String outputValue = integerPartResult
                    + fractionalPartResult.substring(fractionalPartResult.indexOf('.'),
                    fractionalPartResult.length());

            return new NumberInPositionalNotation(outputValue, notation);
        } else {
            return new NumberInPositionalNotation(getValue(), getNotation());
        }
    }

    public NumberInPositionalNotation convertToDecimal() {
        if (checkInputData(DECIMAL_NOTATION.getBase())
                && getNotation() != DECIMAL_NOTATION.getBase()) {
            int indexOfPoint = getValue().indexOf('.');
            String [] parts = getValue().split("\\.");
            String integerPart = parts[0];
            String fractionalPart;
            if (indexOfPoint < 0) {
                fractionalPart = "0";
            } else {
                fractionalPart = parts[1];
            }
            int lengthOfFractionalPart = fractionalPart.length();
            double convertedValue = Integer.parseInt(integerPart, getNotation())
                    + Integer.parseInt(fractionalPart, getNotation())
                    / Math.pow(this.notation, lengthOfFractionalPart);
            String convertedValueString = Double.toString(convertedValue);
            return new NumberInPositionalNotation(convertedValueString, DECIMAL_NOTATION.getBase());
        } else {
            return new NumberInPositionalNotation(getValue(), getNotation());
        }
    }

    private boolean checkNotation(final int notation) {
        if (notation != BINARY_NOTATION.getBase() && notation != OCTAL_NOTATION.getBase()
                && notation != DECIMAL_NOTATION.getBase()
                && notation != HEXADECIMAL_NOTATION.getBase()) {
            return false;
        }
        return true;
    }

    private boolean checkInputData(final int notation) {
        int numberOfSymbols;
        String symbols;
        if (!checkNotation(notation) || !checkNotation(getNotation())) {
            return false;
        }
        Notations not = Notations.create(getNotation());
        symbols = not.getSymbols();
        numberOfSymbols = not.getNumberOfSymbols();
        int count = 0;
        for (int i = 1; i < (getValue()).length() + 1; i++) {
            count = 0;
            for (int j = 1; j < numberOfSymbols + 1; j++) {
                if (!getValue().substring(i - 1, i).equals(symbols.substring(j - 1, j))) {
                    count++;
                }
                if (count == numberOfSymbols) {
                    return false;
                }

            }
        }
        return true;
    }

    public NumberInPositionalNotation convertToNotation(final int notation) {
        if (checkInputData(notation)) {
            NumberInPositionalNotation out;
            if (getNotation() == notation) {
                out = new NumberInPositionalNotation(getValue(), getNotation());
            } else if (getNotation() == DECIMAL_NOTATION.getBase()) {
                out = convertFromDecimal(notation);
            } else {
                out = convertToDecimal();
                if (notation != DECIMAL_NOTATION.getBase()) {
                    out = out.convertFromDecimal(notation);
                }
            }
            return out;
        } else {
            return new NumberInPositionalNotation(getValue(), getNotation());
        }
    }
}
