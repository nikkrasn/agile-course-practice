package ru.unn.agile.calculateSalary;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class CalculateSalaryTests {

    @Test
    public void NullWhenSalaryNegativelyTest(){
        double example = -10000;
        LocalDate exampleMonth = LocalDate.of(2014, Month.OCTOBER, 1);
        assertEquals(0, CalculateSalary.CountingCash(example, exampleMonth), 0);
    }

    @Test
    public void countingCashInMonthWithoutHolidaysAndVacationTest(){
        double example = 10000;
        LocalDate exampleMonth = LocalDate.of(2014, Month.OCTOBER, 1);
        assertEquals(8700, CalculateSalary.CountingCash(example, exampleMonth), 0);
    }

    @Test
    public void countingCashInMonthWithout2WeekVacationTest(){
        double example = 10000;
        LocalDate exampleMonth = LocalDate.of(2014, Month.MARCH, 1);
        assertEquals(8700, CalculateSalary.CountingCash(example, exampleMonth), 0);
    }

}
