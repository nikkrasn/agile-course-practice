package ru.unn.agile.calculateSalary;

import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class CalculateSalaryTests {

    @Test
    public void NullWhenSalaryNegatively(){
        double salary = -10000;
        LocalDate month = LocalDate.of(2014, Month.OCTOBER, 1);
        LocalDate vacation = LocalDate.of(2014, Month.OCTOBER, 1);
        assertEquals(0, CalculateSalary.CountCash(salary, month, 168, vacation, 14), 0);
    }

    @Test
    public void countCashInMonth(){
        double salary = 10000;
        LocalDate month = LocalDate.of(2014, Month.OCTOBER, 1);
        LocalDate vacation = LocalDate.of(2014, Month.NOVEMBER, 1);
        assertEquals(8700, CalculateSalary.CountCash(salary, month, 176, vacation, 14), 0);
    }

    @Test
    public void countCashInMonthWithOvertime(){
        double salary = 10000;
        LocalDate month = LocalDate.of(2014, Month.OCTOBER, 1);
        LocalDate vacation = LocalDate.of(2014, Month.NOVEMBER, 1);
        assertEquals(10213, CalculateSalary.CountCash(salary, month, 200, vacation, 14), 1);
    }

    @Test
    public void countCashInMonthWith14DaysOfVacation(){
        double salary = 10000;
        LocalDate month = LocalDate.of(2014, Month.OCTOBER, 1);
        LocalDate vacation = LocalDate.of(2014, Month.OCTOBER, 6);
        assertEquals(4917, CalculateSalary.CountCash(salary, month, 104, vacation, 14), 1);
    }

    @Test
    public void countCashInMonthWithStartVacationButEndVacationInAnotherMonth(){
        double salary = 10000;
        LocalDate month = LocalDate.of(2014, Month.OCTOBER, 1);
        LocalDate vacation = LocalDate.of(2014, Month.OCTOBER, 27);
        assertEquals(6808, CalculateSalary.CountCash(salary, month, 144, vacation, 14), 1);
    }

    @Test
    public void countCashInMonthWithStartVacationInAnotherMonthButEndVacationInCashMonth(){
        double salary = 10000;
        LocalDate month = LocalDate.of(2014, Month.NOVEMBER, 1);
        LocalDate vacation = LocalDate.of(2014, Month.OCTOBER, 27);
        assertEquals(6525, CalculateSalary.CountCash(salary, month, 120, vacation, 14), 1);
    }

    @Test
    public void countCashInMonthWhichInYearNotEqualYearOfVacation(){
        double salary = 10000;
        LocalDate month = LocalDate.of(2014, Month.NOVEMBER, 1);
        LocalDate vacation = LocalDate.of(2015, Month.OCTOBER, 27);
        assertEquals(8700, CalculateSalary.CountCash(salary, month, 160, vacation, 14), 1);
    }

}
