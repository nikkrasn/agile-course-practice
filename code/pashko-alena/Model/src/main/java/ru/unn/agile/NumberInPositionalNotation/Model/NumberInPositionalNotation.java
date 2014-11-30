package ru.unn.agile.NumberInPositionalNotation.Model;
import static ru.unn.agile.NumberInPositionalNotation.Model.Notation.*;

public class NumberInPositionalNotation {
    private final String value;
    private final Notation notation;

    public NumberInPositionalNotation(final String val, final Notation not) {
        this.value = val;
        this.notation = not;
    }

    public NumberInPositionalNotation(final String val) {
        this.value = val;
        this.notation = DECIMAL;
    }

    public NumberInPositionalNotation() {
        this.value = "0";
        this.notation = DECIMAL;
    }

    @Override
    public int hashCode() {
        final int shift = 32;
        int result = shift + (value == null ? 0 : value.hashCode());
        result = (shift - 1) * result + ((notation == null) ? 0 : notation.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof NumberInPositionalNotation)) {
            return false;
        }
        NumberInPositionalNotation number = (NumberInPositionalNotation) object;
        return this.hashCode() == object.hashCode();
    }

    public String getValue() {
        return value;
    }

    public Notation getNotation() {
        return notation;
    }

    public NumberInPositionalNotation convertFromDecimal(final Notation notation) {
        if (checkInputData()) {
            String [] parts = getValue().split("\\.");
            int indexOfPoint = getValue().indexOf('.');
            int integerPart = Integer.parseInt(parts[0]);
            String fractionalPart = "0";
            if (indexOfPoint >= 0) {
                fractionalPart = parts[1];
            }
            int lengthOfFractionalPart = fractionalPart.length();
            int fractionalPartNumber = (int) (Integer.parseInt(fractionalPart)
                    * Math.pow(notation.getBase(), lengthOfFractionalPart)
                    /  Math.pow(DECIMAL.getBase(), lengthOfFractionalPart));
            String integerPartResult = parts[0];
            String fractionalPartResult = fractionalPart;

            switch (notation) {
                case BINARY: integerPartResult = Integer.toBinaryString(integerPart);
                    fractionalPartResult = Integer.toBinaryString(fractionalPartNumber);
                    break;
                case OCTAL: integerPartResult = Integer.toOctalString(integerPart);
                    fractionalPartResult = Integer.toOctalString(fractionalPartNumber);
                    break;
                case HEXADECIMAL: integerPartResult = Integer.toHexString(integerPart);
                    fractionalPartResult = Integer.toHexString(fractionalPartNumber);
                    break;
                default: break;
            }

            fractionalPartResult = convertFractionalPart(fractionalPartResult,
                    lengthOfFractionalPart);
            String outputValue = integerPartResult
                    + fractionalPartResult.substring(fractionalPartResult.indexOf('.'),
                    fractionalPartResult.length());

            return new NumberInPositionalNotation(outputValue, notation);
        } else {
            return new NumberInPositionalNotation(getValue(), getNotation());
        }
    }

    public NumberInPositionalNotation convertToDecimal() {
        if (checkInputData()
                && getNotation() != DECIMAL) {
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
            double convertedValue = Integer.parseInt(integerPart, getNotation().getBase())
                    + Integer.parseInt(fractionalPart, getNotation().getBase())
                    / Math.pow(this.notation.getBase(), lengthOfFractionalPart);
            String convertedValueString = Double.toString(convertedValue);
            return new NumberInPositionalNotation(convertedValueString, DECIMAL);
        } else {
            return new NumberInPositionalNotation(getValue(), getNotation());
        }
    }

    public boolean checkInputData() {
        int numberOfSymbols;
        String symbols;
        symbols = getNotation().getSymbols();
        numberOfSymbols = getNotation().getBase() + 1;
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

    public NumberInPositionalNotation convertToNotation(final Notation notation) {
        if (checkInputData()) {
            NumberInPositionalNotation out;
            if (getNotation() == notation) {
                out = new NumberInPositionalNotation(getValue(), getNotation());
            } else if (getNotation() == DECIMAL) {
                out = convertFromDecimal(notation);
            } else {
                out = convertToDecimal();
                if (notation != DECIMAL) {
                    out = out.convertFromDecimal(notation);
                }
            }
            return out;
        } else {
            return new NumberInPositionalNotation(getValue(), getNotation());
        }
    }

    private String convertFractionalPart(final String fractPart, final int len) {
        int length = len;
        String fractionalPart = fractPart;
        int lengthOfFract = fractionalPart.length();
        while (lengthOfFract > 1 && fractionalPart.substring(lengthOfFract - 1,
                lengthOfFract).equals("0")) {
            fractionalPart =
                    fractionalPart.substring(0, fractionalPart.length() - 1);
            length--;
            lengthOfFract--;
        }
        for (int i = 0; i < length - lengthOfFract; i++) {
            fractionalPart = "0" + fractionalPart;
        }
        fractionalPart = "0." + fractionalPart;
        return fractionalPart;
    }
}
