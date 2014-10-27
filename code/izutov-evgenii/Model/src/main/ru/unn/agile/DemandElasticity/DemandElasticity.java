package ru.unn.agile.DemandElasticity;

public abstract class DemandElasticity implements IDemandElasticity {
    @Override
    public float calculate(final IRange firstRange, final IRange secondRange) {
        return firstRange.calculateRelativeChangeInMidpoint() / secondRange.calculateRelativeChangeInMidpoint();
    }
}
