package ru.unn.agile.CurrencyConverter.viewmodel;

public enum ViewModelStatus {
    WAITING("Please provide input data"),
    READY("Press 'Calculate' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    private ViewModelStatus(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
