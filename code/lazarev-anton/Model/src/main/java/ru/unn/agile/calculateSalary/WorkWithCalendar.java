package ru.unn.agile.calculateSalary;

import java.time.LocalDate;

public class WorkWithCalendar {
    private static final int DEFAULT_VACATION_YEAR = 1970;
    private static final int SATURDAY_NUMBER_DAY_OF_WEEK = 6;
    private static final int SUNDAY_NUMBER_DAY_OF_WEEK = 7;
    private int lengthOfVacation = 0;
    private LocalDate countMonth;
    private LocalDate startVacation = LocalDate.of(DEFAULT_VACATION_YEAR, 1, 1);
    private LocalDate vacationEnd;

    public int countJobDaysInMonth() {
        int jobDaysInMonth = 0;
        for (int i = 1; i <= countMonth.lengthOfMonth(); i++) {
            if (isNotDayOff(i)) {
                jobDaysInMonth++;
            }
        }
        return jobDaysInMonth;
    }

    public int countCashDaysInMonth() {
        vacationEnd = startVacation.plusDays(lengthOfVacation);
        int cashDaysInMonth = countJobDaysInMonth();
        int dayStartSubtract = getStartDay();
        int dayEndSubtract = getEndDay();
        if (isCountYearNotVacationYear()) {
            return cashDaysInMonth;
        } else {
            cashDaysInMonth = cashDaysSubtractUntilVacationEnd(cashDaysInMonth,
                    dayStartSubtract,
                    dayEndSubtract);
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


    private int cashDaysSubtractUntilVacationEnd(final int cashDaysInMonth,
                                                 final int dayStartSubtract,
                                                 final int dayEndStartSubtract) {
        int tempCashDaysInMonth = cashDaysInMonth;
        for (int i = dayStartSubtract; i < dayEndStartSubtract; i++) {
            if (isNotDayOff(i)) {
                tempCashDaysInMonth--;
            }
        }
        return tempCashDaysInMonth;
    }

    private int getStartDay() {
        if (isAllVacationInCashMonth()) {
            return startVacation.getDayOfMonth();
        }
        if (vacationStartInCashMonthAndEndInAnother()) {
            return startVacation.getDayOfMonth();
        }
        if (vacationEndInCashMonthButStartInAnother()) {
            return 1;
        }
        return 0;
    }

    private int getEndDay() {
        if (isAllVacationInCashMonth()) {
            return vacationEnd.getDayOfMonth();
        }
        if (vacationStartInCashMonthAndEndInAnother()) {
            return vacationEnd.lengthOfMonth();
        }
        if (vacationEndInCashMonthButStartInAnother()) {
            return vacationEnd.getDayOfMonth();
        }
        return 0;
    }

    private boolean vacationEndInCashMonthButStartInAnother() {
        return isCountMonthNotStartVacationMonth() && isCountMonthEqualVacationEndMonth();
    }

    private boolean vacationStartInCashMonthAndEndInAnother() {
        return isCountMonthEqualStartVacationMonth() && isCountMonthNotVacationEndMonth();
    }

    private boolean isAllVacationInCashMonth() {
        return isCountMonthEqualStartVacationMonth() && isCountMonthEqualVacationEndMonth();
    }

    private boolean isCountYearNotVacationYear() {
        return countMonth.getYear() != startVacation.getYear();
    }

    private boolean isCountMonthEqualVacationEndMonth() {
        return countMonth.getMonth() == vacationEnd.getMonth();
    }

    private boolean isCountMonthNotVacationEndMonth() {
        return countMonth.getMonth() != vacationEnd.getMonth();
    }

    private boolean isCountMonthEqualStartVacationMonth() {
        return countMonth.getMonth() == startVacation.getMonth();
    }

    private boolean isCountMonthNotStartVacationMonth() {
        return countMonth.getMonth() != startVacation.getMonth();
    }

    private boolean isNotDayOff(final int checkDayNumber) {
        LocalDate checkDate = LocalDate.of(countMonth.getYear(),
                countMonth.getMonth(),
                checkDayNumber);
        return isDayNotSaturday(checkDate) && isDayNotSunday(checkDate);
    }

    private static boolean isDayNotSunday(final LocalDate checkDay) {
        return checkDayOfWeek(checkDay) != SUNDAY_NUMBER_DAY_OF_WEEK;
    }

    private static boolean isDayNotSaturday(final LocalDate checkDay) {
        return checkDayOfWeek(checkDay) != SATURDAY_NUMBER_DAY_OF_WEEK;
    }

    private static int checkDayOfWeek(final LocalDate checkDay) {
        return checkDay.getDayOfWeek().getValue();
    }

}
