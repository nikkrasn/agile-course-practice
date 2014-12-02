package ru.unn.agile.calculateSalary;

import java.time.LocalDate;

public class SubtractHolidays {
    private static final int DEFAULT_VACATION_YEAR = 1970;
    private static final int SATURDAY_NUMBER_DAY_OF_WEEK = 6;
    private static final int SUNDAY_NUMBER_DAY_OF_WEEK = 7;
    private LocalDate checkMonth;
    private LocalDate startVacation = LocalDate.of(DEFAULT_VACATION_YEAR, 1, 1);
    private int lengthOfVacation = 0;
    private LocalDate endVacation;
    private int dayStartSubtract;
    private int dayEndSubtract;

    public int getHolidaysInVacation() {
        endVacation = startVacation.plusDays(lengthOfVacation);
        dayStartSubtract = getStartDay();
        dayEndSubtract = getEndDay();
        return summVacationDaysToSubtract();
    }

    public boolean isNotDayOff(final int checkDayNumber) {
        LocalDate checkDate = LocalDate.of(checkMonth.getYear(),
                                           checkMonth.getMonth(),
                                           checkDayNumber);
        return isDayNotSaturday(checkDate) && isDayNotSunday(checkDate);
    }

    public SubtractHolidays setCheckMonth(final LocalDate inCheckMonth) {
        checkMonth = inCheckMonth;
        return this;
    }

    public SubtractHolidays setStartVacation(final LocalDate inStartVacation) {
        startVacation = inStartVacation;
        return this;
    }

    public SubtractHolidays setLengthVacation(final int inLengthOfVacation) {
        lengthOfVacation = inLengthOfVacation;
        return this;
    }

    private int summVacationDaysToSubtract() {
        if (checkMonth.getYear() != startVacation.getYear()) {
            return 0;
        }
        int dayToSubtract = 0;
        for (int i = dayStartSubtract; i < dayEndSubtract; i++) {
            if (isNotDayOff(i)) {
                dayToSubtract++;
            }
        }
        if (isExtraSituationWithEndOfSubtract()) {
            dayToSubtract++;
        }
        return dayToSubtract;
    }

    private int getStartDay() {
        if (isAllCashMonthInVacation() || vacationInAnotherMonthButEndInCashMonth()) {
            return 1;
        }
        if (isAllVacationInCashMonth() || vacationInCashMonthButEndInAnother()) {
            return startVacation.getDayOfMonth();
        }
        return 0;
    }

    private int getEndDay() {
        if (isAllCashMonthInVacation() || vacationInCashMonthButEndInAnother()) {
            return checkMonth.lengthOfMonth();
        }
        if (isAllVacationInCashMonth() || vacationInAnotherMonthButEndInCashMonth()) {
            return endVacation.getDayOfMonth();
        }
        return 0;
    }

    private boolean isExtraSituationWithEndOfSubtract() {
        return vacationInCashMonthButEndInAnother() && isNotDayOff(startVacation.lengthOfMonth())
                || isAllCashMonthInVacation() && isNotDayOff(startVacation.lengthOfMonth());
    }

    private boolean isAllCashMonthInVacation() {
        return startVacation.getMonth().getValue() < checkMonth.getMonth().getValue()
                && endVacation.getMonth().getValue() > checkMonth.getMonth().getValue();
    }

    private boolean vacationInAnotherMonthButEndInCashMonth() {
        return !isCountMonthEqualStartVacationMonth() && isCountMonthEqualVacationEndMonth();
    }

    private boolean vacationInCashMonthButEndInAnother() {
        return isCountMonthEqualStartVacationMonth() && !isCountMonthEqualVacationEndMonth();
    }

    private boolean isAllVacationInCashMonth() {
        return isCountMonthEqualStartVacationMonth() && isCountMonthEqualVacationEndMonth();
    }

    private boolean isCountMonthEqualVacationEndMonth() {
        return checkMonth.getMonth() == endVacation.getMonth();
    }

    private boolean isCountMonthEqualStartVacationMonth() {
        return checkMonth.getMonth() == startVacation.getMonth();
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
