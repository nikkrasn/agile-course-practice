package ru.unn.agile.calculateSalary;

import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.Assert.*;

public class CalculateSalaryTests {
    private final double delta = 1e-10;

    @Test
    public void canCalculateCashWithNegativeSalary() {
        CalculateSalary calculator = new CalculateSalary().setSalary(-10000);
        assertEquals(0, calculator.calculate(), delta);
    }

    @Test
    public void inputNegativeLengthOfVacation() {
        CalculateSalary calculator = new CalculateSalary().setLengthOfVacation(-14);
        assertEquals(0, calculator.calculate(), delta);
    }

    @Test
    public void inputNegativeWorkedHours() {
        CalculateSalary calculator = new CalculateSalary().setWorkedHourInMonth(-180);
        assertEquals(0, calculator.calculate(), delta);
    }

    @Test
    public void inputOnlyNegativeWorkedHours() {
        CalculateSalary calculator = new CalculateSalary()
                .setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.APRIL, 1))
                .setWorkedHourInMonth(-200);
        assertEquals(0, calculator.calculate(), delta);
    }

    @Test
    public void canCalculateCashInNormalMonth() {
        CalculateSalary calculator = new CalculateSalary()
                .setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(184);
        assertEquals(8700.0, calculator.calculate(), delta);
    }

    @Test
    public void canCalculateCashInNormalMonthWithOvertime() {
        CalculateSalary calculator = new CalculateSalary()
                .setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(200);
        assertEquals(10213.04347826086548, calculator.calculate(), delta);
    }

    @Test
    public void canCalculateCashInNormalMonthWithLessHours() {
        CalculateSalary calculator = new CalculateSalary()
                .setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(154);
        assertEquals(7281.52173913043187, calculator.calculate(), delta);
    }

    @Test
    public void countCashInMonthWith14DaysOfVacation() {
        CalculateSalary calculator = new CalculateSalary()
                .setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(104)
                .setStartOfVacation(LocalDate.of(2014, Month.OCTOBER, 6))
                .setLengthOfVacation(14);
        assertEquals(4917.39130434782412, calculator.calculate(), delta);
    }

    @Test
    public void countCashInMonthWithStartOfVacationAndFinishInAnother() {
        CalculateSalary calculator = new CalculateSalary()
                .setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(144)
                .setStartOfVacation(LocalDate.of(2014, Month.OCTOBER, 27))
                .setLengthOfVacation(14);
        assertEquals(6808.69565217391032, calculator.calculate(), delta);
    }

    @Test
    public void countCashInMonthWithEndOfVacation() {
        CalculateSalary calculator = new CalculateSalary()
                .setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.NOVEMBER, 1))
                .setWorkedHourInMonth(120)
                .setStartOfVacation(LocalDate.of(2014, Month.OCTOBER, 27))
                .setLengthOfVacation(14);
        assertEquals(6525.0, calculator.calculate(), delta);
    }

    @Test
    public void countCashInMonthWhichInYearNotEqualYearOfVacation() {
        CalculateSalary calculator = new CalculateSalary()
                .setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.NOVEMBER, 1))
                .setWorkedHourInMonth(160)
                .setStartOfVacation(LocalDate.of(2015, Month.OCTOBER, 27))
                .setLengthOfVacation(14);
        assertEquals(8700.0, calculator.calculate(), delta);
    }

    @Test
    public void countCashInMonthWithOvertimeAndWithoutVacation() {
        CalculateSalary calculator = new CalculateSalary()
                .setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.NOVEMBER, 1))
                .setWorkedHourInMonth(160);
        assertEquals(8700.0, calculator.calculate(), delta);
    }

    @Test
    public void countCashInMonthWhichAllInVacation() {
        CalculateSalary calculator = new CalculateSalary()
                .setSalary(10000)
                .setCountingMonth(LocalDate.of(2014, Month.OCTOBER, 1))
                .setWorkedHourInMonth(0)
                .setStartOfVacation(LocalDate.of(2015, Month.SEPTEMBER, 27))
                .setLengthOfVacation(50);
        assertEquals(0.0, calculator.calculate(), delta);
    }
}
