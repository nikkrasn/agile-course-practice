package ru.unn.agile.CalculateSquare.Model;

import static org.junit.Assert.*;

import org.junit.Test;


public class CylinderTest {

    private static final double DELTA = 0.001;

    @Test
    public void canInitializationRadiusAndHeightWithValues() {
        Cylinder figure = new Cylinder(1, 1.0);
        assertNotNull(figure);
    }

    @Test
    public void canInitializationRadiusAndHeightWithObtainedIntegerValues() {
        Cylinder figure = new Cylinder(1, 1);
        assertNotNull(figure);
    }

    @Test
    public void canInitializationRadiusAndHeightWithObtainedRealValues() {
        Cylinder figure = new Cylinder(1.0, 1.0);
        assertNotNull(figure);
    }

    @Test
    public void isCorrectCalculationCylinderSquareWithIntegerValue() {
        Cylinder figure = new Cylinder(1, 1);
        assertEquals(4 * Math.PI, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationCylinderSquareWithRealValues() {
        Cylinder figure = new Cylinder(1.0, 1.0);
        assertEquals(4 * Math.PI, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationCylinderSquareWithZeroValue() {
        Cylinder figure = new Cylinder(0, 0);
        assertEquals(0, figure.calculateSquare(), DELTA);
    }
}
