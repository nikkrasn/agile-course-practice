package ru.unn.agile.calculateSalary;

import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class CalculateJobDaysTests {

    @Test
    public void checkDayOfWeekTest(){
       LocalDate example = LocalDate.of(2014, Month.NOVEMBER, 16);
        assertEquals(7, CalculateJobDays.checkDayOfWeek(example));
    }

    @Test
    public void checkJobDaysInSeptemberTest(){
        LocalDate example = LocalDate.of(2014, Month.SEPTEMBER, 1);
        assertEquals(22, CalculateJobDays.countingJobDaysInMonth(example));
    }

    @Test
    public void checkJobDaysInAugustTest(){
        LocalDate example = LocalDate.of(2014, Month.AUGUST, 1);
        assertEquals(21, CalculateJobDays.countingJobDaysInMonth(example));
    }

    @Test
    public void checkJobDaysInMonthWithoutVacationDaysTest(){
        LocalDate exampleCashMonth = LocalDate.of(2014, Month.MARCH, 1);
        LocalDate exampleOfVacation = LocalDate.of(2014, Month.SEPTEMBER, 8);
        assertEquals(21, CalculateJobDays.countingCashDaysInMonth(7, exampleOfVacation, exampleCashMonth));
    }

    @Test
    public void checkCashDaysInMonthWith14VacationDaysTest(){
        LocalDate exampleCashMonth = LocalDate.of(2014, Month.AUGUST, 1);
        LocalDate exampleOfVacation = LocalDate.of(2014, Month.AUGUST, 1);
        assertEquals(11, CalculateJobDays.countingCashDaysInMonth(14, exampleOfVacation, exampleCashMonth));
    }

    @Test
    public void checkCashDaysInMonthWith7VacationDaysTest(){
        LocalDate exampleCashMonth = LocalDate.of(2014, Month.SEPTEMBER, 1);
        LocalDate exampleOfVacation = LocalDate.of(2014, Month.SEPTEMBER, 8);
        assertEquals(17, CalculateJobDays.countingCashDaysInMonth(7, exampleOfVacation, exampleCashMonth));
    }

    @Test
    public void checkCashDaysInMonthWithEndOfVacationInAnotherMonthTest(){
        LocalDate exampleCashMonth = LocalDate.of(2014, Month.AUGUST, 1);
        LocalDate exampleOfVacation = LocalDate.of(2014, Month.AUGUST, 25);
        assertEquals(16, CalculateJobDays.countingCashDaysInMonth(14, exampleOfVacation, exampleCashMonth));
    }

    @Test
    public void checkCashDaysInMonthWhenVacationInAnotherYearTest(){
        LocalDate exampleCashMonth = LocalDate.of(2014, Month.SEPTEMBER, 1);
        LocalDate exampleOfVacation = LocalDate.of(2015, Month.AUGUST, 25);
        assertEquals(22, CalculateJobDays.countingCashDaysInMonth(14, exampleOfVacation, exampleCashMonth));
    }
}
