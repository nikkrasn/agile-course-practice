package ru.unn.agile.CalculateSquare.Model;

import static org.junit.Assert.*;
import org.junit.Test;


public class SphereTest {

    private static final double DELTA = 0.001;

    @Test
    public void canInitializationRadiusWithObtainedIntegerValues() {
        Sphere figure = new Sphere(1);
        assertNotNull(figure);
    }

    @Test
    public void canInitializationRadiusWithObtainedRealValues() {
        Sphere figure = new Sphere(1.0);
        assertNotNull(figure);
    }

    @Test
    public void isCorrectCalculationSphereSquareWithIntegerValue() {
        Sphere figure = new Sphere(1);
        assertEquals(4 * Math.PI, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationSphereSquareWithRealValues() {
        Sphere figure = new Sphere(1.0);
        assertEquals(4 * Math.PI, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationSphereSquareWithZeroValue() {
        Sphere figure = new Sphere(0);
        assertEquals(0, figure.calculateSquare(), DELTA);
    }
}
