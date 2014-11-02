package ru.unn.agile.Triangle;

import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleTest {
    private final double accurateDelta = 0;
    private final double roughDelta = 0.001;

    @Test
    public void canCreateTriangle() {
        Triangle newTriangle = new Triangle(1, 2, 3, 4, 2, 1);
        assertNotNull(newTriangle);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenMatchingPoints() {
        Triangle newTriangle = new Triangle(1, 2, 1, 2, 5, 6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenPointsOnStraightLine() {
        Triangle newTriangle = new Triangle(1, 2, 1, 3, 1, 4);
    }

    @Test
    public void canSetInitialAPoint() {
        Triangle newTriangle = new Triangle(1, 2, 3, 4, 2, 1);
        Point correctA = new Point(1, 2);
        assertEquals(correctA, newTriangle.getA());
    }

    @Test
    public void canSetInitialBPoint() {
        Triangle newTriangle = new Triangle(1, 2, 3, 4, 2, 1);
        Point correctB = new Point(3, 4);
        assertEquals(correctB, newTriangle.getB());
    }

    @Test
    public void canSetInitialCPoint() {
        Triangle newTriangle = new Triangle(1, 2, 3, 4, 2, 1);
        Point correctC = new Point(2, 1);
        assertEquals(correctC, newTriangle.getC());
    }

    @Test
     public void canCountLengthsOfRectangularTriangle() {
        Triangle certainTriangle = new Triangle(1, 2, 1, 4, 2, 2);
        assertArrayEquals(new double[]{2, 2.2361, 1}, certainTriangle.countLengths(), roughDelta);
    }

    @Test
    public void canCountLengthsOfRandomTriangle() {
        Triangle certainTriangle = new Triangle(1, 2, 2, 4, 5, 3);
        assertArrayEquals(new double[]{2.2361, 3.1623, 4.1231}, certainTriangle.countLengths(), roughDelta);
    }

    @Test
    public void canCountPerimeterOfRectangularTriangle() {
        Triangle certainTriangle = new Triangle(1, 2, 1, 4, 2, 2);
        assertEquals(5.2361, certainTriangle.countPerimeter(), roughDelta);
    }

    @Test
    public void canCountPerimeterOfRandomTriangle() {
        Triangle certainTriangle = new Triangle(1, 2, 2, 4, 5, 3);
        assertEquals(9.5215, certainTriangle.countPerimeter(), roughDelta);
    }

    @Test
    public void canCountSpaceOfRectangularTriangle() {
        Triangle certainTriangle = new Triangle(1, 2, 1, 4, 2, 2);
        assertEquals(1.0, certainTriangle.countSpace(), accurateDelta);
    }

    @Test
    public void canCountSpaceOfRandomTriangle() {
        Triangle certainTriangle = new Triangle(1, 2, 2, 4, 5, 3);
        assertEquals(3.5, certainTriangle.countSpace(), roughDelta);
    }

    @Test
    public void canCountAnglesCosineOfRectangularTriangle() {
        Triangle certainTriangle = new Triangle(1, 2, 1, 4, 2, 2);
        assertArrayEquals(new double[]{0.0, 0.8944, 0.4472}, certainTriangle.countAnglesCosine(), roughDelta);
    }

    @Test
    public void canCountAnglesCosineOfRandomTriangle() {
        Triangle certainTriangle = new Triangle(1, 2, 2, 4, 5, 3);
        assertArrayEquals(new double[]{0.6508, -0.1414, 0.8437}, certainTriangle.countAnglesCosine(), roughDelta);
    }
}