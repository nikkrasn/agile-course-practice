package ru.unn.agile.DemandElasticity.Model;

public abstract class DemandElasticityMethod implements IDemandElasticityMethod {
    private static final double DELTA = 0.000001;

    protected double getDelta() {
        return DELTA;
    }

    @Override
    public Coefficient calculate(final IPositiveRange first, final IPositiveRange second) {
        testArgumentsOnNull(first, second);
        testArgumentsOnType(first, second);

        double firstMidpoint = first.calculateRelativeChangeInMidpoint();
        double secondMidpoint = second.calculateRelativeChangeInMidpoint();

        Coefficient coefficient;
        if (isUndefinedValue(firstMidpoint, secondMidpoint)) {
            coefficient = createUndefinedCoefficient();
        } else if (isInfiniteValue(firstMidpoint, secondMidpoint)) {
            coefficient = createInfiniteCoefficient(firstMidpoint);
        } else {
            double coefficientValue = firstMidpoint / secondMidpoint;
            coefficient =  createFiniteCoefficient(coefficientValue);
        }
        return coefficient;
    }

    protected abstract void testArgumentsOnType(IPositiveRange first, IPositiveRange second);

    protected abstract Coefficient createUndefinedCoefficient();

    protected abstract Coefficient createInfiniteCoefficient(final double firstMidpoint);

    protected abstract Coefficient createFiniteCoefficient(final double coefficientValue);

    private boolean isInfiniteValue(final double firstMidpoint, final double secondMidpoint) {
        return Math.abs(firstMidpoint) >= DELTA && Math.abs(secondMidpoint) < DELTA;
    }

    private boolean isUndefinedValue(final double firstMidpoint, final double secondMidpoint) {
        return Math.abs(firstMidpoint) < DELTA && Math.abs(secondMidpoint) < DELTA;
    }

    private void testArgumentsOnNull(final IPositiveRange first, final IPositiveRange second) {
        if (first == null) {
            throw new NullPointerException("first range can not be null");
        }

        if (second == null) {
            throw new NullPointerException("second range can not be null");
        }
    }
}
