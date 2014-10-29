package ru.unn.agile.DemandElasticity.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class IncomeRangeTest {
    private final double delta = 0.000001;

    @Test
    public void canCreateIncomeRangeWithDefaultValues() {
        IncomeRange range = new IncomeRange();

        assertNotNull(range);
    }

    @Test
    public void canCreateIncomeRangeWithInitialValues() {
        IncomeRange range = new IncomeRange(12d, 25d);

        assertNotNull(range);
    }

    @Test
    public void canSetInitialStartValue() {
        IncomeRange range = new IncomeRange(11d, 23d);

        assertEquals(11d, range.getStartValue(), delta);
    }

    @Test
    public void canSetInitialEndValue() {
        IncomeRange range = new IncomeRange(1d, 22d);

        assertEquals(22d, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incomeRangeThrowsExceptionOnNegativeStartValue() {
        new IncomeRange(-10d, 20d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incomeRangeThrowsExceptionOnNegativeEndValue() {
        new IncomeRange(7d, -11d);
    }

    @Test
    public void canSetArbitraryStartValue() {
        IncomeRange range = new IncomeRange();

        range.setStartValue(1d);

        assertEquals(1d, range.getStartValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setStartValueThrowsExceptionOnNegativeValue() {
        IncomeRange range = new IncomeRange();

        range.setStartValue(-1d);
    }

    @Test
    public void canSetArbitraryEndValue() {
        IncomeRange range = new IncomeRange();

        range.setEndValue(2d);

        assertEquals(2d, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEndValueThrowsExceptionOnNegativeValue() {
        IncomeRange range = new IncomeRange();

        range.setEndValue(-1d);
    }

    @Test
    public void canCalculateDifferenceBetweenDifferentNumbers() {
        IncomeRange range = new IncomeRange(18d, 23d);
        double expectedDifference = 23d - 18d;

        double result = range.calculateDifference();

        assertEquals(expectedDifference, result, delta);
    }

    @Test
    public void canCalculateDifferenceBetweenEqualNumbers() {
        IncomeRange range = new IncomeRange(37d, 37d);
        double expectedDifference = 37d - 37d;

        double result = range.calculateDifference();

        assertEquals(expectedDifference, result, delta);
    }

    @Test
    public void canCalculateSumBetweenDifferentNumbers() {
        IncomeRange range = new IncomeRange(7d, 19d);
        double expectedSum = 7d + 19d;

        double result = range.calculateSum();

        assertEquals(expectedSum, result, delta);
    }

    @Test
    public void canCalculateSumBetweenEqualNumbers() {
        IncomeRange range = new IncomeRange(47d, 47d);
        double expectedSum = 47d + 47d;

        double result = range.calculateSum();

        assertEquals(expectedSum, result, delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenDifferentNumbers() {
        IncomeRange range = new IncomeRange(9d, 13d);
        double expectedRelativeChangeInMidpoint = (13d - 9d) / ((9d + 13d) / 2d);

        double result = range.calculateRelativeChangeInMidpoint();

        assertEquals(expectedRelativeChangeInMidpoint, result, delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenEqualNumbers() {
        IncomeRange range = new IncomeRange(71d, 71d);
        double expectedRelativeChangeInMidpoint = (71d - 71d) / ((71d + 71d) / 2d);

        double result = range.calculateRelativeChangeInMidpoint();

        assertEquals(expectedRelativeChangeInMidpoint, result, delta);
    }

    @Test(expected = ArithmeticException.class)
    public void calculateRelativeChangeThrowsExceptionOnInitialValues() {
        IncomeRange range = new IncomeRange();

        range.calculateRelativeChangeInMidpoint();
    }

    @Test
    public void isZeroLengthRangeReturnsTrueOnEqualNumbers() {
        IncomeRange range = new IncomeRange(21d, 21d);

        assertTrue(range.isZeroLengthRange());
    }

    @Test
    public void isZeroLengthRangeReturnsFalseOnDifferentNumbers() {
        IncomeRange range = new IncomeRange(27d, 12d);

        assertFalse(range.isZeroLengthRange());
    }
}
