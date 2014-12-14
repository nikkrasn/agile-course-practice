package ru.unn.agile.CreditCalculator.Model;

import java.util.*;

public  class CreditCalculatorDifferentiated extends CreditCalculator {

    public static class BuilderDifferentiated extends Builder {

        public BuilderDifferentiated(final int sum, final int paymentPeriod) {
            super(sum, paymentPeriod);
        }

        public BuilderDifferentiated currency(final char currency) {
            setCurrencyBuild(currency);
            return this;
        }

        public BuilderDifferentiated interestRate(final double interestRate) {
            setInterestRateBuild(interestRate);
            return this;
        }

        public BuilderDifferentiated startMonth(final int startMonth) {
            setStartMonthBuild(startMonth);
            return this;
        }

        public CreditCalculatorDifferentiated build() {
            return new CreditCalculatorDifferentiated(this);
        }
    }

    public CreditCalculatorDifferentiated(final BuilderDifferentiated builderDifferentiated) {
        super(builderDifferentiated);
    }

    public double getMonthlyPayment(final int numberOfPayment) {
        double monthPayment;
        double percentInMonth = getInterestRate() / PERCENT / MONTH_IN_YEAR;
        monthPayment = getSum() / getPaymentPeriod()
                + getSum() * (getPaymentPeriod() - numberOfPayment + 1)
                * percentInMonth / getPaymentPeriod();
        return monthPayment;
    }

    public double getAllSum() {
        double monthPayment;
        double percentInMonth = getInterestRate() / PERCENT / MONTH_IN_YEAR;
        monthPayment = getSum() + getSum() * percentInMonth * (getPaymentPeriod() + 1) / 2;
        return monthPayment;
    }
}
