package ru.unn.agile.DemandElasticity.viewmodel;

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Calculate' or Enter"),
    NOT_NUMBER("Bad number format"),
    NOT_POSITIVE("Not positive value"),
    WRONG_ARGUMENTS("Wrong arguments"),
    SUCCESS("Success");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
