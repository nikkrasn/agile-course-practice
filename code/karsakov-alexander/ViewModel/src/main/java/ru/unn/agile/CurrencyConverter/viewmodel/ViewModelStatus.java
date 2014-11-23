package ru.unn.agile.CurrencyConverter.viewmodel;

public enum ViewModelStatus {
    WAITING("Please enter amount of money"),
    READY("Please select convert mode and press 'Convert'"),
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
