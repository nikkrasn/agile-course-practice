package ru.unn.agile.ComplexNumber.viewmodel.legacy;

import ru.unn.agile.ComplexNumber.model.legacy.ComplexNumber;

public class ViewModel {
    private String re1;
    private String im1;
    private String re2;
    private String im2;
    private Operation operation;
    private String result;
    private String status;
    private boolean isCalculateButtonEnabled;

    public ViewModel() {
        re1 = "";
        im1 = "";
        re2 = "";
        im2 = "";
        operation = Operation.ADD;
        result = "";
        status = Status.WAITING;

        isCalculateButtonEnabled = false;
    }

    public void processKeyInTextField(final int keyCode) {
        parseInput();

        if (keyCode == KeyboardKeys.ENTER) {
            enterPressed();
        }
    }

    private void enterPressed() {

        if (isCalculateButtonEnabled()) {
            calculate();
        }
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    private boolean isInputAvailable() {
        return !re1.isEmpty() && !im1.isEmpty() && !re2.isEmpty() && !im2.isEmpty();
    }

    private boolean parseInput() {
        try {
            if (!re1.isEmpty()) {
                Double.parseDouble(re1);
            }
            if (!im1.isEmpty()) {
                Double.parseDouble(im1);
            }
            if (!re2.isEmpty()) {
                Double.parseDouble(re2);
            }
            if (!im2.isEmpty()) {
                Double.parseDouble(im2);
            }
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isCalculateButtonEnabled = false;
            return false;
        }

        isCalculateButtonEnabled = isInputAvailable();
        if (isCalculateButtonEnabled) {
            status = Status.READY;
        } else {
            status = Status.WAITING;
        }

        return isCalculateButtonEnabled;
    }

    public void calculate() {
        if (!parseInput()) {
            return;
        }

        ComplexNumber z1 = new ComplexNumber(re1, im1);
        ComplexNumber z2 = new ComplexNumber(re2, im2);
        ComplexNumber z3;

        switch (operation) {
            case ADD:
                z3 = z1.add(z2);
                break;
            case MULTIPLY:
                z3 = z1.multiply(z2);
                break;
            default:
                throw new IllegalArgumentException("Only ADD and MULTIPLY are supported");
        }

        result = z3.toString();
        status = Status.SUCCESS;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(final Operation operation) {
            this.operation = operation;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    public String getRe1() {
        return re1;
    }

    public void setRe1(final String re1) {
        if (re1.equals(this.re1)) {
            return;
        }

        this.re1 = re1;
    }

    public String getIm1() {
        return im1;
    }

    public void setIm1(final String im1) {
        if (im1.equals(this.im1)) {
            return;
        }

        this.im1 = im1;
    }

    public String getRe2() {
        return re2;
    }

    public void setRe2(final String re2) {
        if (re2.equals(this.re2)) {
            return;
        }

        this.re2 = re2;
    }

    public String getIm2() {
        return im2;
    }

    public void setIm2(final String im2) {
        if (im2.equals(this.im2)) {
            return;
        }

        this.im2 = im2;
    }

    public enum Operation {
        ADD("Add"),
        MULTIPLY("Mul");
        private final String name;

        private Operation(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public final class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calculate' or Enter";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";

        private Status() { }
    }
}
