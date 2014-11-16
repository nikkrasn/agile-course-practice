package ru.unn.agile.CreditCalculator.core;

import java.util.*;

public class Builder {

    private int sumBuild;
    private int paymentPeriodBuild;

    private char currencyBuild = 'R';
    private double interestRateBuild = 0.0;
    private int startMonthBuild = 1;

    public Builder(final int sum, final int paymentPeriod) {
        this.sumBuild = sum;
        this.paymentPeriodBuild = paymentPeriod;
    }

    public Builder currency(final char currency) {
        this.currencyBuild = currency;
        return this;
    }

    public Builder interestRate(final double interestRate) {
        this.interestRateBuild = interestRate;
        return this;
    }

    public Builder startMonth(final int startMonth) {
        this.startMonthBuild = startMonth;
        return this;
    }

    public void setSumBuild(final int sum) {
        this.sumBuild = sum;
    }

    public int getSumBuild() {
        return sumBuild;
    }

    public void setCurrencyBuild(final char currency) {
        this.currencyBuild = currency;
    }

    public char getCurrencyBuild() {
        return currencyBuild;
    }

    public void setPaymentPeriodBuild(final int paymentPeriod) {
        this.paymentPeriodBuild = paymentPeriod;
    }

    public int getPaymentPeriodBuild() {
        return paymentPeriodBuild;
    }

    public void setInterestRateBuild(final double interestRate) {
        this.interestRateBuild = interestRate;
    }

    public double getInterestRateBuild() {
        return interestRateBuild;
    }

    public void setStartMonthBuild(final int startMonth) {
        this.startMonthBuild = startMonth;
    }

    public int getStartMonthBuild() {
        return startMonthBuild;
    }
}
