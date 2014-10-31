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
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
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
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
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
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        }
        catch (Exception e)
        {

        }

        assertEquals(3.0, expectedValue, Float.MIN_VALUE);
    }

    @Test
    public void checkExpectedValueSumProbabilitiesValueEqualsOne() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(0.1, 0.5);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("Sum of probabilities doesn't equal to 1", message);
    }

    @Test
    public void checkExpectedValueNegativeProbabilitiesValue() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(-5.0, 0.9);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is negative", message);
    }

    @Test
    public void checkExpectedValueProbabilitiesValueMoreOne() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(0.3, 1.5);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is more than 1", message);
    }

    @Test
    public void checkExpectedValueDifferentSizeOfLists() {
        List values = Arrays.asList(1.0, 2.0, 3.0);
        List probabilities = Arrays.asList(0.3, 0.7);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("Size of values and probabilities lists doesn't match", message);
    }

    @Test
    public void canCalculateVarianceWithOneNumber() {
        List values = Arrays.asList(1.0);
        List probabilities = Arrays.asList(1.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;

        try {
            variance = statisticalCalculator.calculateVariance();
        }
        catch (Exception e)
        {

        }

        assertEquals(0, variance, Float.MIN_VALUE);
    }

    @Test
    public void canCalculateVarianceWithTwoNumbers() {
        List values = Arrays.asList(2.0, 2.0);
        List probabilities = Arrays.asList(0.3, 0.7);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;

        try {
            variance = statisticalCalculator.calculateVariance();
        }
        catch (Exception e)
        {

        }

        assertEquals(0.0, variance, Float.MIN_VALUE);
    }

    @Test
    public void canCalculateVarianceWithMultipleNumbers() {
        List values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List probabilities = Arrays.asList(0.2, 0.2, 0.2, 0.2, 0.2);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;

        try {
            variance = statisticalCalculator.calculateVariance();
        }
        catch (Exception e)
        {

        }

        assertEquals(2.0, variance, Float.MIN_VALUE);
    }

    @Test
    public void checkVarianceSumProbabilitiesValueEqualsOne() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(0.1, 0.5);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            variance = statisticalCalculator.calculateVariance();
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("Sum of probabilities doesn't equal to 1", message);
    }

    @Test
    public void checkVarianceNegativeProbabilitiesValue() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(-5.0, 0.9);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            variance = statisticalCalculator.calculateVariance();
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is negative", message);
    }

    @Test
    public void checkVarianceProbabilitiesValueMoreOne() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(0.3, 1.5);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            variance = statisticalCalculator.calculateVariance();
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is more than 1", message);
    }

    @Test
    public void checkVarianceDifferentSizeOfLists() {
        List values = Arrays.asList(1.0, 2.0, 3.0);
        List probabilities = Arrays.asList(0.3, 0.7);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            variance = statisticalCalculator.calculateVariance();
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("Size of values and probabilities lists doesn't match", message);
    }

    @Test
    public void canCalculateInitialMomentWithOneNumber() {
        List values = Arrays.asList(1.0);
        List probabilities = Arrays.asList(1.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        }
        catch (Exception e)
        {

        }

        assertEquals(1.0, moment, Float.MIN_VALUE);
    }

    @Test
    public void canCalculateInitialMomentWithTwoNumbers() {
        List values = Arrays.asList(2.0, 2.0);
        List probabilities = Arrays.asList(0.3, 0.7);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        }
        catch (Exception e)
        {

        }

        assertEquals(4.0, moment, Float.MIN_VALUE);
    }

    @Test
    public void canCalculateInitialMomentWithMultipleNumbers() {
        List values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List probabilities = Arrays.asList(0.2, 0.2, 0.2, 0.2, 0.2);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        }
        catch (Exception e)
        {

        }

        assertEquals(11.0, moment, Float.MIN_VALUE);
    }

    @Test
    public void checkInitialMomentSumProbabilitiesValueEqualsOne() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(0.1, 0.5);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("Sum of probabilities doesn't equal to 1", message);
    }

    @Test
    public void checkInitialMomentNegativeProbabilitiesValue() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(-5.0, 0.9);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is negative", message);
    }

    @Test
    public void checkInitialMomentProbabilitiesValueMoreOne() {
        List values = Arrays.asList(1.0, 2.0);
        List probabilities = Arrays.asList(0.3, 1.5);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is more than 1", message);
    }

    @Test
    public void checkInitialMomentDifferentSizeOfLists() {
        List values = Arrays.asList(1.0, 2.0, 3.0);
        List probabilities = Arrays.asList(0.3, 0.7);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        }
        catch (Exception e)
        {
            message = e.getMessage();
        }

        assertEquals("Size of values and probabilities lists doesn't match", message);
    }

}
