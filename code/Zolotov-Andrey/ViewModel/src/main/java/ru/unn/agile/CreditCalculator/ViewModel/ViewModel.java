package ru.unn.agile.CreditCalculator.ViewModel;

import ru.unn.agile.CreditCalculator.Model.CreditCalculator;
import ru.unn.agile.CreditCalculator.Model.CreditCalculatorAnnuity;
import ru.unn.agile.CreditCalculator.Model.CreditCalculatorDifferentiated;


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
        currency = Currency.RUR;
        allSum = "";
        status = UserInputStatus.WAITING;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(final String sum) {
        this.sum = sum;
    }

    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(final String paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public void setInterestRate(final String interestRate) {
        this.interestRate = interestRate;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setStartMonth(final String startMonth) {
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

        private TypePayment(final String nameGetting) {
            this.name = nameGetting;
        }

        public String toString() {
            return name;
        }
    }

    public enum Currency {
        RUR("R"),
        USD("$");
        private final String name;

        private Currency(final String nameGetting) {
            this.name = nameGetting;
        }

        public String toString() {
            return name;
        }
    }

    public final class UserInputStatus {
        public static final String WAITING = "Please provide input data";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";
        public static final String IS_NULL = "Is null";

        private UserInputStatus() { }
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
        } catch (NumberFormatException e) {
            status = UserInputStatus.BAD_FORMAT;
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
            status = UserInputStatus.IS_NULL;
            return false;
        }
        return true;
    }

    public void calculate() {
        if (!parseInput() || !isNotNullInputAvailable()) {
            return;
        }
        String stringCurrency = "";
        switch (currency) {
            case RUR:
                stringCurrency = currency.RUR.toString();
                break;
            case USD:
                stringCurrency = currency.USD.toString();
                break;
            default:
                throw new IllegalArgumentException("Only Annuity and Differentiated are supported");
        }
        switch (typePayment) {
            case Annuity:
                CreditCalculatorAnnuity calculatorAnnuity = new CreditCalculatorAnnuity
                        .BuilderAnnuity(Integer.parseInt(sum) , Integer.parseInt(paymentPeriod))
                        .currency(stringCurrency.charAt(0))
                        .interestRate(Double.parseDouble(interestRate))
                        .startMonth(Integer.parseInt(startMonth))
                        .build();
                setResults(calculatorAnnuity);
                break;
            case Differentiated:
                CreditCalculatorDifferentiated calculatorDiff = new CreditCalculatorDifferentiated
                        .BuilderDifferentiated(
                            Integer.parseInt(sum) , Integer.parseInt(paymentPeriod))
                        .interestRate(Double.parseDouble(interestRate))
                        .currency(stringCurrency.charAt(0))
                        .startMonth(Integer.parseInt(startMonth))
                        .build();
                setResults(calculatorDiff);
                break;
            default:
                throw new IllegalArgumentException("Only Annuity and Differentiated are supported");
        }
        status = UserInputStatus.SUCCESS;
    }
    public  void setResults(final CreditCalculator calculator) {
        allSum = String.valueOf(calculator.getAllSum());
        startDateOfPayment = String.valueOf(
                calculator.getStartDateOfPayment());
        finishDateOfPayment = String.valueOf(
                calculator.getFinishDateOfPayment());
        overPayment = String.valueOf(calculator.getOverPayment());
        firstPayment = String.valueOf(calculator.getMonthlyPayment(1));
    }

}
