package ru.unn.agile.DemandElasticity;

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
        IncomeRange range = new IncomeRange(12, 25);
        assertNotNull(range);
    }

    @Test
    public void canSetInitialStartValue() {
        IncomeRange range = new IncomeRange(11, 23);
        assertEquals(11, range.getStartValue(), delta);
    }

    @Test
    public void canSetInitialEndValue() {
        IncomeRange range = new IncomeRange(1, 22);
        assertEquals(22, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incomeRangeThrowsExceptionOnNegativeStartValue() {
        new IncomeRange(-10, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incomeRangeThrowsExceptionOnNegativeEndValue() {
        new IncomeRange(7, -11);
    }

    @Test
    public void canSetArbitraryStartValue() {
        IncomeRange range = new IncomeRange();
        range.setStartValue(1);
        assertEquals(1, range.getStartValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setStartValueThrowsExceptionOnNegativeValue() {
        IncomeRange range = new IncomeRange();
        range.setStartValue(-1);
    }

    @Test
    public void canSetArbitraryEndValue() {
        IncomeRange range = new IncomeRange();
        range.setEndValue(2);
        assertEquals(2, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEndValueThrowsExceptionOnNegativeValue() {
        IncomeRange range = new IncomeRange();
        range.setEndValue(-1);
    }

    @Test
    public void canCalculateDifferenceBetweenDifferentNumbers() {
        IncomeRange range = new IncomeRange(17, 23);
        assertEquals(6, range.calculateDifference(), delta);
    }

    @Test
    public void canCalculateDifferenceBetweenEqualNumbers() {
        IncomeRange range = new IncomeRange(21, 21);
        assertEquals(0, range.calculateDifference(), delta);
    }

    @Test
    public void canCalculateSumBetweenDifferentNumbers() {
        IncomeRange range = new IncomeRange(4, 19);
        assertEquals(23, range.calculateSum(), delta);
    }

    @Test
    public void canCalculateSumBetweenEqualNumbers() {
        IncomeRange range = new IncomeRange(13, 13);
        assertEquals(26, range.calculateSum(), delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenDifferentNumbers() {
        IncomeRange range = new IncomeRange(6, 10);
        assertEquals(0.5, range.calculateRelativeChangeInMidpoint(), delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenEqualNumbers() {
        IncomeRange range = new IncomeRange(7, 7);
        assertEquals(0, range.calculateRelativeChangeInMidpoint(), delta);
    }

    @Test(expected = ArithmeticException.class)
    public void calculateRelativeChangeThrowsExceptionOnInitialValues() {
        IncomeRange range = new IncomeRange();
        range.calculateRelativeChangeInMidpoint();
    }

    @Test
    public void isZeroLengthRangeReturnsTrueOnEqualNumbers() {
        IncomeRange range = new IncomeRange(21, 21);
        assertTrue(range.isZeroLengthRange());
    }

    @Test
    public void isZeroLengthRangeReturnsFalseOnDifferentNumbers() {
        IncomeRange range = new IncomeRange(27, 12);
        assertFalse(range.isZeroLengthRange());
    }
}
