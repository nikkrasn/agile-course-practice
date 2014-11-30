package ru.unn.agile.Triangle.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {
    private final double accurateDelta = 0;
    private final double roughDelta = 0.001;

    @Test
    public void canCreatePoint() {
        Point newPoint = new Point(0, 0);

        assertNotNull(newPoint);
    }

    @Test
    public void canSetInitialXCoordinate() {
        Point newPoint = new Point(2, 4);

        assertEquals(2.0, newPoint.getX(), accurateDelta);
    }

    @Test
    public void canSetInitialCoordinate() {
        Point newPoint = new Point(2, 4);

        assertEquals(4.0, newPoint.getY(), accurateDelta);
    }

    @Test
    public void areEqualPointsEqual() {
        Point certainPoint = new Point(2, 4);
        Point equalPoint = new Point(2, 4);

        assertTrue(certainPoint.equals(equalPoint));
    }

    @Test
    public void isPointsEqualToNull() {
        Point certainPoint = new Point(2, 4);

        assertFalse(certainPoint.equals(null));
    }

    @Test
    public void isPointsEqualToNotPointObject() {
        Point certainPoint = new Point(2, 4);
        int number = 1;

        assertFalse(certainPoint.equals(number));
    }

    @Test
    public void arePointsWithDifferentXCooridinateNotEqual() {
        Point certainPoint = new Point(2, 4);
        Point anotherPoint = new Point(4, 4);

        assertFalse(certainPoint.equals(anotherPoint));
    }

    @Test
    public void arePointsWithDifferentYCooridinateNotEqual() {
        Point certainPoint = new Point(2, 4);
        Point anotherPoint = new Point(2, 5);

        assertFalse(certainPoint.equals(anotherPoint));
    }

    @Test
    public void canGetDistanceBetweenSamePoints() {
        Point certainPoint = new Point(2, 4);

        double distance = certainPoint.getDistance(certainPoint);

        assertEquals(0.0, distance, accurateDelta);
    }

    @Test
    public void canGetDistanceBetweenPointsWithCoinsidingXCoordinate() {
        Point pointA = new Point(2, 4);
        Point pointB = new Point(2, 5);

        double distance = pointA.getDistance(pointB);

        assertEquals(1.0, distance, accurateDelta);
    }

    @Test
    public void canGetDistanceBetweenPointsWithCoinsidingYCoordinate() {
        Point pointA = new Point(2, 4);
        Point pointB = new Point(4, 4);

        double distance = pointA.getDistance(pointB);

        assertEquals(2.0, distance, accurateDelta);
    }

    @Test
    public void canGetDistanceBetweenPointsWithDifferentCoordinates() {
        Point pointA = new Point(2, 4);
        Point pointB = new Point(3, 5);

        double distance = pointA.getDistance(pointB);

        assertEquals(1.4142, distance, roughDelta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenSearchWhenLineIsIndefinite() {
        Point pointA = new Point(2, 4);
        Point pointB = new Point(1, 1);

        pointB.isOnStraightLine(pointA, pointA);
    }

    @Test
    public void isPointOnLineOfPointsOnLine() {
        Point pointA = new Point(1, 1);
        Point pointB = new Point(3, 5);
        Point pointC = new Point(2.5, 4);

        assertTrue(pointC.isOnStraightLine(pointA, pointB));
    }

    @Test
    public void isPointNotOnLineOfPointsNotOnLine() {
        Point pointA = new Point(1, 1);
        Point pointB = new Point(3, 5);
        Point pointC = new Point(2, 4);

        assertFalse(pointC.isOnStraightLine(pointA, pointB));
    }

    @Test
    public void canConvertStringToPoint() {
        Point newPoint = new Point("1", "2");

        assertEquals(new Point(1, 2), newPoint);
    }
}
