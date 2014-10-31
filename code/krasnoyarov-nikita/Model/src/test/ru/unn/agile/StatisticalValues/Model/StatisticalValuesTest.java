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

        expectedValue = statisticalCalculator.calculateExpectedValue();

        assertEquals(1.0, expectedValue, EPSILON);
    }

    @Test
    public void canCalculateExpectedValueWithTwoNumbers() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.3, 0.7);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;

        expectedValue = statisticalCalculator.calculateExpectedValue();

        assertEquals(1.7, expectedValue, EPSILON);
    }

    @Test
    public void canCalculateExpectedValueWithMultipleNumbers() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List<Double> probabilities = Arrays.asList(0.2, 0.2, 0.2, 0.2, 0.2);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;

        expectedValue = statisticalCalculator.calculateExpectedValue();

        assertEquals(3.0, expectedValue, EPSILON);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkExpectedValueSumProbabilitiesValueEqualsOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.1, 0.5);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        expectedValue = statisticalCalculator.calculateExpectedValue();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkExpectedValueNegativeProbabilitiesValue() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(-5.0, 0.9);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        expectedValue = statisticalCalculator.calculateExpectedValue();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkExpectedValueProbabilitiesValueMoreThanOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.3, 1.5);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        expectedValue = statisticalCalculator.calculateExpectedValue();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkExpectedValueDifferentSizeOfLists() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> probabilities = Arrays.asList(0.3, 0.7);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        expectedValue = statisticalCalculator.calculateExpectedValue();
    }

    @Test
    public void canCalculateVarianceWithOneNumber() {
        List<Double> values = Arrays.asList(1.0);
        List<Double> probabilities = Arrays.asList(1.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;

        variance = statisticalCalculator.calculateVariance();

        assertEquals(0.0, variance, EPSILON);
    }

    @Test
    public void canCalculateVarianceWithTwoNumbers() {
        List<Double> values = Arrays.asList(5.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.2, 0.8);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;

        variance = statisticalCalculator.calculateVariance();

        assertEquals(1.44, variance, EPSILON);
    }

    @Test
    public void canCalculateVarianceWithMultipleNumbers() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List<Double> probabilities = Arrays.asList(0.3, 0.4, 0.1, 0.1, 0.1);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;

        variance = statisticalCalculator.calculateVariance();

        assertEquals(1.61, variance, EPSILON);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkVarianceSumProbabilitiesValueEqualsOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.1, 0.5);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        variance = statisticalCalculator.calculateVariance();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkVarianceNegativeProbabilitiesValue() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(-5.0, 0.9);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        variance = statisticalCalculator.calculateVariance();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkVarianceProbabilitiesValueMoreThanOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.3, 1.5);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        variance = statisticalCalculator.calculateVariance();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkVarianceDifferentSizeOfLists() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> probabilities = Arrays.asList(0.3, 0.7);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        variance = statisticalCalculator.calculateVariance();
    }

    @Test
    public void canCalculateInitialMomentWithOneNumber() {
        List<Double> values = Arrays.asList(1.0);
        List<Double> probabilities = Arrays.asList(1.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;

        moment = statisticalCalculator.calculateInitialMoment(2);

        assertEquals(1.0, moment, EPSILON);
    }

    @Test
    public void canCalculateInitialMomentWithTwoNumbers() {
        List<Double> values = Arrays.asList(5.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.2, 0.8);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;

        moment = statisticalCalculator.calculateInitialMoment(2);

        assertEquals(8.2, moment, EPSILON);
    }

    @Test
    public void canCalculateInitialMomentWithMultipleNumbers() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List<Double> probabilities = Arrays.asList(0.1, 0.2, 0.3, 0.4, 0.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;

        moment = statisticalCalculator.calculateInitialMoment(2);

        assertEquals(10.0, moment, EPSILON);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkInitialMomentSumProbabilitiesValueEqualsOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.1, 0.5);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        moment = statisticalCalculator.calculateInitialMoment(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkInitialMomentNegativeProbabilitiesValue() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(-5.0, 0.9);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        moment = statisticalCalculator.calculateInitialMoment(2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkInitialMomentProbabilitiesValueMoreThanOne() {
        List<Double> values = Arrays.asList(1.0, 2.0);
        List<Double> probabilities = Arrays.asList(0.3, 1.5);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        moment = statisticalCalculator.calculateInitialMoment(2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkInitialMomentDifferentSizeOfLists() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> probabilities = Arrays.asList(0.3, 0.7);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        moment = statisticalCalculator.calculateInitialMoment(2);
    }
}
