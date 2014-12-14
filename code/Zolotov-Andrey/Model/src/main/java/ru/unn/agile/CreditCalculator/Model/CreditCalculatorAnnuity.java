package ru.unn.agile.CreditCalculator.Model;

import java.util.*;

public  class CreditCalculatorAnnuity extends CreditCalculator {

    public static class BuilderAnnuity extends Builder {

        public BuilderAnnuity(final int sum, final int paymentPeriod) {
            super(sum, paymentPeriod);
        }

        public BuilderAnnuity currency(final char currency) {
            setCurrencyBuild(currency);
            return this;
        }

        public BuilderAnnuity interestRate(final double interestRate) {
            setInterestRateBuild(interestRate);
            return this;
        }

        public BuilderAnnuity startMonth(final int startMonth) {
            setStartMonthBuild(startMonth);
            return this;
        }

        public CreditCalculatorAnnuity build() {
            return new CreditCalculatorAnnuity(this);
        }
    }

    public CreditCalculatorAnnuity(final BuilderAnnuity builderAnnuity) {
        super(builderAnnuity);
    }

    @Override
    public double getMonthlyPayment(final int numberOfPayment) {
        double monthPayment;
        double percentInMonth = getInterestRate() / PERCENT / MONTH_IN_YEAR;
        monthPayment = getSum() * percentInMonth
                / (1 - 1 / Math.pow(1 + percentInMonth, getPaymentPeriod()));
        return monthPayment;
    }

    @Override
    public double getAllSum() {
        double monthPayment;
        monthPayment = getMonthlyPayment(1);
        return getPaymentPeriod() * monthPayment;
    }
}
