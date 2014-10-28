package ru.unn.agile.DemandElasticity.Model;

public abstract class PositiveRange implements IPositiveRange {
    private final double delta = 0.000001;
    private double startValue;
    private double endValue;

    public PositiveRange() {
        setStartValue(0);
        setEndValue(0);
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
        if (startValue < 0) {
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
        if (endValue < 0) {
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

        if ((Math.abs(sum) < delta) && (Math.abs(difference) < delta)) {
            throw new ArithmeticException("division zero by zero");
        }

        return difference / (sum / 2);
    }

    @Override
    public boolean isZeroLengthRange() {
        return Math.abs(startValue - endValue) < delta;
    }
}
