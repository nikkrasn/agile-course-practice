package ru.unn.agile.DemandElasticity;

public abstract class DemandElasticityMethod<TFirst extends IPositiveRange, TSecond extends IPositiveRange, TOutput extends Enum> implements IDemandElasticityMethod<TFirst, TSecond, TOutput> {
    private final double delta = 0.000001;

    protected double getDelta() {
        return delta;
    }

    @Override
    public Coefficient<TOutput> calculate(final TFirst firstRange, final TSecond secondRange) {
        if (firstRange == null) {
            throw new NullPointerException("firstRange can not be null");
        }

        if (secondRange == null) {
            throw new NullPointerException("secondRange can not be null");
        }

        double firstMidpoint = firstRange.calculateRelativeChangeInMidpoint();
        double secondMidpoint = secondRange.calculateRelativeChangeInMidpoint();

        Coefficient<TOutput> coefficient;
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

    protected abstract Coefficient<TOutput> createUndefinedCoefficient();

    protected abstract Coefficient<TOutput> createInfiniteCoefficient(final double firstMidpoint);

    protected abstract Coefficient<TOutput> createFiniteCoefficient(final double coefficientValue);

    private boolean isInfiniteValue(final double firstMidpoint, final double secondMidpoint) {
        return (Math.abs(firstMidpoint) >= delta) && (Math.abs(secondMidpoint) < delta);
    }

    private boolean isUndefinedValue(final double firstMidpoint, final double secondMidpoint) {
        return (Math.abs(firstMidpoint) < delta) && (Math.abs(secondMidpoint) < delta);
    }
}
