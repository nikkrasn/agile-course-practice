package ru.unn.agile.DemandElasticity.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class DemandRangeTest {
    private final double delta = 0.000001;

    @Test
    public void canCreateDemandRangeWithDefaultValues() {
        DemandRange range = new DemandRange();
        assertNotNull(range);
    }

    @Test
    public void canCreateDemandRangeWithInitialValues() {
        DemandRange range = new DemandRange(12, 25);
        assertNotNull(range);
    }

    @Test
    public void canSetInitialStartValue() {
        DemandRange range = new DemandRange(11, 23);
        assertEquals(11, range.getStartValue(), delta);
    }

    @Test
    public void canSetInitialEndValue() {
        DemandRange range = new DemandRange(1, 22);
        assertEquals(22, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void demandRangeThrowsExceptionOnNegativeStartValue() {
        new DemandRange(-10, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void demandRangeThrowsExceptionOnNegativeEndValue() {
        new DemandRange(7, -11);
    }

    @Test
    public void canSetArbitraryStartValue() {
        DemandRange range = new DemandRange();
        range.setStartValue(1);
        assertEquals(1, range.getStartValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setStartValueThrowsExceptionOnNegativeValue() {
        DemandRange range = new DemandRange();
        range.setStartValue(-1);
    }

    @Test
    public void canSetArbitraryEndValue() {
        DemandRange range = new DemandRange();
        range.setEndValue(2);
        assertEquals(2, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEndValueThrowsExceptionOnNegativeValue() {
        DemandRange range = new DemandRange();
        range.setEndValue(-1);
    }

    @Test
    public void canCalculateDifferenceBetweenDifferentNumbers() {
        DemandRange range = new DemandRange(17, 23);
        assertEquals(6, range.calculateDifference(), delta);
    }

    @Test
    public void canCalculateDifferenceBetweenEqualNumbers() {
        DemandRange range = new DemandRange(21, 21);
        assertEquals(0, range.calculateDifference(), delta);
    }

    @Test
    public void canCalculateSumBetweenDifferentNumbers() {
        DemandRange range = new DemandRange(4, 19);
        assertEquals(23, range.calculateSum(), delta);
    }

    @Test
    public void canCalculateSumBetweenEqualNumbers() {
        DemandRange range = new DemandRange(13, 13);
        assertEquals(26, range.calculateSum(), delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenDifferentNumbers() {
        DemandRange range = new DemandRange(13, 8);
        assertEquals(-0.47619047619, range.calculateRelativeChangeInMidpoint(), delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenEqualNumbers() {
        DemandRange range = new DemandRange(27, 27);
        assertEquals(0, range.calculateRelativeChangeInMidpoint(), delta);
    }

    @Test(expected = ArithmeticException.class)
    public void calculateRelativeChangeThrowsExceptionOnInitialValues() {
        DemandRange range = new DemandRange();
        range.calculateRelativeChangeInMidpoint();
    }

    @Test
    public void isZeroLengthRangeReturnsTrueOnEqualNumbers() {
        DemandRange range = new DemandRange(19, 19);
        assertTrue(range.isZeroLengthRange());
    }

    @Test
    public void isZeroLengthRangeReturnsFalseOnDifferentNumbers() {
        DemandRange range = new DemandRange(35, 17);
        assertFalse(range.isZeroLengthRange());
    }
}
