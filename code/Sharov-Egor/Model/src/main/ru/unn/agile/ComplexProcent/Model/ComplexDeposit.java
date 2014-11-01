package ru.unn.agile.ComplexProcent.Model;

public class ComplexDeposit {
    private double base;
    private double percent;
    private double interestCountInYear;

    public ComplexDeposit(final double depositBase, final double depositPercent, final double depositInterestCount) {
        this.setBase(depositBase);
        this.setPercent(depositPercent);
        this.setInterestCountInYear(depositInterestCount);
    }

    public double getCapitalizedBase(int years) {
        return this.getBase() * Math.pow((1 + (this.getPercent() / this.getInterestCountInYear())), years * this.getInterestCountInYear());
    }


    public double getBase() {
        return base;
    }

    public ComplexDeposit setBase(double base) {
        this.base = base;
        return this;
    }

    public double getPercent() {
        return percent;
    }

    public ComplexDeposit setPercent(double percent) {
        this.percent = percent / 100;
        return this;
    }

    public double getInterestCountInYear() {
        return interestCountInYear;
    }


    public ComplexDeposit setInterestCountInYear(double interestCountInYear) {
        this.interestCountInYear = interestCountInYear;
        return this;
    }
}