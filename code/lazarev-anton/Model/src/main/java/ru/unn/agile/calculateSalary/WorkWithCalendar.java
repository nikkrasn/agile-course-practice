package ru.unn.agile.calculateSalary;

import java.time.LocalDate;

public class WorkWithCalendar {
    private static final int DEFAULT_VACATION_YEAR = 1970;
    private LocalDate countMonth;
    private LocalDate startVacation = LocalDate.of(DEFAULT_VACATION_YEAR, 1, 1);
    private int lengthOfVacation = 0;

    public int countJobDaysInMonth() {
        SubtractHolidays checkingPeriod = new SubtractHolidays()
                                              .setCheckMonth(countMonth);
        int jobDaysInMonth = 0;
        for (int i = 1; i <= countMonth.lengthOfMonth(); i++) {
            if (checkingPeriod.isNotDayOff(i)) {
                jobDaysInMonth++;
            }
        }
        return jobDaysInMonth;
    }

    public int countCashDaysInMonth() {
        SubtractHolidays checkingPeriod = new SubtractHolidays()
                                              .setCheckMonth(countMonth)
                                              .setStartVacation(startVacation)
                                              .setLengthVacation(lengthOfVacation);
        int cashDaysInMonth = countJobDaysInMonth();
        if (!isCountYearNotVacationYear()) {
            return cashDaysInMonth - checkingPeriod.getHolidaysInVacation();
        }
        return cashDaysInMonth;
    }

    public WorkWithCalendar setCountMonth(final LocalDate inCountMonth) {
        countMonth = inCountMonth;
        return this;
    }

    public WorkWithCalendar setStartVacation(final LocalDate inStartVacation) {
        startVacation = inStartVacation;
        return this;
    }

    public WorkWithCalendar setLengthOfVacation(final int inLengthOfVacation) {
        lengthOfVacation = inLengthOfVacation;
        return this;
    }

    private boolean isCountYearNotVacationYear() {
        return countMonth.getYear() != startVacation.getYear();
    }
}
