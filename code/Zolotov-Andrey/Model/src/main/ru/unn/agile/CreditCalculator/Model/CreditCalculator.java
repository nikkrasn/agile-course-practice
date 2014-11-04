package ru.unn.agile.CreditCalculator.core;

public class CreditCalculator {

    public static final int MONTH_IN_YEAR = 12;
    public static final int PROCENT = 100;
    private int summ;
    private char currency;
    private int time;
    private double percent;
    private int startMonth;
    private boolean typePayment;

    public CreditCalculator(final int summa,
                            final char currency,
                            final int time,
                            final double percent,
                            final int startMonth,
                            final boolean typePayment) {
        this.summ = summa;
        this.currency = currency;
        this.time = time;
        this.percent = percent;
        this.startMonth = startMonth;
        this.typePayment = typePayment;
    }

    public double monthlyPayment(final int monthDifPay) {
        double monthPayment;
        if (typePayment) {
            double percentInMonth = percent / PROCENT / MONTH_IN_YEAR;
            monthPayment = summ * percentInMonth / (1 - 1 / Math.pow(1 + percentInMonth, time));
            return monthPayment;
        } else {
            double percentInMonth = percent / PROCENT / MONTH_IN_YEAR;
            monthPayment = summ / time + summ * (time - monthDifPay + 1) * percentInMonth / time;
            return monthPayment;
        }
    }

    public double allSumm() {
        double monthPayment;
        if (typePayment) {
            monthPayment = this.monthlyPayment(1);
            return time * monthPayment;
        } else {
            double percentInMonth = percent / PROCENT / MONTH_IN_YEAR;
            monthPayment = summ + summ * percentInMonth * (time + 1) / 2;
            return monthPayment;
        }
    }

    public double overPayment() {
        return this.allSumm() - summ;
    }

    public void setSumm(final int summ) {

        this.summ = summ;
    }

    public int getSumm() {
        return summ;
    }

    public void setCurrency(final char currency) {

        this.currency = currency;
    }

    public char getCurrency() {
        return currency;
    }

    public void setTime(final int time) {

        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setPercent(final double percent) {

        this.percent = percent;
    }

    public double getPercent() {
        return percent;
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
