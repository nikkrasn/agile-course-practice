package ru.unn.agile.calculateSalary.ViewModel;

import ru.unn.agile.calculateSalary.CalculateSalary;

import java.time.LocalDate;

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
        startVacationDay = "1";
        vacationMonth = "1";
        vacationYear = DEFAULT_VACATION_YEAR;
        result = "";
        status = Status.WAITING;
        isCalculateButtonEnabled = false;
    }

    public final class Status {
        public static final String WAITING = "Please provide salary and count period information";
        public static final String READY_CALCULATE = "Press 'Calculate' button";
        public static final String BAD_FORMAT = "Wrong format of input information";
        public static final String CASH_WITH_VACATION = "This your cash";

        private Status() { }
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
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
        result = Double.toString(countPeriod.calculate());
        status = Status.CASH_WITH_VACATION;
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

    private void pressButton() {

        if (isCalculateButtonEnabled()) {
            calculate();
        }
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
            status = Status.BAD_FORMAT;
            isCalculateButtonEnabled = false;
            return false;
        }
        isCalculateButtonEnabled = countInputAvailable();
        if (isCalculateButtonEnabled) {
            status = Status.READY_CALCULATE;
        } else {
            status = Status.WAITING;
        }
        return isCalculateButtonEnabled;
    }

    private boolean countInputAvailable() {
        return !salary.isEmpty()
                && !workedHours.isEmpty()
                && !countMonth.isEmpty()
                && !countYear.isEmpty();
    }
}
