package ru.unn.agile.StatisticalValues.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StatisticalValuesTest {

    private static final double EPSILON = 0.0001;
    private List<Double> values = new ArrayList<>();
    private List<Double> probabilities = new ArrayList<>();

    @Test
    public void canCalculateExpectedValueWithOneNumber() {
        values = Arrays.asList(1.0);
        probabilities = Arrays.asList(1.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;

        expectedValue = statisticalCalculator.calculateExpectedValue();

        assertEquals(1.0, expectedValue, EPSILON);
    }

    @Test
    public void canCalculateExpectedValueWithTwoNumbers() {
        values = Arrays.asList(5.0, 3.0);
        probabilities = Arrays.asList(0.4, 0.6);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;

        expectedValue = statisticalCalculator.calculateExpectedValue();

        assertEquals(3.8, expectedValue, EPSILON);
    }

    @Test
    public void canCalculateExpectedValueWithMultipleNumbers() {
        values = Arrays.asList(1.0, 2.0, 4.0, 3.0, 5.0);
        probabilities = Arrays.asList(0.2, 0.3, 0.0, 0.1, 0.4);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double expectedValue = 0;

        expectedValue = statisticalCalculator.calculateExpectedValue();

        assertEquals(3.1, expectedValue, EPSILON);
    }

    @Test
    public void canCalculateVarianceWithOneNumber() {
        values = Arrays.asList(3.0);
        probabilities = Arrays.asList(1.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;

        variance = statisticalCalculator.calculateVariance();

        assertEquals(0.0, variance, EPSILON);
    }

    @Test
    public void canCalculateVarianceWithTwoNumbers() {
        values = Arrays.asList(2.0, 3.0);
        probabilities = Arrays.asList(0.3, 0.7);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;

        variance = statisticalCalculator.calculateVariance();

        assertEquals(0.21, variance, EPSILON);
    }

    @Test
    public void canCalculateVarianceWithMultipleNumbers() {
        values = Arrays.asList(2.0, 5.0, 1.0, 3.0, 4.0);
        probabilities = Arrays.asList(0.1, 0.5, 0.1, 0.0, 0.3);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double variance = 0;

        variance = statisticalCalculator.calculateVariance();

        assertEquals(1.8, variance, EPSILON);
    }

    @Test
    public void canCalculateInitialMomentWithOneNumber() {
        values = Arrays.asList(5.0);
        probabilities = Arrays.asList(1.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;

        moment = statisticalCalculator.calculateInitialMoment(2);

        assertEquals(25.0, moment, EPSILON);
    }

    @Test
    public void canCalculateInitialMomentWithTwoNumbers() {
        values = Arrays.asList(4.0, 3.0);
        probabilities = Arrays.asList(0.1, 0.9);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;

        moment = statisticalCalculator.calculateInitialMoment(2);

        assertEquals(9.7, moment, EPSILON);
    }

    @Test
    public void canCalculateInitialMomentWithMultipleNumbers() {
        values = Arrays.asList(1.0, 3.0, 4.0, 5.0, 8.0);
        probabilities = Arrays.asList(0.3, 0.1, 0.2, 0.4, 0.0);
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);
        double moment = 0;

        moment = statisticalCalculator.calculateInitialMoment(2);

        assertEquals(14.4, moment, EPSILON);
    }

    @Before
    public void setUp() {
        values = Arrays.asList(1.0, 2.0);
    }
    @Test (expected = IllegalArgumentException.class)
    public void checkExpectedValueSumProbabilitiesValueEqualsOne() {
        probabilities = Arrays.asList(0.2, 0.6);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        expectedValue = statisticalCalculator.calculateExpectedValue();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkExpectedValueNegativeProbabilitiesValue() {
        probabilities = Arrays.asList(0.1, -2.0);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        expectedValue = statisticalCalculator.calculateExpectedValue();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkExpectedValueProbabilitiesValueMoreThanOne() {
        probabilities = Arrays.asList(2.3, 0.5);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        expectedValue = statisticalCalculator.calculateExpectedValue();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkExpectedValueDifferentSizeOfLists() {
        probabilities = Arrays.asList(0.2, 0.5, 0.3);
        double expectedValue = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        expectedValue = statisticalCalculator.calculateExpectedValue();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkVarianceSumProbabilitiesValueEqualsOne() {
        probabilities = Arrays.asList(0.1, 0.5);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        variance = statisticalCalculator.calculateVariance();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkVarianceProbabilitiesValueMoreThanOne() {
        probabilities = Arrays.asList(0.1, 7.5);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        variance = statisticalCalculator.calculateVariance();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkVarianceDifferentSizeOfLists() {
        probabilities = Arrays.asList(0.3, 0.1, 0.1, 0.1, 0.3);
        double variance = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        variance = statisticalCalculator.calculateVariance();
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkInitialMomentSumProbabilitiesValueEqualsOne() {
        probabilities = Arrays.asList(0.6, 0.6);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        moment = statisticalCalculator.calculateInitialMoment(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkInitialMomentNegativeProbabilitiesValue() {
        probabilities = Arrays.asList(-2.0, -7.9);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        moment = statisticalCalculator.calculateInitialMoment(3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkInitialMomentProbabilitiesValueMoreThanOne() {
        probabilities = Arrays.asList(0.3, 1.5);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        moment = statisticalCalculator.calculateInitialMoment(4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkInitialMomentDifferentSizeOfLists() {
        probabilities = Arrays.asList(1.0);
        double moment = 0;
        StatisticalValues statisticalCalculator = new StatisticalValues(values, probabilities);

        moment = statisticalCalculator.calculateInitialMoment(5);
    }
}
