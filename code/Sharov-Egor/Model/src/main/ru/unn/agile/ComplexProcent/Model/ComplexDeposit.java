package ru.unn.agile.ComplexProcent.Model;

public class ComplexDeposit {
    private double base;
    private double percent;
    private double interestCountInYear;
    private static final double ENTIRE_PERCENT = 100;

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

    public ComplexDeposit(final double base, final double percent, final double interestCount) {
        this.setBase(base);
        this.setPercent(percent);
        this.setInterestCountInYear(interestCount);
    }

    public boolean isEqualDeposit(final ComplexDeposit deposit) {
        if (isSameBase(deposit) && isSamePercent(deposit) && isSameInterestCount(deposit)) {
            return true;
        }
        return false;
    }

    private double accrualCount(final int years) {
        return years * this.getInterestCountInYear();
    }

    private boolean isSameBase(final ComplexDeposit depositToCompare) {
        if (this.getBase() == depositToCompare.getBase()) {
            return true;
        }
        return false;
    }

    private boolean isSamePercent(final ComplexDeposit depositToCompare) {
        if (this.getPercent() == depositToCompare.getPercent()) {
            return true;
        }
        return false;
    }

    private boolean isSameInterestCount(final ComplexDeposit depositToCompare) {
        if (this.getInterestCountInYear() == depositToCompare.getInterestCountInYear()) {
            return true;
        }
        return false;
    }

    private double getPercentsInOnePeriod() {
        return 1 + this.getPercent() / this.getInterestCountInYear();
    }

    private double capitalizedPercents(final int years) {
        return Math.pow(getPercentsInOnePeriod(), accrualCount(years));
    }
}
