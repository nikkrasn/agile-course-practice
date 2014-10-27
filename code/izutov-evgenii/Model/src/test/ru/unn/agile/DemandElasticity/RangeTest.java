package ru.unn.agile.DemandElasticity;

import org.junit.Test;
import static org.junit.Assert.*;

public class RangeTest {
    private final double delta = 0.001;

    @Test
    public void canCreateRangeWithDefaultValues() {
        Range range = new Range();
        assertNotNull(range);
    }

    @Test
    public void canCreateRangeWithInitialValues() {
        Range range = new Range(1f, 2f);
        assertNotNull(range);
    }

    @Test
    public void canSetInitialStartValue() {
        Range range = new Range(1f, 2f);
        assertEquals(1f, range.getStartValue(), delta);
    }

    @Test
    public void canSetInitialEndValue() {
        Range range = new Range(1f, 2f);
        assertEquals(2f, range.getEndValue(), delta);
    }

    @Test
    public void canSetArbitraryStartValue() {
        Range range = new Range();
        range.setStartValue(1f);
        assertEquals(1f, range.getStartValue(), delta);
    }

    @Test
    public void canSetArbitraryEndValue() {
        Range range = new Range();
        range.setEndValue(2f);
        assertEquals(2f, range.getEndValue(), delta);
    }

    @Test
    public void canCalculateDifferenceBetweenDifferentNumbers() {
        Range range = new Range(1f, 2f);
        assertEquals(1f, range.calculateDifference(), delta);
    }

    @Test
    public void canCalculateDifferenceBetweenEqualNumbers() {
        Range range = new Range(2f, 2f);
        assertEquals(0f, range.calculateDifference(), delta);
    }

    @Test
    public void canCalculateSumBetweenDifferentNumbers() {
        Range range = new Range(0f, 1f);
        assertEquals(1f, range.calculateSum(), delta);
    }

    @Test
    public void canCalculateSumBetweenEqualNumbers() {
        Range range = new Range(1f, 1f);
        assertEquals(2f, range.calculateSum(), delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenDifferentNumbers() {
        Range range = new Range(0f, 1f);
        assertEquals(0.5f, range.calculateRelativeChangeInMidpoint(), delta);
    }

    @Test
    public void canCalculateRelativeChangeBetweenEqualNumbers() {
        Range range = new Range(1f, 1f);
        assertEquals(0f, range.calculateRelativeChangeInMidpoint(), delta);
    }
}
