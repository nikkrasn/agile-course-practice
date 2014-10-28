package ru.unn.agile.DemandElasticity;

public interface IPositiveRange {
    double getStartValue();
    void setStartValue(double startValue);
    double getEndValue();
    void setEndValue(double endValue);
    double calculateDifference();
    double calculateSum();
    double calculateRelativeChangeInMidpoint();
    boolean isZeroLengthRange();
}
