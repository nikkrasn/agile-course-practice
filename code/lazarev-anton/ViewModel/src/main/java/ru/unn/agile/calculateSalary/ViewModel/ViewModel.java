package ru.unn.agile.calculateSalary.ViewModel;

import ru.unn.agile.calculateSalary.CalculateSalary;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.math.BigDecimal;

public class ViewModel {
    private static final String DEFAULT_VACATION_YEAR = "1960";
    private String salary;
    private String workedHours;
    private String countMonth;
    private String countYear;
    private String vacationLength;
    private String startVacationDay;
    private String vacationMonth;
    private String vacationYear;
    private String result;
    private String status;
    private boolean isCalculateButtonEnabled;

    public ViewModel() {
        salary = "";
        workedHours = "";
        countMonth = "";
        countYear = "";
        vacationLength = "";
        startVacationDay = "";
        vacationMonth = "";
        vacationYear = "";
        result = "";
        status = Status.WAITING;
        isCalculateButtonEnabled = false;
    }

    public final class Status {
        public static final String WAITING = "Please provide salary and count period information";
        public static final String READY_CALCULATE = "Press 'Calculate' button";
        public static final String BAD_COUNT_FORMAT = "Wrong format of count input";
        public static final String BAD_VACATION_FORMAT = "Wrong format of vacation input";
        public static final String CASH = "This your cash";

        private Status() { }
    }

    public void calculate() {
        if (!isCountInputCorrect()) {
            return;
        }
        CalculateSalary countPeriod = new CalculateSalary()
                .setSalary(Double.parseDouble(salary))
                .setWorkedHourInMonth(Integer.parseInt(workedHours))
                .setCountingMonth(LocalDate.of(Integer.parseInt(countYear)
                                             , Integer.parseInt(countMonth)
                                             , 1))
                .setLengthOfVacation(0);
        if (isOneVacationFieldNotDefault()) {
            if (!isVacationInputCorrect()) {
                return;
            }
            countPeriod.setLengthOfVacation(Integer.parseInt(vacationLength))
                    .setCountingMonth(LocalDate.of(Integer.parseInt(vacationYear)
                                                 , Integer.parseInt(vacationMonth)
                                                 , Integer.parseInt(startVacationDay)));
        }
        result = formateCashInMoneyValue(countPeriod);
        status = Status.CASH;
    }

    private String formateCashInMoneyValue(final CalculateSalary countPeriod) {
        double inResult = countPeriod.calculate();
        inResult = new BigDecimal(inResult).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        return Double.toString(inResult);
    }

    public boolean getCalculateButtonEnable() {
        return isCalculateButtonEnabled;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(final String inSalary) {
        if (inSalary.equals(this.salary)) {
            return;
        }
        this.salary = inSalary;
    }

    public String getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(final String inWorkedHours) {
        if (inWorkedHours.equals(this.workedHours)) {
            return;
        }
        this.workedHours = inWorkedHours;
    }

    public String getCountMonth() {
        return countMonth;
    }

    public void setCountMonth(final String inCountMonth) {
        if (inCountMonth.equals(this.countMonth)) {
            return;
        }
        this.countMonth = inCountMonth;
    }

    public String getCountYear() {
        return countYear;
    }

    public void setCountYear(final String inCountYear) {
        if (inCountYear.equals(this.countYear)) {
            return;
        }
        this.countYear = inCountYear;
    }

    public String getVacationLength() {
        return vacationLength;
    }

    public void setVacationLength(final String inVacationLength) {
        if (inVacationLength.equals(this.vacationLength)) {
            return;
        }
        this.vacationLength = inVacationLength;
    }

    public String getStartVacationDay() {
        return startVacationDay;
    }

    public void setStartVacationDay(final String inVacationStart) {
        if (inVacationStart.equals(this.startVacationDay)) {
            return;
        }
        this.startVacationDay = inVacationStart;
    }

    public String getVacationMonth() {
        return vacationMonth;
    }

    public void setVacationMonth(final String inVacationMonth) {
        if (inVacationMonth.equals(this.vacationMonth)) {
            return;
        }
        this.vacationMonth = inVacationMonth;
    }

    public String getVacationYear() {
        return vacationYear;
    }

    public void setVacationYear(final String inVacationYear) {
        if (inVacationYear.equals(this.vacationYear)) {
            return;
        }
        this.vacationYear = inVacationYear;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    private boolean isCountInputCorrect() {
        try {
            if (!salary.isEmpty()) {
                Double.parseDouble(salary);
            }
            if (!workedHours.isEmpty()) {
                Integer.parseInt(workedHours);
            }
            if (!countMonth.isEmpty()) {
                Integer.parseInt(countMonth);
            }
            if (!countYear.isEmpty()) {
                Integer.parseInt(countYear);
            }
        } catch (Exception e) {
            status = Status.BAD_COUNT_FORMAT;
            isCalculateButtonEnabled = false;
            return false;
        }
        isCalculateButtonEnabled = isCountInputAvailable();
        if (isCalculateButtonEnabled) {
            status = Status.READY_CALCULATE;
        } else {
            status = Status.WAITING;
        }
        return isCalculateButtonEnabled;
    }

    private boolean isCountInputAvailable() {
        return !salary.isEmpty()
                && !workedHours.isEmpty()
                && !countMonth.isEmpty()
                && !countYear.isEmpty();
    }

    private boolean isVacationInputCorrect() {
        try {
            if (!vacationLength.isEmpty()) {
                Integer.parseInt(vacationLength);
            }
            if (!startVacationDay.isEmpty()) {
                Integer.parseInt(startVacationDay);
            }
            if (!vacationMonth.isEmpty()) {
                Integer.parseInt(vacationMonth);
            }
            if (!vacationYear.isEmpty()) {
                Integer.parseInt(vacationYear);
            }
        } catch (Exception e) {
            status = Status.BAD_VACATION_FORMAT;
            isCalculateButtonEnabled = false;
            return false;
        }
        isCalculateButtonEnabled = isVacationInputAvailable();
        if (isCalculateButtonEnabled) {
            status = Status.READY_CALCULATE;
        } else {
            status = Status.WAITING;
        }
        return isCalculateButtonEnabled;
    }

    private boolean isVacationInputAvailable() {
        return !vacationLength.isEmpty()
                && !startVacationDay.isEmpty()
                && !vacationMonth.isEmpty()
                && !vacationYear.isEmpty();
    }

    private boolean isOneVacationFieldNotDefault() {
        return !vacationLength.isEmpty()
                || !startVacationDay.isEmpty()
                || !vacationMonth.isEmpty()
                || !vacationYear.isEmpty();
    }
}
