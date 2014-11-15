package ru.unn.agile.DemandElasticity.Model;

public abstract class PositiveRange implements IPositiveRange {
    private static final double DOUBLE = 0.000001;
    private double startValue;
    private double endValue;

    public PositiveRange() {
        setStartValue(0d);
        setEndValue(0d);
    }

    public PositiveRange(final double startValue, final double endValue) {
        setStartValue(startValue);
        setEndValue(endValue);
    }

    @Override
    public double getStartValue() {
        return startValue;
    }

    @Override
    public void setStartValue(final double startValue) {
        if (startValue < 0d) {
            throw new IllegalArgumentException("startValue must be positive");
        }

        this.startValue = startValue;
    }

    @Override
    public double getEndValue() {
        return endValue;
    }

    @Override
    public void setEndValue(final double endValue) {
        if (endValue < 0d) {
            throw new IllegalArgumentException("endValue must be positive");
        }

        this.endValue = endValue;
    }

    @Override
    public double calculateDifference() {
        return endValue - startValue;
    }

    @Override
    public double calculateSum() {
        return endValue + startValue;
    }

    @Override
    public double calculateRelativeChangeInMidpoint() {
        double difference = calculateDifference();
        double sum = calculateSum();

        if (Math.abs(sum) < DOUBLE && Math.abs(difference) < DOUBLE) {
            throw new ArithmeticException("division zero by zero");
        }

        return difference / (sum / 2d);
    }

    @Override
    public boolean isZeroLengthRange() {
        return Math.abs(startValue - endValue) < DOUBLE;
    }
}
