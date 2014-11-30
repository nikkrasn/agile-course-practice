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
    private int summOfWorkedHours;

    public double calculate() {
        if (salary <= 0) {
            return 0;
        }
        payForOneJobHour = calcCashForOneJobHour();
        summOfWorkedHours = getSummOfWorkedHours();
        if (isEmployeeWorkedMoreThanNormHoursInMonth()) {
            return calcCashWithOvertime() * NDS;
        }
        if (isEmployeeWorkedLessThanNormHoursInMonth()) {
            return calcCashForLessHours() * NDS;
        }
        return calcCashForNormalHours() * NDS;
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

    private boolean isEmployeeWorkedLessThanNormHoursInMonth() {
        return workedHourInMonth < summOfWorkedHours;
    }

    private boolean isEmployeeWorkedMoreThanNormHoursInMonth() {
        return workedHourInMonth > summOfWorkedHours;
    }

    private double calcCashForLessHours() {
        return workedHourInMonth * payForOneJobHour;
    }

    private double calcCashForNormalHours() {
        return summOfWorkedHours * payForOneJobHour;
    }

    private double calcCashWithOvertime() {
        return calcCashForNormalHours() + cashForOvertime();
    }

    private double calcCashForOneJobHour() {
        WorkWithCalendar countMonth = new WorkWithCalendar().setCountMonth(countingMonth);
        int jobDaysInCountMonth = countMonth.countJobDaysInMonth();
        return salary / (summJobHours(jobDaysInCountMonth));
    }

    private int getSummOfWorkedHours() {
        WorkWithCalendar countMonth = new WorkWithCalendar().setCountMonth(countingMonth)
                .setStartVacation(startOfVacation)
                .setLengthOfVacation(lengthOfVacation);
        int cashDaysInMonth = countMonth.countCashDaysInMonth();
        return summJobHours(cashDaysInMonth);
    }

    private double cashForOvertime() {
        return (payForOneJobHour * 2) * (workedHourInMonth - summOfWorkedHours);
    }

    private int summJobHours(final int amountDays) {
        return amountDays * WORK_HOUR_IN_DAY;
    }
}
