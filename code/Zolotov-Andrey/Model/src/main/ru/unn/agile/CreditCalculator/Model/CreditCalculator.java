package ru.unn.agile.CreditCalculator.core;

import java.util.*;

public class CreditCalculator {

    public static final int MONTH_IN_YEAR = 12;
    public static final int PERCENT = 100;
    private int sum;
    private char currency;
    private int paymentPeriod;
    private double interestRate;
    private int startMonth;
    private boolean typePayment;

    public CreditCalculator(final int sum,
                            final char currency,
                            final int paymentPeriod,
                            final double interestRate,
                            final int startMonth,
                            final boolean typePayment) {
        this.sum = sum;
        this.currency = currency;
        this.paymentPeriod  = paymentPeriod;
        this.interestRate = interestRate;
        this.startMonth = startMonth;
        this.typePayment = typePayment;
    }

    public double getMonthlyPayment(final int numberOfPayment) {
        double monthPayment;
        if (typePayment) {
            double percentInMonth = interestRate / PERCENT / MONTH_IN_YEAR;
            monthPayment = sum * percentInMonth
                    / (1 - 1 / Math.pow(1 + percentInMonth, paymentPeriod));
            return monthPayment;
        } else {
            double percentInMonth = interestRate / PERCENT / MONTH_IN_YEAR;
            monthPayment = sum / paymentPeriod
                    + sum * (paymentPeriod - numberOfPayment + 1) * percentInMonth / paymentPeriod;
            return monthPayment;
        }
    }

    public double getAllSum() {
        double monthPayment;
        if (typePayment) {
            monthPayment = getMonthlyPayment(1);
            return paymentPeriod * monthPayment;
        } else {
            double percentInMonth = interestRate / PERCENT / MONTH_IN_YEAR;
            monthPayment = sum + sum * percentInMonth * (paymentPeriod + 1) / 2;
            return monthPayment;
        }
    }

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

    public String getSartDateOfPayment() {
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

    public void setTypePayment(final boolean typePayment) {
        this.typePayment = typePayment;
    }

    public boolean getTypePayment() {
        return typePayment;
    }
}
