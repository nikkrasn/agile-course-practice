package ru.unn.agile.calculateSalary;

import java.time.LocalDate;

public class CalculateSalary {
    private static final int DEFAULT_VACATION_YEAR = 1970;
    private static final int WORK_HOUR_IN_DAY = 8;
    private static final double NDS = 0.87;
    private double salary;
    private LocalDate countingMonth;
    private int workedHourInMonth;
    private LocalDate startOfVacation = LocalDate.of(DEFAULT_VACATION_YEAR, 1, 1);
    private int lengthOfVacation;
    private double payForOneJobHour;
    private int amountOfJobHours;

    public double calculate() {
        if (salary <= 0) {
            return 0;
        }
        WorkWithCalendar countMonth = new WorkWithCalendar().setCountMonth(countingMonth)
                .setStartVacation(startOfVacation)
                .setLengthOfVacation(lengthOfVacation);
        double cashForPay = 0;
        int jobDaysInCountMonth = countMonth.countJobDaysInMonth();
        int cashDaysInMonth = countMonth.countCashDaysInMonth();
        payForOneJobHour = calcCashForOneJobHour(jobDaysInCountMonth);
        amountOfJobHours = summJobHours(cashDaysInMonth);
        switch (getCompareWorkAndJobHours()) {
            case "employeeWorkedMoreThanNormHoursInMonth":
                cashForPay = calcCashWithOvertime();
                break;
            case "employeeWorkedLessThanNormHoursInMonth":
                cashForPay = calcCashForLessHours();
                break;
            default:
                cashForPay = calcCashForNormalHours();
        }
        return cashForPay * NDS;
    }

    public CalculateSalary setSalary(final double inSalary) {
        salary = inSalary;
        return this;
    }

    public CalculateSalary setCountingMonth(final LocalDate inCountingMonth) {
        countingMonth = inCountingMonth;
        return this;
    }

    public CalculateSalary setWorkedHourInMonth(final int inWorkedHourInMonth) {
        workedHourInMonth = inWorkedHourInMonth;
        return this;
    }

    public CalculateSalary setStartOfVacation(final LocalDate inStartOfVacation) {
        startOfVacation = inStartOfVacation;
        return this;
    }

    public CalculateSalary setLengthOfVacation(final int inLengthOfVacation) {
        lengthOfVacation = inLengthOfVacation;
        return this;
    }

    private String getCompareWorkAndJobHours() {
        if (workedHourInMonth > amountOfJobHours) {
            return "employeeWorkedMoreThanNormHoursInMonth";
        }
        if (workedHourInMonth < amountOfJobHours) {
            return "employeeWorkedLessThanNormHoursInMonth";
        }
        return "";
    }

    private double calcCashForLessHours() {
        return workedHourInMonth * payForOneJobHour;
    }

    private double calcCashForNormalHours() {
        return amountOfJobHours * payForOneJobHour;
    }

    private double calcCashWithOvertime() {
        return calcCashForNormalHours() + cashForOvertime();
    }

    private double calcCashForOneJobHour(final int jobDaysInCurrentMonth) {
        return salary / (summJobHours(jobDaysInCurrentMonth));
    }

    private double cashForOvertime() {
        return (payForOneJobHour * 2) * (workedHourInMonth - amountOfJobHours);
    }

    private int summJobHours(final int amountDays) {
        return amountDays * WORK_HOUR_IN_DAY;
    }
}
