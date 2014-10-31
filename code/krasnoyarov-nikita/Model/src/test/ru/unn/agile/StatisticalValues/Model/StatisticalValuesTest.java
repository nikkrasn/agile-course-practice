package ru.unn.agile.StatisticalValues.Model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StatisticalValuesTest {

    public static final double EPSILON = 0.0001;

    @Test
    public void canCalculateExpectedValueWithOneNumber() {
        List<Double> values = Arrays.asList(1.0);
        List<Double> probabilities = Arrays.asList(1.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;
        String message = "";

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals(1.0, expectedValue, EPSILON);
    }

    @Test
    public void canCalculateExpectedValueWithTwoNumbers() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.3, 0.7);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;
        String message = "";

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals(1.7, expectedValue, EPSILON);
    }

    @Test
    public void canCalculateExpectedValueWithMultipleNumbers() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List<Double> probabilities = Arrays.asList(0.2, 0.2, 0.2, 0.2, 0.2);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;
        String message = "";

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals(3.0, expectedValue, EPSILON);
    }

    @Test
    public void checkExpectedValueSumProbabilitiesValueEqualsOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.1, 0.5);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("Sum of probabilities doesn't equal to 1", message);
    }

    @Test
    public void checkExpectedValueNegativeProbabilitiesValue() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(-5.0, 0.9);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is negative", message);
    }

    @Test
    public void checkExpectedValueProbabilitiesValueMoreThanOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.3, 1.5);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is more than 1", message);
    }

    @Test
    public void checkExpectedValueDifferentSizeOfLists() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> probabilities = Arrays.asList(0.3, 0.7);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            expectedValue = statisticalCalculator.calculateExpectedValue();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("Size of values and probabilities lists doesn't match", message);
    }

    @Test
    public void canCalculateVarianceWithOneNumber() {
        List<Double> values = Arrays.asList(1.0);
        List<Double> probabilities = Arrays.asList(1.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;
        String message = "";

        try {
            variance = statisticalCalculator.calculateVariance();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals(0.0, variance, Float.MIN_VALUE);
    }

    @Test
    public void canCalculateVarianceWithTwoNumbers() {
        List<Double> values = Arrays.asList(5.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.2, 0.8);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;
        String message = "";

        try {
            variance = statisticalCalculator.calculateVariance();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals(1.44, variance, EPSILON);
    }

    @Test
    public void canCalculateVarianceWithMultipleNumbers() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List<Double> probabilities = Arrays.asList(0.3, 0.4, 0.1, 0.1, 0.1);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;
        String message = "";

        try {
            variance = statisticalCalculator.calculateVariance();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals(0.0, variance, EPSILON);
    }

    @Test
    public void checkVarianceSumProbabilitiesValueEqualsOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.1, 0.5);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            variance = statisticalCalculator.calculateVariance();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("Sum of probabilities doesn't equal to 1", message);
    }

    @Test
    public void checkVarianceNegativeProbabilitiesValue() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(-5.0, 0.9);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            variance = statisticalCalculator.calculateVariance();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is negative", message);
    }

    @Test
    public void checkVarianceProbabilitiesValueMoreThanOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.3, 1.5);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            variance = statisticalCalculator.calculateVariance();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is more than 1", message);
    }

    @Test
    public void checkVarianceDifferentSizeOfLists() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> probabilities = Arrays.asList(0.3, 0.7);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            variance = statisticalCalculator.calculateVariance();
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("Size of values and probabilities lists doesn't match", message);
    }

    @Test
    public void canCalculateInitialMomentWithOneNumber() {
        List<Double> values = Arrays.asList(1.0);
        List<Double> probabilities = Arrays.asList(1.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;
        String message = "";

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals(1.0, moment, EPSILON);
    }

    @Test
    public void canCalculateInitialMomentWithTwoNumbers() {
        List<Double> values = Arrays.asList(5.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.2, 0.8);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;
        String message = "";

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals(8.2, moment, EPSILON);
    }

    @Test
    public void canCalculateInitialMomentWithMultipleNumbers() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List<Double> probabilities = Arrays.asList(0.1, 0.2, 0.3, 0.4, 0.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;
        String message = "";

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals(10.0, moment, EPSILON);
    }

    @Test
    public void checkInitialMomentSumProbabilitiesValueEqualsOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.1, 0.5);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("Sum of probabilities doesn't equal to 1", message);
    }

    @Test
    public void checkInitialMomentNegativeProbabilitiesValue() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(-5.0, 0.9);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is negative", message);
    }

    @Test
    public void checkInitialMomentProbabilitiesValueMoreThanOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.3, 1.5);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("One of probabilities is more than 1", message);
    }

    @Test
    public void checkInitialMomentDifferentSizeOfLists() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> probabilities = Arrays.asList(0.3, 0.7);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        String message = "";

        try {
            moment = statisticalCalculator.calculateInitialMoment(2);
        } catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("Size of values and probabilities lists doesn't match", message);
    }

}
