package ru.unn.agile.calculateSalary;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class CalculateSalaryTests {

    @Test
    public void canCalculateCashWithNegativeSalary() {
        CalculateSalary calculator = new CalculateSalary().setSalary(-10000);
        assertEquals(0, calculator.calculate(), 0);
    }

    @Test
    public void canCalculateCashInNormalMonth() {
        CalculateSalary calculator = new CalculateSalary().setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(184);
        assertEquals(8700.0, calculator.calculate(), 0.1);
    }

    @Test
    public void canCalculateCashInNormalMonthWithOvertime() {
        CalculateSalary calculator = new CalculateSalary().setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(200);
        assertEquals(10213.0, calculator.calculate(), 0.1);
    }

    @Test
    public void canCalculateCashInNormalMonthWithLessHours() {
        CalculateSalary calculator = new CalculateSalary().setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(154);
        assertEquals(7281.0, calculator.calculate(), 1.0);
    }

    @Test
    public void countCashInMonthWith14DaysOfVacation() {
        CalculateSalary calculator = new CalculateSalary().setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(104)
                .setStartOfVacation(LocalDate.of(2014, Month.OCTOBER, 6))
                .setLengthOfVacation(14);
        assertEquals(4917.0, calculator.calculate(), 1.0);
    }

    @Test
    public void countCashInMonthWithStartOfVacationAndFinishInAnother() {
        CalculateSalary calculator = new CalculateSalary().setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(104)
                .setStartOfVacation(LocalDate.of(2014, Month.OCTOBER, 27))
                .setLengthOfVacation(14);
        assertEquals(4917.0, calculator.calculate(), 1.0);
    }

    @Test
    public void countCashInMonthWithEndOfVacation() {
        CalculateSalary calculator = new CalculateSalary().setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(144)
                .setStartOfVacation(LocalDate.of(2014, Month.OCTOBER, 27))
                .setLengthOfVacation(14);
        assertEquals(6808.0, calculator.calculate(), 1.0);
    }

    @Test
    public void countCashInMonthWithEndOfVacationFromAnotherMonth() {
        CalculateSalary calculator = new CalculateSalary().setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.NOVEMBER, 1))
                .setWorkedHourInMonth(120)
                .setStartOfVacation(LocalDate.of(2014, Month.OCTOBER, 27))
                .setLengthOfVacation(14);
        assertEquals(6525.0, calculator.calculate(), 1.0);
    }

    @Test
    public void countCashInMonthWhichInYearNotEqualYearOfVacation() {
        CalculateSalary calculator = new CalculateSalary().setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.NOVEMBER, 1))
                .setWorkedHourInMonth(160)
                .setStartOfVacation(LocalDate.of(2015, Month.OCTOBER, 27))
                .setLengthOfVacation(14);
        assertEquals(8700.0, calculator.calculate(), 1.0);
    }

    @Test
    public void countCashInMonthWithOvertimeAndWithoutVacation() {
        CalculateSalary calculator = new CalculateSalary().setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.NOVEMBER, 1))
                .setWorkedHourInMonth(160);
        assertEquals(8700.0, calculator.calculate(), 1.0);
    }

    @Test
    public void countCashInMonthWhichAllInVacation() {
        CalculateSalary calculator = new CalculateSalary().setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(0)
                .setStartOfVacation(LocalDate.of(2015, Month.SEPTEMBER, 27))
                .setLengthOfVacation(50);
        assertEquals(0.0, calculator.calculate(), 1.0);
    }
}
