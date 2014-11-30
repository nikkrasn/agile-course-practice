package ru.unn.agile.CreditCalculator.ViewModel;

//import ru.unn.agile.CreditCalculator.Model.CreditCalculatorAnnuity;
//import ru.unn.agile.CreditCalculator.Model.CreditCalculatorDifferentiated;

public class ViewModel {
    private final String sum;
    private final String paymentPeriod;
    private final String interestRate;
    private final String startMonth;
    private TypePayment typePayment;
    private Currency currency;
    private final String result;
    private String status;
    private final boolean isCalculateButtonEnabled;

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

    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    public String getInterestRate() {
        return interestRate;
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

    public boolean getIsCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
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
        public static final String READY = "Press 'Calculate' or Enter";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";

        private Status() { }
    }
}
