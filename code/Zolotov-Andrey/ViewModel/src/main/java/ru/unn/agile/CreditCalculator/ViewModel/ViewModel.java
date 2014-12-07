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
    private String allSum;
    private String startDateOfPayment;
    private String finishDateOfPayment;
    private String overPayment;
    private String firstPayment;
    private String status;

    public ViewModel() {
        sum = "";
        paymentPeriod = "";
        interestRate = "";
        startMonth = "";
        typePayment = TypePayment.Annuity;
        currency = Currency.RUB;
        allSum = "";
        status = Status.WAITING;
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

    public String getAllSum() {
        return allSum;
    }

    public String getFirstPayment() {
        return firstPayment;
    }

    public String getOverPayment() {
        return overPayment;
    }

    public String getStartDateOfPayment() {
        return startDateOfPayment;
    }

    public String getFinishDateOfPayment() {
        return finishDateOfPayment;
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
        public static final String IS_NULL = "Is null";

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
            return false;
        }

        return isInputAvailable();
    }

    private boolean isInputAvailable() {
        return !sum.isEmpty()
                && !paymentPeriod.isEmpty()
                && !interestRate.isEmpty()
                && !startMonth.isEmpty();
    }

    private boolean isNotNullInputAvailable() {
        String zero = "0";
        if (sum.equals(zero)
                || paymentPeriod.equals(zero)
                || interestRate.equals(zero)
                || startMonth.equals(zero)) {
            status = Status.IS_NULL;
            return false;
        }
        return true;
    }

    public void calculate() {
        if (!parseInput() || !isNotNullInputAvailable()) {
            return;
        }
        String curren = "";
        switch (currency) {
            case RUB:
                curren = currency.RUB.toString();
                break;
            case Dollar:
                curren = currency.Dollar.toString();
                break;
            default:
                throw new IllegalArgumentException("Only Annuity and Differentiated are supported");
        }
        switch (typePayment) {
            case Annuity:
                CreditCalculatorAnnuity calculatorAnnuity = new CreditCalculatorAnnuity
                        .BuilderAnnuity(
                        Integer.parseInt(sum),
                        Integer.parseInt(paymentPeriod))
                        .currency(curren.charAt(0))
                        .interestRate(Double.parseDouble(interestRate))
                        .startMonth(Integer.parseInt(startMonth))
                        .build();
                allSum = String.valueOf(calculatorAnnuity.getAllSum());
                startDateOfPayment = String.valueOf(calculatorAnnuity.getSartDateOfPayment());
                finishDateOfPayment = String.valueOf(calculatorAnnuity.getFinishDateOfPayment());
                overPayment = String.valueOf(calculatorAnnuity.getOverPayment());
                firstPayment = String.valueOf(calculatorAnnuity.getMonthlyPayment(1));
                break;
            case Differentiated:
                CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                        .BuilderDifferentiated(
                        Integer.parseInt(sum),
                        Integer.parseInt(paymentPeriod))
                        .interestRate(Double.parseDouble(interestRate))
                        .currency(curren.charAt(0))
                        .startMonth(Integer.parseInt(startMonth))
                        .build();
                allSum = String.valueOf(calculator.getAllSum());
                startDateOfPayment = String.valueOf(calculator.getSartDateOfPayment());
                finishDateOfPayment = String.valueOf(calculator.getFinishDateOfPayment());
                overPayment = String.valueOf(calculator.getOverPayment());
                firstPayment = String.valueOf(calculator.getMonthlyPayment(1));
                break;
            default:
                throw new IllegalArgumentException("Only Annuity and Differentiated are supported");
        }
        status = Status.SUCCESS;
    }

}
