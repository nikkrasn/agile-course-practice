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
        assertEquals(1, calculator.calculateExpectedValue(), Float.MIN_VALUE);
    }

    @Test
    public void canCalculateExpectedValueWithTwoNumbers() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(0.3, 0.7);
        StatisticalValues calculator = new StatisticalValues(values, probabilities);
        assertEquals(1.7, calculator.calculateExpectedValue(), Float.MIN_VALUE);
    }

}
