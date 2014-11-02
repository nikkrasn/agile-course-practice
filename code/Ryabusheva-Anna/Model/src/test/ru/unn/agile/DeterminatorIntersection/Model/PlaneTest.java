package ru.unn.agile.DeterminatorIntersection.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlaneTest {
    private final double delta = 0.001;

    @Test
    public void canCreatePlane() {
        Plane plane = new Plane(0, 0, 0, 0);
        assertNotNull(plane);
    }

    @Test
    public void isCorrectPlane() {
        Plane plane = new Plane(0, 0, 0, 0);
        assertTrue(plane.isCorrect());
    }

    @Test
    public void isNotCorrectPlane() {
        Plane plane = new Plane(0, 0, 0, 1);
        assertFalse(plane.isCorrect());
    }

    @Test
    public void isReturnNotNullNormalVector() {
        Plane plane = new Plane(1, 1, 1, 1);
        assertNotNull(plane.getNormalVector());
    }

    @Test
    public void isPointBelongPlane() {
        Plane plane = new Plane(1, 0, 0, 1);
        Point3D point = new Point3D(1, 0, 0);
        assertTrue(plane.isPointBelongPlane(point));
    }

    @Test
    public void isNotPointBelongPlane() {
        Plane plane = new Plane(1, 0, 0, 1);
        Point3D point = new Point3D(5, 0, 0);
        assertFalse(plane.isPointBelongPlane(point));
    }
}
