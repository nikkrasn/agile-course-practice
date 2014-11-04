package ru.unn.agile.CalculateSquare.Model;

import static org.junit.Assert.*;
import org.junit.Test;

public class ConeTest {

    private static final double DELTA = 0.001;

    @Test
    public void canInitializationRadiusAndHeightWithValues() {
        Cone figure = new Cone(1, 1.0);
        assertNotNull(figure);
    }

    @Test
    public void canInitializationRadiusAndHeightWithObtainedIntegerValues() {
        Cone figure = new Cone(1, 1);
        assertNotNull(figure);
    }

    @Test
    public void canInitializationRadiusAndHeightWithObtainedRealValues() {
        Cone figure = new Cone(1.0, 1.0);
        assertNotNull(figure);
    }

    @Test
    public void isCorrectCalculationConeSquareWithIntegerValues() {
        Cone figure = new Cone(1, 0);
        assertEquals(2 * Math.PI, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationConeSquareWithRealValues() {
        Cone figure = new Cone(1.0, 0);
        assertEquals(2 * Math.PI, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationConeSquareWithZeroValues() {
        Cone figure = new Cone(0, 0);
        assertEquals(0, figure.calculateSquare(), DELTA);
    }
}
