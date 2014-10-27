package tdd;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculateSalaryTests {

    @Test
    public void watchSalaryTest(){
        int example = 10000;
        assertEquals(10000, CalculateSalary.withoutNothing(example));
    }
}
