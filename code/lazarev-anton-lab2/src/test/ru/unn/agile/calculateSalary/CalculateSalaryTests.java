package ru.unn.agile.calculateSalary;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculateSalaryTests {

    @Test
    public void NullWhenSalaryNegativelyTest(){
        double example = -10000;
        assertEquals(0, CalculateSalary.CountingCash(example), 0);
    }

    @Test
    public void SalaryWithNDSTest(){
        double example = 10000;
        assertEquals(8700, CalculateSalary.CountingCash(example), 0);
    }
}
