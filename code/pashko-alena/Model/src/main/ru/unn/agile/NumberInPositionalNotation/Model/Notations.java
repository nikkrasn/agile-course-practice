package ru.unn.agile.NumberInPositionalNotation.Model;

public enum Notations {
    BINARY_NOTATION(2, "01."),
    OCTAL_NOTATION(8, "01234567."),
    DECIMAL_NOTATION(10, "0123456789."),
    HEXADECIMAL_NOTATION(16, "0123456789abcdef.");

    private final int base;
    private final String symbols;

    Notations(final int base, final String symbols) {
        this.base = base;
        this.symbols = symbols;
    }
    public int getBase() {
        return base;
    }

    public int getNumberOfSymbols() {
        return base + 1;
    }

    public String getSymbols() {
        return symbols;
    }

    public static Notations create(final int base) {
        if (base == BINARY_NOTATION.getBase()) {
            return Notations.BINARY_NOTATION;
        } else if (base == OCTAL_NOTATION.getBase()) {
            return Notations.OCTAL_NOTATION;
        }  else if (base == DECIMAL_NOTATION.getBase()) {
            return Notations.DECIMAL_NOTATION;
        } else {
            return Notations.HEXADECIMAL_NOTATION;
        }
    }

}
