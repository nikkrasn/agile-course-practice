package ru.unn.agile.CreditCalculator.Model;

import java.util.*;

public abstract class CreditCalculator {

    public static final int MONTH_IN_YEAR = 12;
    public static final int PERCENT = 100;

    private int sum;
    private int paymentPeriod;
    private char currency;
    private double interestRate;
    private int startMonth;

    public CreditCalculator(final Builder builder) {
        sum = builder.getSumBuild();
        paymentPeriod = builder.getPaymentPeriodBuild();
        currency = builder.getCurrencyBuild();
        interestRate = builder.getInterestRateBuild();
        startMonth = builder.getStartMonthBuild();
    }


    public abstract double getMonthlyPayment(final int numberOfPayment);

    public abstract double getAllSum();

    public double getOverPayment() {
        return getAllSum() - sum;
    }

    public String getFinishDateOfPayment() {
        Calendar c = Calendar.getInstance();
        int finishYear = c.get(c.YEAR) + (paymentPeriod + startMonth) / MONTH_IN_YEAR;
        int month = (paymentPeriod + startMonth) % MONTH_IN_YEAR;
        if (month == 0) {
            month = MONTH_IN_YEAR;
            finishYear--;
        }
        return "" + month + "." + finishYear + "";
    }

    public String getStartDateOfPayment() {
        Calendar c = Calendar.getInstance();
        int startYear = c.get(c.YEAR);
        int startPaymentMonth = startMonth + 1;
        if (startMonth == MONTH_IN_YEAR) {
            startPaymentMonth = 1;
            startYear++;
        }
        return "" + startPaymentMonth + "." + startYear + "";
    }

    public void setSum(final int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }

    public void setCurrency(final char currency) {
        this.currency = currency;
    }

    public char getCurrency() {
        return currency;
    }

    public void setPaymentPeriod(final int paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public int getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setInterestRate(final double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setStartMonth(final int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartMonth() {
        return startMonth;
    }
}
