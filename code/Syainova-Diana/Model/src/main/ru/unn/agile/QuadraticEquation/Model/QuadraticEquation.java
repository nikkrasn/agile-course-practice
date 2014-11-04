package ru.unn.agile.QuadraticEquation.Model;

public class QuadraticEquation {
    private double firstCoefficient;
    private double secondCoefficient;
    private double freeTerm;
    private static final double ZERO = 0.0;
    private static final double TWO = 2.0;
    private static final double FOUR = 4.0;

    public QuadraticEquation(final double coefficientFirst,
                             final double coefficientSecond,
                             final double termFree) {
        setFirstCoefficientEquation(coefficientFirst);
        setSecondCoefficientEquation(coefficientSecond);
        setFreeTermEquation(termFree);
    }

    public void setFirstCoefficientEquation(final double coefficientFirst) {
        if (coefficientFirst == ZERO) {
            throw new IllegalArgumentException(
                    "The first coefficient can't be null in quadratic equation");
        } else {
            this.firstCoefficient = coefficientFirst;
        }
    }

    public void setSecondCoefficientEquation(final double coefficientSecond) {
        this.secondCoefficient = coefficientSecond;
    }

    public void setFreeTermEquation(final double termFree) {
        this.freeTerm = termFree;
    }

    public double getDiscriminant() {
        return secondCoefficient * secondCoefficient - FOUR * firstCoefficient * freeTerm;
    }

    public double getFirstRoot() {
        double discriminant = getDiscriminant();
        if (discriminant >= ZERO) {
            return (-secondCoefficient + Math.sqrt(discriminant)) / (TWO * firstCoefficient);
        } else {
            throw new IllegalArgumentException(
                    "The quadratic equation don't have real roots with Discriminant < 0 ");
        }
    }

    public double getSecondRoot() {
        double discriminant = getDiscriminant();
        if (discriminant >= ZERO) {
            return (-secondCoefficient - Math.sqrt(discriminant)) / (TWO * firstCoefficient);
        } else {
            throw new IllegalArgumentException(
                    "The quadratic equation don't have real roots with Discriminant < 0");
        }
    }

    public double[] getTwoRoots() {
        double discriminant = getDiscriminant();
        return new double [] {getFirstRoot(), getSecondRoot()};
    }
}
