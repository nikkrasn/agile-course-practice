package ru.unn.agile.calculateSalary;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalculateJobDays {

    public static int countingJobDaysInMonth(int month, int year){
        int jobDaysInMonth = 0;
        LocalDate currentMonth = LocalDate.of(year, month, 1);
        jobDaysInMonth = calculateOnlyJobDays(month, year, jobDaysInMonth, currentMonth);
        jobDaysInMonth = subtractionHolidaysInMonth(month, year, jobDaysInMonth, currentMonth);
        return jobDaysInMonth;
    }

    private static int subtractionHolidaysInMonth(int month, int year, int jobDaysInMonth, LocalDate currentMonth) {
        for(int i=1; i<=currentMonth.lengthOfMonth(); i++){
            if (isHoliday(month, year, i)) {
                jobDaysInMonth--;
            }
        }
        return jobDaysInMonth;
    }

    private static int calculateOnlyJobDays(int month, int year, int jobDaysInMonth, LocalDate currentMonth) {
        for(int i=1; i<=currentMonth.lengthOfMonth(); i++){
            if (isNotDayOff(month, year, i)) {
                jobDaysInMonth++;
            }
        }
        return jobDaysInMonth;
    }

    private static boolean isNotDayOff(int month, int year, int dayOfMonth) {
        return (checkDayOfWeek(dayOfMonth, month, year)!=6)&&(checkDayOfWeek(dayOfMonth, month, year)!=7);
    }

    public static boolean isHoliday(int month, int year, int day) {
        if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 1, 1))){return true;}
        else if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 1, 2))){return true;}
        else if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 1, 3))){return true;}
        else if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 1, 4))){return true;}
        else if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 1, 5))){return true;}
        else if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 1, 7))){return true;}
        else if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 2, 23))){return true;}
        else if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 3, 8))){return true;}
        else if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 5, 1))){return true;}
        else if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 5, 9))){return true;}
        else if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 6, 12))){return true;}
        else if (LocalDate.of(year, month, day).equals(LocalDate.of(year, 11, 4))){return true;}
        return false;
    }

    public static int checkDayOfWeek(int dayOfMonth, int month, int year) {
        LocalDate currentDate = LocalDate.of(year, month, dayOfMonth);
        DayOfWeek checkingDay = currentDate.getDayOfWeek();
        int numberDayOfWeek = checkingDay.getValue();
        return numberDayOfWeek;
    }

}
