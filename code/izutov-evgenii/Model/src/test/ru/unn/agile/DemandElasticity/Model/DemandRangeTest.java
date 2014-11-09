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
        DemandRange range = new DemandRange(12d, 25d);

        assertNotNull(range);
    }

    @Test
    public void canSetInitialStartValue() {
        DemandRange range = new DemandRange(11d, 23d);

        assertEquals(11d, range.getStartValue(), delta);
    }

    @Test
    public void canSetInitialEndValue() {
        DemandRange range = new DemandRange(1d, 22d);

        assertEquals(22d, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void demandRangeThrowsExceptionOnNegativeStartValue() {
        new DemandRange(-10d, 20d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void demandRangeThrowsExceptionOnNegativeEndValue() {
        new DemandRange(7d, -11d);
    }

    @Test
    public void canSetArbitraryStartValue() {
        DemandRange range = new DemandRange();

        range.setStartValue(1d);

        assertEquals(1d, range.getStartValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setStartValueThrowsExceptionOnNegativeValue() {
        DemandRange range = new DemandRange();

        range.setStartValue(-1d);
    }

    @Test
    public void canSetArbitraryEndValue() {
        DemandRange range = new DemandRange();

        range.setEndValue(2d);

        assertEquals(2d, range.getEndValue(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEndValueThrowsExceptionOnNegativeValue() {
        DemandRange range = new DemandRange();

        range.setEndValue(-1d);
    }

    @Test
    public void canCalculateDifferenceBetweenDifferentNumbers() {
        DemandRange range = new DemandRange(17d, 23d);
        double expectedDifference = 23d - 17d;

        double result = range.calculateDifference();

        assertEquals(expectedDifference, result, delta);
    }

    @Test
    public void canCalculateDifferenceBetweenEqualNumbers() {
        DemandRange range = new DemandRange(21d, 21d);
        double expectedDifference = 21d - 21d;

        double result = range.calculateDifference();

        assertEquals(expectedDifference, result, delta);
    }

    @Test
    public void canCalculateSumBetweenDifferentNumbers() {
        DemandRange range = new DemandRange(4d, 19d);
        double expectedSum = 4d + 19d;

        double result = range.calculateSum();

        assertEquals(expectedSum, result, delta);
    }

    @Test
    public void canCalculateSumBetweenEqualNumbers() {
        DemandRange range = new DemandRange(13d, 13d);
        double expectedSum = 13d + 13d;

        double result = range.calculateSum();

        assertEquals(expectedSum, result, delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenDifferentNumbers() {
        DemandRange range = new DemandRange(13d, 8d);
        double expectedRelativeChangeInMidpoint = (8d - 13d) / ((13d + 8d) / 2d);

        double result = range.calculateRelativeChangeInMidpoint();

        assertEquals(expectedRelativeChangeInMidpoint, result, delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenEqualNumbers() {
        DemandRange range = new DemandRange(27d, 27d);
        double expectedRelativeChangeInMidpoint = (27d - 27d) / ((27d + 27d) / 2d);

        double result = range.calculateRelativeChangeInMidpoint();

        assertEquals(expectedRelativeChangeInMidpoint, result, delta);
    }

    @Test(expected = ArithmeticException.class)
    public void calculateRelativeChangeThrowsExceptionOnInitialValues() {
        DemandRange range = new DemandRange();

        range.calculateRelativeChangeInMidpoint();
    }

    @Test
    public void isZeroLengthRangeReturnsTrueOnEqualNumbers() {
        DemandRange range = new DemandRange(19d, 19d);

        assertTrue(range.isZeroLengthRange());
    }

    @Test
    public void isZeroLengthRangeReturnsFalseOnDifferentNumbers() {
        DemandRange range = new DemandRange(35d, 17d);

        assertFalse(range.isZeroLengthRange());
    }
}
