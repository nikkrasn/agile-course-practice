package ru.unn.agile.NumberInPositionalNotation.Model;

public enum Notation {
    BINARY(2, "01."),
    OCTAL(8, "01234567."),
    DECIMAL(10, "0123456789."),
    HEXADECIMAL(16, "0123456789abcdef.");

    private final int base;
    private final String symbols;

    Notation(final int base, final String symbols) {
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

}
