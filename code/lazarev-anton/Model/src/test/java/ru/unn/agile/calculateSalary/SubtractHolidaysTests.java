package ru.unn.agile.calculateSalary;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SubtractHolidaysTests {
    @Test
    public void checkNotDayOff() {
        SubtractHolidays countMonth = new SubtractHolidays()
                .setCheckMonth(LocalDate.of(2014, Month.SEPTEMBER, 1));
        assertTrue(countMonth.isNotDayOff(3));
    }

    @Test
    public void checkDayOff() {
        SubtractHolidays countMonth = new SubtractHolidays()
                .setCheckMonth(LocalDate.of(2014, Month.SEPTEMBER, 1));
        assertFalse(countMonth.isNotDayOff(7));
    }

    @Test
    public void checkDayForSubtractionWithoutVacation() {
        SubtractHolidays countMonth = new SubtractHolidays()
                .setCheckMonth(LocalDate.of(2014, Month.AUGUST, 1));
        assertEquals(0, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkDayForSubtractionWitSameVacationMonth() {
        SubtractHolidays countMonth = new SubtractHolidays()
                .setCheckMonth(LocalDate.of(2014, Month.AUGUST, 1))
                .setStartVacation(LocalDate.of(2014, Month.AUGUST, 4))
                .setLengthVacation(14);
        assertEquals(10, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkDayForSubtractionWitDifferentVacationMonth() {
        SubtractHolidays countMonth = new SubtractHolidays()
                .setCheckMonth(LocalDate.of(2014, Month.AUGUST, 1))
                .setStartVacation(LocalDate.of(2014, Month.JULY, 28))
                .setLengthVacation(14);
        assertEquals(6, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkSubtractionDayWithStartInCountMonthButEndInAnother() {
        SubtractHolidays countMonth = new SubtractHolidays()
                .setCheckMonth(LocalDate.of(2014, Month.AUGUST, 1))
                .setStartVacation(LocalDate.of(2014, Month.AUGUST, 25))
                .setLengthVacation(14);
        assertEquals(5, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkSubtractionDayWithVacationInMiddleOfMonth() {
        SubtractHolidays countMonth = new SubtractHolidays()
                .setCheckMonth(LocalDate.of(2014, Month.SEPTEMBER, 1))
                .setStartVacation(LocalDate.of(2014, Month.SEPTEMBER, 10))
                .setLengthVacation(14);
        assertEquals(10, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkSubtractionDayWithVacationEndInMiddleOfAnotherMonth() {
        SubtractHolidays countMonth = new SubtractHolidays()
                .setCheckMonth(LocalDate.of(2014, Month.NOVEMBER, 1))
                .setStartVacation(LocalDate.of(2014, Month.OCTOBER, 28))
                .setLengthVacation(14);
        assertEquals(6, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkSubtractionDayWithVacationStartInMiddleOfCountMonth() {
        SubtractHolidays countMonth = new SubtractHolidays()
                .setCheckMonth(LocalDate.of(2014, Month.MARCH, 1))
                .setStartVacation(LocalDate.of(2014, Month.MARCH, 21))
                .setLengthVacation(14);
        assertEquals(7, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkSubtractionDayWhenAllMonthInVacation() {
        SubtractHolidays countMonth = new SubtractHolidays()
                .setCheckMonth(LocalDate.of(2014, Month.JULY, 1))
                .setStartVacation(LocalDate.of(2014, Month.JUNE, 19))
                .setLengthVacation(50);
        assertEquals(23, countMonth.getHolidaysInVacation());
    }
}
