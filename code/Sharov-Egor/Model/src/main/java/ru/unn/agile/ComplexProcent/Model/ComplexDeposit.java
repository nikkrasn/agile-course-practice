package ru.unn.agile.ComplexProcent.Model;

import java.util.GregorianCalendar;

public class ComplexDeposit {
    private static final double ENTIRE_PERCENT = 100;
    private static final int PRIME_NUMBER = 31;
    private static final int MLS_IN_DAY = 24 * 60 * 60 * 1000;
    private static final int DAYS_IN_YEAR = 365;
    private double base;
    private double percent;
    private int interestCountInYear;
    private GregorianCalendar startDate;
    private GregorianCalendar finishDate;

    public ComplexDeposit(final String base, final String percent, final String interestCount) {
        setBase(base);
        setPercent(percent);
        setInterestCountInYear(interestCount);
    }

    public ComplexDeposit() {
        base = 0;
        percent = 0;
        interestCountInYear = 1;
    }

    public double getCapitalizedBase() {
        return base * capitalizedPercentsFullInterest() * restPercents();
    }

    public double getBase() {
        return base;
    }


    public ComplexDeposit setBase(final String base) {
        this.base = Double.parseDouble(base);
        return this;
    }

    public double getPercent() {
        return percent;
    }

    public ComplexDeposit setPercent(final String percent) {
        this.percent = Double.parseDouble(percent) / ENTIRE_PERCENT;
        return this;
    }

    public double getInterestCountInYear() {
        return interestCountInYear;
    }

    public ComplexDeposit setInterestCountInYear(final String interestCountInYear) {
        this.interestCountInYear = Integer.parseInt(interestCountInYear);
        return this;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public ComplexDeposit setStartDate(final GregorianCalendar startDate) {
        this.startDate = startDate;
       return this;
    }

    public GregorianCalendar getFinishDate() {
        return finishDate;
    }

    public ComplexDeposit setFinishDate(final GregorianCalendar finishDate) {
        this.finishDate = finishDate;
        return this;
    }

    @Override
    public int hashCode() {
        int result = PRIME_NUMBER + (int) base;
        result = PRIME_NUMBER * result + (int) percent;
        result = PRIME_NUMBER * result + (int) percent;
        return result;
    }

    @Override
    public boolean equals(final Object depositObject) {
        if (depositObject == null) { return false; }
        if (depositObject == this) { return true; }
        if (!(depositObject instanceof ComplexDeposit)) { return false; }
        ComplexDeposit deposit = (ComplexDeposit) depositObject;
        return isSameBase(deposit) && isSamePercent(deposit) && isSameInterestCount(deposit);
    }

    private int accrualCount() {
       return (int) (dayDifference() / getDaysInInterest());
    }

    private boolean isSameBase(final ComplexDeposit depositToCompare) {
        return getBase() == depositToCompare.getBase();
    }

    private boolean isSamePercent(final ComplexDeposit depositToCompare) {
        return getPercent() == depositToCompare.getPercent();
    }

    private boolean isSameInterestCount(final ComplexDeposit depositToCompare) {
        return getInterestCountInYear() == depositToCompare.getInterestCountInYear();
    }

    private double getPercentsInOnePeriod() {
        return getPercent() / getInterestCountInYear();
    }

    private long getDaysInInterest() {
        return DAYS_IN_YEAR / interestCountInYear;
    }

    private int dayDifference() {
        return (int) ((finishDate.getTimeInMillis() - startDate.getTimeInMillis()) / MLS_IN_DAY);
    }

    private int getLastDays() {
       return (int) (dayDifference() % getDaysInInterest());
    }

    private double restPercents() {
        int unCapitalisedDays = getLastDays();
            return getPercentsInOnePeriod() * unCapitalisedDays / getDaysInInterest() + 1;
    }

    private double capitalizedPercentsFullInterest() {
       return Math.pow(getPercentsInOnePeriod() + 1, accrualCount());
    }
}
