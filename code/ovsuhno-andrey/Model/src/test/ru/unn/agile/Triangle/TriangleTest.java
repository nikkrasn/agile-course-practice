package ru.unn.agile.Triangle;

import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleTest {
    private final double accurateDelta = 0;
    private final double roughDelta = 0.001;

    @Test
    public void canCreateTriangle() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(3, 4);
        Point pointC = new Point(2, 1);
        Triangle newTriangle = new Triangle(pointA, pointB, pointC);
        assertNotNull(newTriangle);
    }

    @Test
    public void areSamePointsFormingTriangle() {
        Point pointA = new Point(1, 1);
        Point pointB = new Point(1, 1);
        Point pointC = new Point(1, 1);
        assertFalse(Triangle.isTriangle(pointA, pointB, pointC));
    }

    @Test
    public void areNotDifferentPointsFormingTriangle() {
        Point pointA = new Point(1, 1);
        Point pointB = new Point(1, 1);
        Point pointC = new Point(2, 2);
        assertFalse(Triangle.isTriangle(pointA, pointB, pointC));
    }

    @Test
    public void arePointsOnStraightLineFormingTriangle() {
        Point pointA = new Point(1, 1);
        Point pointB = new Point(1, 3);
        Point pointC = new Point(1, 2);
        assertFalse(Triangle.isTriangle(pointA, pointB, pointC));
    }

    @Test
    public void arePointsNotOnStraightLineFormingTriangle() {
        Point pointA = new Point(1, 1);
        Point pointB = new Point(2, 2);
        Point pointC = new Point(2, 1);
        assertTrue(Triangle.isTriangle(pointA, pointB, pointC));
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenMatchingPoints() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(1, 2);
        Point pointC = new Point(5, 6);
        Triangle newTriangle = new Triangle(pointA, pointB, pointC);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenPointsOnStraightLine() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(1, 3);
        Point pointC = new Point(1, 4);
        Triangle newTriangle = new Triangle(pointA, pointB, pointC);
    }

    @Test
    public void canSetInitialAPoint() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(3, 4);
        Point pointC = new Point(2, 1);
        Triangle newTriangle = new Triangle(pointA, pointB, pointC);
        Point correctA = new Point(1, 2);
        assertEquals(correctA, newTriangle.getA());
    }

    @Test
    public void canSetInitialBPoint() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(3, 4);
        Point pointC = new Point(2, 1);
        Triangle newTriangle = new Triangle(pointA, pointB, pointC);
        Point correctB = new Point(3, 4);
        assertEquals(correctB, newTriangle.getB());
    }

    @Test
    public void canSetInitialCPoint() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(3, 4);
        Point pointC = new Point(2, 1);
        Triangle newTriangle = new Triangle(pointA, pointB, pointC);
        Point correctC = new Point(2, 1);
        assertEquals(correctC, newTriangle.getC());
    }

    @Test (expected = IllegalArgumentException.class)
     public void throwsWhenSetPointAInOtherPoint() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(1, 4);
        Point pointC = new Point(2, 2);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        certainTriangle.setA(1, 4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenSetPointBInOtherPoint() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(1, 4);
        Point pointC = new Point(2, 2);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        certainTriangle.setB(2, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenSetPointCInOtherPoint() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(1, 4);
        Point pointC = new Point(2, 2);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        certainTriangle.setC(1, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenSetPointAOnLineOfOtherPoints() {
        Point pointA = new Point(1, 1);
        Point pointB = new Point(3, 5);
        Point pointC = new Point(3, 1);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        certainTriangle.setA(3, 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenSetPointBOnLineOfOtherPoints() {
        Point pointA = new Point(1, 1);
        Point pointB = new Point(3, 5);
        Point pointC = new Point(3, 1);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        certainTriangle.setB(2, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenSetPointCOnLineOfOtherPoints() {
        Point pointA = new Point(1, 1);
        Point pointB = new Point(3, 5);
        Point pointC = new Point(3, 1);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        certainTriangle.setC(2, 3);
    }

    @Test
     public void canCountLengthsOfRectangularTriangle() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(1, 4);
        Point pointC = new Point(2, 2);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        assertArrayEquals(new double[]{2, 2.2361, 1}, certainTriangle.countLengths(), roughDelta);
    }

    @Test
    public void canCountLengthsOfRandomTriangle() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(2, 4);
        Point pointC = new Point(5, 3);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        double[] correctLengths = {2.2361, 3.1623, 4.1231};
        assertArrayEquals(correctLengths, certainTriangle.countLengths(), roughDelta);
    }

    @Test
    public void canCountPerimeterOfRectangularTriangle() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(1, 4);
        Point pointC = new Point(2, 2);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        assertEquals(5.2361, certainTriangle.countPerimeter(), roughDelta);
    }

    @Test
    public void canCountPerimeterOfRandomTriangle() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(2, 4);
        Point pointC = new Point(5, 3);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        assertEquals(9.5215, certainTriangle.countPerimeter(), roughDelta);
    }

    @Test
    public void canCountSpaceOfRectangularTriangle() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(1, 4);
        Point pointC = new Point(2, 2);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        assertEquals(1.0, certainTriangle.countSpace(), accurateDelta);
    }

    @Test
    public void canCountSpaceOfRandomTriangle() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(2, 4);
        Point pointC = new Point(5, 3);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        assertEquals(3.5, certainTriangle.countSpace(), roughDelta);
    }

    @Test
    public void canCountAnglesCosineOfRectangularTriangle() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(1, 4);
        Point pointC = new Point(2, 2);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        double[] correctAngles = {0.0, 0.8944, 0.4472};
        assertArrayEquals(correctAngles, certainTriangle.countAnglesCosine(), roughDelta);
    }

    @Test
    public void canCountAnglesCosineOfRandomTriangle() {
        Point pointA = new Point(1, 2);
        Point pointB = new Point(2, 4);
        Point pointC = new Point(5, 3);
        Triangle certainTriangle = new Triangle(pointA, pointB, pointC);
        double[] correctAngles = {0.6508, -0.1414, 0.8437};
        assertArrayEquals(correctAngles, certainTriangle.countAnglesCosine(), roughDelta);
    }
}
