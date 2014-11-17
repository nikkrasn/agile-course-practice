package ru.unn.agile.calculateSalary;

import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class CalculateSalaryTests {

    @Test
    public void NullWhenSalaryNegativelyTest(){
        double exampleSalary = -10000;
        LocalDate exampleMonth = LocalDate.of(2014, Month.OCTOBER, 1);
        LocalDate exampleVacation = LocalDate.of(2014, Month.OCTOBER, 1);
        assertEquals(0, CalculateSalary.CountingCash(exampleSalary, exampleMonth, 168, exampleVacation, 14), 0);
    }

    @Test
    public void countingCashInMonthTest(){
        double exampleSalary = 10000;
        LocalDate exampleMonth = LocalDate.of(2014, Month.OCTOBER, 1);
        LocalDate exampleVacation = LocalDate.of(2014, Month.NOVEMBER, 1);
        assertEquals(8700, CalculateSalary.CountingCash(exampleSalary, exampleMonth, 176, exampleVacation, 14), 0);
    }

    @Test
    public void countingCashInMonthWithOvertimeTest(){
        double exampleSalary = 10000;
        LocalDate exampleMonth = LocalDate.of(2014, Month.OCTOBER, 1);
        LocalDate exampleVacation = LocalDate.of(2014, Month.NOVEMBER, 1);
        assertEquals(10213, CalculateSalary.CountingCash(exampleSalary, exampleMonth, 200, exampleVacation, 14), 1);
    }

    @Test
    public void countingCashInMonthWith14DaysOfVacationTest(){
        double exampleSalary = 10000;
        LocalDate exampleMonth = LocalDate.of(2014, Month.OCTOBER, 1);
        LocalDate exampleVacation = LocalDate.of(2014, Month.OCTOBER, 6);
        assertEquals(4917, CalculateSalary.CountingCash(exampleSalary, exampleMonth, 104, exampleVacation, 14), 1);
    }

    @Test
    public void countingCashInMonthWithStartVacationButEndVacationInAnotherMonthTest(){
        double exampleSalary = 10000;
        LocalDate exampleMonth = LocalDate.of(2014, Month.OCTOBER, 1);
        LocalDate exampleVacation = LocalDate.of(2014, Month.OCTOBER, 27);
        assertEquals(6808, CalculateSalary.CountingCash(exampleSalary, exampleMonth, 144, exampleVacation, 14), 1);
    }

    @Test
    public void countingCashInMonthWithStartVacationInAnotherMonthButEndVacationInCashMonthTest(){
        double exampleSalary = 10000;
        LocalDate exampleMonth = LocalDate.of(2014, Month.NOVEMBER, 1);
        LocalDate exampleVacation = LocalDate.of(2014, Month.OCTOBER, 27);
        assertEquals(6525, CalculateSalary.CountingCash(exampleSalary, exampleMonth, 120, exampleVacation, 14), 1);
    }

    @Test
    public void countingCashInMonthWhichInYearNotEqualYearOfVacationTest(){
        double exampleSalary = 10000;
        LocalDate exampleMonth = LocalDate.of(2014, Month.NOVEMBER, 1);
        LocalDate exampleVacation = LocalDate.of(2015, Month.OCTOBER, 27);
        assertEquals(8700, CalculateSalary.CountingCash(exampleSalary, exampleMonth, 160, exampleVacation, 14), 1);
    }

}
