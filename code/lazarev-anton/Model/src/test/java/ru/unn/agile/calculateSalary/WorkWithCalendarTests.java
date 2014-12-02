package ru.unn.agile.calculateSalary;

import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.Assert.*;

public class WorkWithCalendarTests {

    @Test
    public void checkJobDaysInSeptember() {
        WorkWithCalendar countMonth = new WorkWithCalendar()
                .setCountMonth(LocalDate.of(2014, Month.OCTOBER, 1));
        assertEquals(23, countMonth.countJobDaysInMonth());
    }

    @Test
    public void checkJobDaysInAugust() {
        WorkWithCalendar countMonth = new WorkWithCalendar()
                .setCountMonth(LocalDate.of(2014, Month.AUGUST, 1));
        assertEquals(21, countMonth.countJobDaysInMonth());
    }

    @Test
    public void checkJobDaysMonthWithoutVacation() {
        WorkWithCalendar countMonth = new WorkWithCalendar()
                .setCountMonth(LocalDate.of(2014, Month.MARCH, 1));
        assertEquals(21, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkCashDaysInMonthWith14VacationDays() {
        WorkWithCalendar countMonth = new WorkWithCalendar()
                .setCountMonth(LocalDate.of(2014, Month.AUGUST, 1))
                .setStartVacation(LocalDate.of(2014, Month.AUGUST, 1))
                .setLengthOfVacation(14);
        assertEquals(11, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkCashDaysInMonthWith7VacationDays() {
        WorkWithCalendar countMonth = new WorkWithCalendar()
                .setCountMonth(LocalDate.of(2014, Month.SEPTEMBER, 1))
                .setStartVacation(LocalDate.of(2014, Month.SEPTEMBER, 8))
                .setLengthOfVacation(7);
        assertEquals(17, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkCashDaysInMonthWithEndOfVacationInAnotherMonth() {
        WorkWithCalendar countMonth = new WorkWithCalendar()
                .setCountMonth(LocalDate.of(2014, Month.AUGUST, 1))
                .setStartVacation(LocalDate.of(2014, Month.AUGUST, 25))
                .setLengthOfVacation(14);
        assertEquals(16, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkCashDaysInMonthWithEndOfVacationInMiddleOfMonth() {
        WorkWithCalendar countMonth = new WorkWithCalendar()
                .setCountMonth(LocalDate.of(2014, Month.SEPTEMBER, 1))
                .setStartVacation(LocalDate.of(2014, Month.AUGUST, 25))
                .setLengthOfVacation(10);
        assertEquals(19, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkCashDaysInMonthWhenVacationInAnotherYear() {
        WorkWithCalendar countMonth = new WorkWithCalendar()
                .setCountMonth(LocalDate.of(2014, Month.SEPTEMBER, 1))
                .setStartVacation(LocalDate.of(2015, Month.AUGUST, 25))
                .setLengthOfVacation(14);
        assertEquals(22, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkCashDaysWhenAllCountMonthInVacation() {
        WorkWithCalendar countMonth = new WorkWithCalendar()
                .setCountMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setStartVacation(LocalDate.of(2014, Month.SEPTEMBER, 25))
                .setLengthOfVacation(50);
        assertEquals(0, countMonth.countCashDaysInMonth());
    }
}
