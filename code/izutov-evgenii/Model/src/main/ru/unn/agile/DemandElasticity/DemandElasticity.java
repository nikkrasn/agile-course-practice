package ru.unn.agile.DemandElasticity;

public abstract class DemandElasticity implements IDemandElasticity {
    private final double delta = 0.000001;

    @Override
    public double calculate(final IPositiveRange firstRange, final IPositiveRange secondRange) {
        if (firstRange == null) {
            throw new NullPointerException("firstRange can not be null");
        }

        if (secondRange == null) {
            throw new NullPointerException("secondRange can not be null");
        }

        double firstRangeMidpoint = firstRange.calculateRelativeChangeInMidpoint();
        double secondRangeMidpoint = secondRange.calculateRelativeChangeInMidpoint();

        return firstRangeMidpoint / secondRangeMidpoint;
    }
}
