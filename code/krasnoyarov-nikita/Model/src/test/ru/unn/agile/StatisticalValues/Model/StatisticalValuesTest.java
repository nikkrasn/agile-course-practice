package ru.unn.agile.StatisticalValues.Model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StatisticalValuesTest {

    @Test
    public void canCalculateExpectedValueWithOneNumber() {
        List values = Arrays.asList(1.0);
        List probabilities = Arrays.asList(1.0);
        StatisticalValues calculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;

        try {
            expectedValue = calculator.calculateExpectedValue();
        }
        catch (Exception e)
        {

        }

        assertEquals(1, expectedValue, Float.MIN_VALUE);
    }

    @Test
    public void canCalculateExpectedValueWithTwoNumbers() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(0.3, 0.7);
        StatisticalValues calculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;

        try {
            expectedValue = calculator.calculateExpectedValue();
        }
        catch (Exception e)
        {

        }

        assertEquals(1.7, expectedValue, Float.MIN_VALUE);
    }

    @Test
    public void canCalculateExpectedValueWithMultipleNumbers() {
        List values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List probabilities = Arrays.asList(0.2, 0.2, 0.2, 0.2, 0.2);
        StatisticalValues calculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;

        try {
            expectedValue = calculator.calculateExpectedValue();
        }
        catch (Exception e)
        {

        }

        assertEquals(3.0, expectedValue, Float.MIN_VALUE);
    }

    @Test
    public void checkSumProbabilitiesValueEqualsOne() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(0.1, 0.5);
        double expectedValue = 0;
        StatisticalValues calculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            expectedValue = calculator.calculateExpectedValue();
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("Sum of probabilities doesn't equal to 1", message);
    }

}
