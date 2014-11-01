package ru.unn.agile.ComplexProcent.Model;

public class ComplexDeposit {
    private double base;
    private double percent;
    private double interestCountInYear;
    private static final double ENTIRE_PERCENT = 100;

    public ComplexDeposit(final double base, final double percent, final double interestCount) {
        this.setBase(base);
        this.setPercent(percent);
        this.setInterestCountInYear(interestCount);
    }

    private double accrualCount(final int years) {
        return years * this.getInterestCountInYear();
    }

    private double getPercentsInOnePeriod() {
        return 1 + this.getPercent() / this.getInterestCountInYear();
    }

    private double capitalizedPercents(final int years) {
        return Math.pow(getPercentsInOnePeriod(), accrualCount(years));
    }

    public double getCapitalizedBase(final int years) {
        return this.getBase() * capitalizedPercents(years);
    }

    public double getBase() {
        return base;
    }

    public ComplexDeposit setBase(final double base) {
        this.base = base;
        return this;
    }

    public double getPercent() {
        return percent;
    }

    public ComplexDeposit setPercent(final double percent) {
        this.percent = percent / ENTIRE_PERCENT;
        return this;
    }

    public double getInterestCountInYear() {
        return interestCountInYear;
    }


    public ComplexDeposit setInterestCountInYear(final double interestCountInYear) {
        this.interestCountInYear = interestCountInYear;
        return this;
    }
}
