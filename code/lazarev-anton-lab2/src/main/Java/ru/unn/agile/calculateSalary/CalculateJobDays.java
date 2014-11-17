package ru.unn.agile.calculateSalary;

import java.time.LocalDate;

public class CalculateJobDays {

    public static int countingJobDaysInMonth(LocalDate checkingMonth){
        int jobDaysInMonth = 0;
        jobDaysInMonth = calculateCalendarDays(jobDaysInMonth, checkingMonth);
        return jobDaysInMonth;
    }

    public static int countingCashDaysInMonth(int lengthOfVacation, LocalDate startOfVacation, LocalDate cashMonth){
        LocalDate endOfVacation = startOfVacation.plusDays(lengthOfVacation);
        int cashDaysInMonth = calculateCalendarDays(0, cashMonth);
        if (cashMonth.getYear() != startOfVacation.getYear()){return cashDaysInMonth;}
        else if (allVacationIncludeInCashMonth(startOfVacation, cashMonth, endOfVacation)){
            for(int i = startOfVacation.getDayOfMonth(); i < endOfVacation.getDayOfMonth(); i++){
                LocalDate checkingDate = LocalDate.of(cashMonth.getYear(), cashMonth.getMonth(), i);
                if(isNotDayOff(checkingDate)){cashDaysInMonth--;}
            }
        }
        else if (vacationStartInCashMonthAndEndInAnother(startOfVacation, cashMonth, endOfVacation)){
            for(int i = startOfVacation.getDayOfMonth(); i <= startOfVacation.lengthOfMonth(); i++){
                LocalDate checkingDate = LocalDate.of(cashMonth.getYear(), cashMonth.getMonth(), i);
                if(isNotDayOff(checkingDate)){cashDaysInMonth--;}
            }
        }
        else if (vacationStartInAnotherMonthButEndInCashMonth(startOfVacation, cashMonth, endOfVacation)){
            for(int i = 1; i < endOfVacation.getDayOfMonth(); i++){
                LocalDate checkingDate = LocalDate.of(cashMonth.getYear(), cashMonth.getMonth(), i);
                if(isNotDayOff(checkingDate)){cashDaysInMonth--;}
            }
        }
        return cashDaysInMonth;
    }

    private static boolean vacationStartInAnotherMonthButEndInCashMonth(LocalDate startOfVacation, LocalDate cashMonth, LocalDate endOfVacation) {
        return (cashMonth.getMonth() != startOfVacation.getMonth())&&(cashMonth.getMonth() == endOfVacation.getMonth());
    }

    private static boolean vacationStartInCashMonthAndEndInAnother(LocalDate startOfVacation, LocalDate cashMonth, LocalDate endOfVacation) {
        return (cashMonth.getMonth() == startOfVacation.getMonth())&&(cashMonth.getMonth() != endOfVacation.getMonth());
    }

    private static boolean allVacationIncludeInCashMonth(LocalDate startOfVacation, LocalDate cashMonth, LocalDate endOfVacation) {
        return (cashMonth.getMonth() == startOfVacation.getMonth()&&(cashMonth.getMonth() == endOfVacation.getMonth()));
    }

    private static int calculateCalendarDays(int jobDaysInMonth, LocalDate checkingMonth) {
        for(int i=1; i <= checkingMonth.lengthOfMonth(); i++){
            LocalDate checkingDate = LocalDate.of(checkingMonth.getYear(),checkingMonth.getMonth(), i);
            if (isNotDayOff(checkingDate)) {
                jobDaysInMonth++;
            }
        }
        return jobDaysInMonth;
    }

    private static boolean isNotDayOff(LocalDate checkingDay) {
        return (checkDayOfWeek(checkingDay)!=6)&&(checkDayOfWeek(checkingDay)!=7);
    }

    public static int checkDayOfWeek(LocalDate checkingDay) {
        return checkingDay.getDayOfWeek().getValue();
    }

}
