package ru.unn.agile.CalculateSquare.Model;

import static org.junit.Assert.*;
import org.junit.Test;


public class CubeTest {

    private static final double DELTA = 0.001;

    @Test
    public void canInitializationEdgeWithObtainedIntegerValue() {
        Cube figure = new Cube(1);
        assertNotNull(figure);
    }

    @Test
    public void canInitializationEdgeWithObtainedRealValues() {
        Cube figure = new Cube(1.0);
        assertNotNull(figure);
    }

    @Test
    public void isCorrectCalculationCubeSquareWithIntegerValues() {
        Cube figure = new Cube(1);
        assertEquals(6, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationCubeSquareWithRealValues() {
        Cube figure = new Cube(1.0);
        assertEquals(6, figure.calculateSquare(), DELTA);
    }

    @Test
    public void isCorrectCalculationCubeSquareWithZeroValues() {
        Cube figure = new Cube(0);
        assertEquals(0, figure.calculateSquare(), DELTA);
    }
}
