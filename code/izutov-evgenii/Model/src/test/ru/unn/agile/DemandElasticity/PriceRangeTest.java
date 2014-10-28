package ru.unn.agile.DemandElasticity;

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
        PriceRange range = new PriceRange(12, 25);
        assertNotNull(range);
    }

    @Test
    public void canSetInitialStartValue() {
        PriceRange range = new PriceRange(11, 23);
        assertEquals(11, range.getStartValue(), delta);
    }

    @Test
    public void canSetInitialEndValue() {
        PriceRange range = new PriceRange(1, 22);
        assertEquals(22, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void priceRangeThrowsExceptionOnNegativeStartValue() {
        new PriceRange(-10, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void priceRangeThrowsExceptionOnNegativeEndValue() {
        new PriceRange(7, -11);
    }

    @Test
    public void canSetArbitraryStartValue() {
        PriceRange range = new PriceRange();
        range.setStartValue(1);
        assertEquals(1, range.getStartValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setStartValueThrowsExceptionOnNegativeValue() {
        PriceRange range = new PriceRange();
        range.setStartValue(-1);
    }

    @Test
    public void canSetArbitraryEndValue() {
        PriceRange range = new PriceRange();
        range.setEndValue(2);
        assertEquals(2, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEndValueThrowsExceptionOnNegativeValue() {
        PriceRange range = new PriceRange();
        range.setEndValue(-1);
    }

    @Test
    public void canCalculateDifferenceBetweenDifferentNumbers() {
        PriceRange range = new PriceRange(17, 23);
        assertEquals(6, range.calculateDifference(), delta);
    }

    @Test
    public void canCalculateDifferenceBetweenEqualNumbers() {
        PriceRange range = new PriceRange(21, 21);
        assertEquals(0, range.calculateDifference(), delta);
    }

    @Test
    public void canCalculateSumBetweenDifferentNumbers() {
        PriceRange range = new PriceRange(4, 19);
        assertEquals(23, range.calculateSum(), delta);
    }

    @Test
    public void canCalculateSumBetweenEqualNumbers() {
        PriceRange range = new PriceRange(13, 13);
        assertEquals(26, range.calculateSum(), delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenDifferentNumbers() {
        PriceRange range = new PriceRange(6, 10);
        assertEquals(0.5, range.calculateRelativeChangeInMidpoint(), delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenEqualNumbers() {
        PriceRange range = new PriceRange(7, 7);
        assertEquals(0, range.calculateRelativeChangeInMidpoint(), delta);
    }

    @Test(expected = ArithmeticException.class)
    public void calculateRelativeChangeThrowsExceptionOnInitialValues() {
        PriceRange range = new PriceRange();
        range.calculateRelativeChangeInMidpoint();
    }

    @Test
    public void isZeroLengthRangeReturnsTrueOnEqualNumbers() {
        PriceRange range = new PriceRange(21, 21);
        assertTrue(range.isZeroLengthRange());
    }

    @Test
    public void isZeroLengthRangeReturnsFalseOnDifferentNumbers() {
        PriceRange range = new PriceRange(27, 12);
        assertFalse(range.isZeroLengthRange());
    }
}
