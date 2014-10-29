package ru.unn.agile.DemandElasticity.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PriceRangeTest {
    private final double delta = 0.000001;

    @Test
    public void canCreatePriceRangeWithDefaultValues() {
        PriceRange range = new PriceRange();

        assertNotNull(range);
    }

    @Test
    public void canCreatePriceRangeWithInitialValues() {
        PriceRange range = new PriceRange(12d, 25d);

        assertNotNull(range);
    }

    @Test
    public void canSetInitialStartValue() {
        PriceRange range = new PriceRange(11d, 23d);

        assertEquals(11d, range.getStartValue(), delta);
    }

    @Test
    public void canSetInitialEndValue() {
        PriceRange range = new PriceRange(1d, 22d);

        assertEquals(22d, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void priceRangeThrowsExceptionOnNegativeStartValue() {
        new PriceRange(-10d, 20d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void priceRangeThrowsExceptionOnNegativeEndValue() {
        new PriceRange(7d, -11d);
    }

    @Test
    public void canSetArbitraryStartValue() {
        PriceRange range = new PriceRange();

        range.setStartValue(1d);

        assertEquals(1d, range.getStartValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setStartValueThrowsExceptionOnNegativeValue() {
        PriceRange range = new PriceRange();

        range.setStartValue(-1d);
    }

    @Test
    public void canSetArbitraryEndValue() {
        PriceRange range = new PriceRange();

        range.setEndValue(2d);

        assertEquals(2d, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEndValueThrowsExceptionOnNegativeValue() {
        PriceRange range = new PriceRange();

        range.setEndValue(-1d);
    }

    @Test
    public void canCalculateDifferenceBetweenDifferentNumbers() {
        PriceRange range = new PriceRange(11d, 29d);
        double expectedDifference = 29d - 11d;

        double result = range.calculateDifference();

        assertEquals(expectedDifference, result, delta);
    }

    @Test
    public void canCalculateDifferenceBetweenEqualNumbers() {
        PriceRange range = new PriceRange(64d, 64d);
        double expectedDifference = 64d - 64d;

        double result = range.calculateDifference();

        assertEquals(expectedDifference, result, delta);
    }

    @Test
    public void canCalculateSumBetweenDifferentNumbers() {
        PriceRange range = new PriceRange(73d, 97d);
        double expectedSum = 73d + 97d;

        double result = range.calculateSum();

        assertEquals(expectedSum, result, delta);
    }

    @Test
    public void canCalculateSumBetweenEqualNumbers() {
        PriceRange range = new PriceRange(51d, 51d);
        double expectedSum = 51d + 51d;

        double result = range.calculateSum();

        assertEquals(expectedSum, result, delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenDifferentNumbers() {
        PriceRange range = new PriceRange(99d, 37d);
        double expectedRelativeChangeInMidpoint = (37d - 99d) / ((99d + 37d) / 2d);

        double result = range.calculateRelativeChangeInMidpoint();

        assertEquals(expectedRelativeChangeInMidpoint, result, delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenEqualNumbers() {
        PriceRange range = new PriceRange(83d, 83d);
        double expectedRelativeChangeInMidpoint = (83d - 83d) / ((83d + 83d) / 2d);

        double result = range.calculateRelativeChangeInMidpoint();

        assertEquals(expectedRelativeChangeInMidpoint, result, delta);
    }

    @Test(expected = ArithmeticException.class)
    public void calculateRelativeChangeThrowsExceptionOnInitialValues() {
        PriceRange range = new PriceRange();

        range.calculateRelativeChangeInMidpoint();
    }

    @Test
    public void isZeroLengthRangeReturnsTrueOnEqualNumbers() {
        PriceRange range = new PriceRange(55d, 55d);

        assertTrue(range.isZeroLengthRange());
    }

    @Test
    public void isZeroLengthRangeReturnsFalseOnDifferentNumbers() {
        PriceRange range = new PriceRange(44d, 87d);

        assertFalse(range.isZeroLengthRange());
    }
}
