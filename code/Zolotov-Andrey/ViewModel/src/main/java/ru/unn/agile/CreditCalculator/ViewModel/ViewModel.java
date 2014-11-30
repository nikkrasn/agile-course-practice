package ru.unn.agile.CreditCalculator.ViewModel;

import ru.unn.agile.CreditCalculator.core.CreditCalculatorAnnuity;
import ru.unn.agile.CreditCalculator.core.CreditCalculatorDifferentiated;

public class ViewModel {
    private String sum;
    private String paymentPeriod;
    private String interestRate;
    private String startMonth;
    private TypePayment typePayment;
    private Currency currency;
    private String result;
    private String status;
    private boolean isCalculateButtonEnabled;

    public ViewModel() {
        sum = "";
        paymentPeriod = "";
        interestRate = "";
        startMonth = "";
        typePayment = TypePayment.Annuity;
        currency = Currency.RUB;
        result = "";
        status = Status.WAITING;

        isCalculateButtonEnabled = false;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(final String sum) {
        if (sum.equals(this.sum)) {
            return;
        }
        this.sum = sum;
    }

    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(final String paymentPeriod) {
        if (paymentPeriod.equals(this.paymentPeriod)) {
            return;
        }
        this.paymentPeriod = paymentPeriod;
    }

    public void setInterestRate(final String interestRate) {
        if (interestRate.equals(this.interestRate)) {
            return;
        }
        this.interestRate = interestRate;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setStartMonth(final String startMonth) {
        if (startMonth.equals(this.startMonth)) {
            return;
        }
        this.startMonth = startMonth;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    public Currency getCurrency() {
        return currency;
    }

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    public void setTypePayment(final TypePayment typePayment) {
        this.typePayment = typePayment;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public enum TypePayment {
        Annuity("Annuity"),
        Differentiated("Differentiated");
        private final String name;

        private TypePayment(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum Currency {
        RUB("R"),
        Dollar("$");
        private final String name;

        private Currency(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public final class Status {
        public static final String WAITING = "Please provide input data";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";

        private Status() { }
    }

    private boolean parseInput() {
        try {
            if (!sum.isEmpty()) {
                Integer.parseInt(sum);
            }
            if (!paymentPeriod.isEmpty()) {
                Integer.parseInt(paymentPeriod);
            }
            if (!interestRate.isEmpty()) {
                Double.parseDouble(interestRate);
            }
            if (!startMonth.isEmpty()) {
                Integer.parseInt(startMonth);
            }
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isCalculateButtonEnabled = false;
            return false;
        }

        isCalculateButtonEnabled = isInputAvailable();

        return isCalculateButtonEnabled;
    }

    private boolean isInputAvailable() {
        return !sum.isEmpty() && !paymentPeriod.isEmpty();
    }

    public void calculate() {
        if (!parseInput()) {
            return;
        }

        switch (typePayment) {
            case Annuity:
                CreditCalculatorAnnuity calculatorAnnuity = new CreditCalculatorAnnuity
                        .BuilderAnnuity(
                        Integer.parseInt(sum),
                        Integer.parseInt(paymentPeriod))
                        .build();
                result = String.valueOf(calculatorAnnuity.getAllSum());
                break;
            case Differentiated:
                CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                        .BuilderDifferentiated(
                        Integer.parseInt(sum),
                        Integer.parseInt(paymentPeriod))
                        .build();
                result = String.valueOf(calculator.getAllSum());
                break;
            default:
                throw new IllegalArgumentException("Only Annuity and Differentiated are supported");
        }
        status = Status.SUCCESS;
    }

}
