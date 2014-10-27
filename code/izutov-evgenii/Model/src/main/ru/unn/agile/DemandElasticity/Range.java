package ru.unn.agile.DemandElasticity;

public class Range implements IRange {
    private float startValue;
    private float endValue;

    public Range() {
        setStartValue(0f);
        setEndValue(0f);
    }

    public Range(final float startValue, final float endValue) {
        setStartValue(startValue);
        setEndValue(endValue);
    }

    @Override
    public float getStartValue() {
        return startValue;
    }

    @Override
    public void setStartValue(final float startValue) {
        this.startValue = startValue;
    }

    @Override
    public float getEndValue() {
        return endValue;
    }

    @Override
    public void setEndValue(final float endValue) {
        this.endValue = endValue;
    }

    @Override
    public float calculateDifference() {
        return endValue - startValue;
    }

    @Override
    public float calculateSum() {
        return endValue + startValue;
    }

    @Override
    public float calculateRelativeChangeInMidpoint() {
        return calculateDifference() / calculateSum() / 2f;
    }
}
