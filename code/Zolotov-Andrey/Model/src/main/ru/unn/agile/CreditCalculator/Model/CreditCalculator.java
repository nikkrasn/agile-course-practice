package ru.unn.agile.CreditCalculator.core;

public class CreditCalculator {

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
