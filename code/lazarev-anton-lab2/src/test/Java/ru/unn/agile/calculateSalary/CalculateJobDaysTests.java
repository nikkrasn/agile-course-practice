package ru.unn.agile.calculateSalary;

import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class CalculateJobDaysTests {

    @Test
    public void checkDayOfWeek() {
       LocalDate example = LocalDate.of(2014, Month.NOVEMBER, 16);
        assertEquals(7, CalculateJobDays.checkDayOfWeek(example));
    }

    @Test
    public void checkJobDaysInSeptember() {
        LocalDate example = LocalDate.of(2014, Month.SEPTEMBER, 1);
        assertEquals(22, CalculateJobDays.countJobDaysInMonth(example));
    }

    @Test
    public void checkJobDaysInAugust() {
        LocalDate example = LocalDate.of(2014, Month.AUGUST, 1);
        assertEquals(21, CalculateJobDays.countJobDaysInMonth(example));
    }

    @Test
    public void checkJobDaysInMonthWithoutVacationDays() {
        LocalDate cashMonth = LocalDate.of(2014, Month.MARCH, 1);
        LocalDate vacation = LocalDate.of(2014, Month.SEPTEMBER, 8);
        assertEquals(21, CalculateJobDays.countCashDaysInMonth(7, vacation, cashMonth));
    }

    @Test
    public void checkCashDaysInMonthWith14VacationDays() {
        LocalDate cashMonth = LocalDate.of(2014, Month.AUGUST, 1);
        LocalDate vacation = LocalDate.of(2014, Month.AUGUST, 1);
        assertEquals(11, CalculateJobDays.countCashDaysInMonth(14, vacation, cashMonth));
    }

    @Test
    public void checkCashDaysInMonthWith7VacationDays() {
        LocalDate cashMonth = LocalDate.of(2014, Month.SEPTEMBER, 1);
        LocalDate vacation = LocalDate.of(2014, Month.SEPTEMBER, 8);
        assertEquals(17, CalculateJobDays.countCashDaysInMonth(7, vacation, cashMonth));
    }

    @Test
    public void checkCashDaysInMonthWithEndOfVacationInAnotherMonth() {
        LocalDate cashMonth = LocalDate.of(2014, Month.AUGUST, 1);
        LocalDate vacation = LocalDate.of(2014, Month.AUGUST, 25);
        assertEquals(16, CalculateJobDays.countCashDaysInMonth(14, vacation, cashMonth));
    }

    @Test
    public void checkCashDaysInMonthWhenVacationInAnotherYear() {
        LocalDate cashMonth = LocalDate.of(2014, Month.SEPTEMBER, 1);
        LocalDate vacation = LocalDate.of(2015, Month.AUGUST, 25);
        assertEquals(22, CalculateJobDays.countCashDaysInMonth(14, vacation, cashMonth));
    }
}
