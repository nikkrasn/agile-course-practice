package ru.unn.agile.DemandElasticity;

public interface IRange {
    float getStartValue();
    void setStartValue(float startValue);
    float getEndValue();
    void setEndValue(float endValue);
    float calculateDifference();
    float calculateSum();
    float calculateRelativeChangeInMidpoint();
}
