package ru.unn.agile.CalculateSquare.Model;

import static org.junit.Assert.*;
import org.junit.Test;


public class SphereSectorTest {
    private static final double DELTA = 0.001;

    @Test
    public void canInitializationRadiusWithObtainedIntegerValues() {
        SphereSector figure = new SphereSector(1, 1);
        assertNotNull(figure);
    }

    @Test
    public void canInitializationRadiusWithObtainedRealValues() {
        SphereSector figure = new SphereSector(1.0, 1.0);
        assertNotNull(figure);
    }

    @Test
    public void isCorrectCalculationSphereSegmentSquareWithIntegerValue() {
        SphereSector figure = new SphereSector(4, 3);
        assertEquals(4 * Math.PI, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationSphereSegmentSquareWithRealValues() {
        SphereSector figure = new SphereSector(4, 3);
        assertEquals(4 * Math.PI, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationSphereSegmentWithZeroValue() {
        SphereSector figure = new SphereSector(0, 0);
        assertEquals(0, figure.calculateSquare(), DELTA);
    }
}
