package ru.unn.agile.DeterminatorIntersection.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class Point3DTest {
    private final double delta = 0.001;

    @Test
    public void canCreatePoint3D() {
        Point3D point = new Point3D(1, 1, 1);
        assertNotNull(point);
    }

    @Test
    public void isNullPoint() {
        Point3D point = new Point3D(0, 0, 0);
        assertTrue(point.isNullPoint());
    }

    @Test
    public void isNotNullPoint() {
        Point3D point = new Point3D(1, 0, 0);
        assertFalse(point.isNullPoint());
    }

    @Test
    public void isCorrectScalarMultiplicationWithNullPoints() {
        Point3D point1 = new Point3D(0, 0, 0);
        Point3D point2 = new Point3D(0, 0, 0);
        assertEquals(0, point1.scalarMultiplication(point2), delta);
    }

    @Test
    public void isCorrectScalarMultiplication() {
        Point3D point1 = new Point3D(1, 2, 3);
        Point3D point2 = new Point3D(2, 1, 0);
        assertEquals(4, point1.scalarMultiplication(point2), delta);
    }
}
