package ru.unn.agile.salary;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculateSalaryTests {
    @Test
    public void ClearSalary() {
        int example = 10000;
        assertEquals(10000, CalculateSalary.withoutNothing(example));
    }

}
