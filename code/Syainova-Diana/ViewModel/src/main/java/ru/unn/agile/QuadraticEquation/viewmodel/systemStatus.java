package ru.unn.agile.QuadraticEquation.viewmodel;

enum systemStatus {
    WAITING("Please provide input data"),
    READY("Press 'solve the equation' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success"),
    INCORRECT_COEF("The first coefficient can't be null"),
    NO_ROOTS("The quadratic equation hasn't real roots");

    private final String name;

    private systemStatus(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
