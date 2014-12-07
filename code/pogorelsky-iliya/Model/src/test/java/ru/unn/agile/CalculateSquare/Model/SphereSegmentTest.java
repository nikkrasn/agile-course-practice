package ru.unn.agile.CalculateSquare.Model;

import static org.junit.Assert.*;
import org.junit.Test;


public class SphereSegmentTest {
    private static final double DELTA = 0.001;

    @Test
    public void canInitializationRadiusWithObtainedIntegerValues() {
        SphereSegment figure = new SphereSegment(1, 1);
        assertNotNull(figure);
    }

    @Test
    public void canInitializationRadiusWithObtainedRealValues() {
        SphereSegment figure = new SphereSegment(1.0, 1.0);
        assertNotNull(figure);
    }

    @Test
    public void isCorrectCalculationSphereSegmentSquareWithIntegerValue() {
        SphereSegment figure = new SphereSegment(1, 1);
        assertEquals(2 * Math.PI, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationSphereSegmentSquareWithRealValues() {
        SphereSegment figure = new SphereSegment(1.0, 1.0);
        assertEquals(2 * Math.PI, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationSphereSegmentWithZeroValue() {
        SphereSegment figure = new SphereSegment(0, 0);
        assertEquals(0, figure.calculateSquare(), DELTA);
    }
}

