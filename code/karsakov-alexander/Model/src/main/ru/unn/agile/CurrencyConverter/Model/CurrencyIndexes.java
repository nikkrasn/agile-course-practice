package ru.unn.agile.CurrencyConverter.Model;

public enum CurrencyIndexes {
    USD(0, 840),
    EUR(1, 978),
    RUB(2, 1);

    private final int index;
    private final int numCode;

    CurrencyIndexes(final int index, final int numCode) {
        this.index = index;
        this.numCode = numCode;
    }

    public final int getIndex() {
        return index;
    }

    public final int getNumCode() {
        return numCode;
    }
}
