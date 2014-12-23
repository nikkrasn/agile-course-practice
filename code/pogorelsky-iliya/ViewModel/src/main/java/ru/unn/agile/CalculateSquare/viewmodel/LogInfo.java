package ru.unn.agile.CalculateSquare.viewmodel;

final class LogInfo {
    public static final String PRESSED_CALCULATE = "Calculate. ";
    public static final String CHANGED_OPERATION = "Operation was changed to ";
    public static final String FINIS_EDITD = "Updated input. ";

    private LogInfo() { }
}

enum MessageStatus {
    WAITING("Please provide input data"),
    READY("Press 'Calculate' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    private MessageStatus(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
