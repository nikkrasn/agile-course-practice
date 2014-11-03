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

    @Test (expected = IllegalArgumentException.class)
     public void throwsWhenSetPointAInOtherPoint() {
        Triangle certainTriangle = new Triangle(1, 2, 1, 4, 2, 2);
        certainTriangle.setA(1, 4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenSetPointBInOtherPoint() {
        Triangle certainTriangle = new Triangle(1, 2, 1, 4, 2, 2);
        certainTriangle.setB(2, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenSetPointCInOtherPoint() {
        Triangle certainTriangle = new Triangle(1, 2, 1, 4, 2, 2);
        certainTriangle.setC(1, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenSetPointAOnLineOfOtherPoints() {
        Triangle certainTriangle = new Triangle(1, 2, 1, 4, 2, 2);
        certainTriangle.setA(1.5, 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenSetPointBOnLineOfOtherPoints() {
        Triangle certainTriangle = new Triangle(1, 2, 1, 4, 2, 2);
        certainTriangle.setB(1.5, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenSetPointCOnLineOfOtherPoints() {
        Triangle certainTriangle = new Triangle(1, 2, 1, 4, 2, 2);
        certainTriangle.setC(1, 3);
    }

    @Test
     public void canCountLengthsOfRectangularTriangle() {
        Triangle certainTriangle = new Triangle(1, 2, 1, 4, 2, 2);
        assertArrayEquals(new double[]{2, 2.2361, 1}, certainTriangle.countLengths(), roughDelta);
    }

    @Test
    public void canCountLengthsOfRandomTriangle() {
        Triangle certainTriangle = new Triangle(1, 2, 2, 4, 5, 3);
        double[] correctLengths = {2.2361, 3.1623, 4.1231};
        assertArrayEquals(correctLengths, certainTriangle.countLengths(), roughDelta);
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
        double[] correctAngles = {0.0, 0.8944, 0.4472};
        assertArrayEquals(correctAngles, certainTriangle.countAnglesCosine(), roughDelta);
    }

    @Test
    public void canCountAnglesCosineOfRandomTriangle() {
        Triangle certainTriangle = new Triangle(1, 2, 2, 4, 5, 3);
        double[] correctAngles = {0.6508, -0.1414, 0.8437};
        assertArrayEquals(correctAngles, certainTriangle.countAnglesCosine(), roughDelta);
    }
}
