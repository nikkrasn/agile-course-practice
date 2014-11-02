package ru.unn.agile.DeterminatorIntersection.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class Line3DTest {

    @Test
    public void canCreateLine3D() {
        Point3D point = new Point3D(2, 2, 2);
        Point3D vector = new Point3D(0, 0, 0);
        Line3D line = new Line3D(point, vector);
        assertNotNull(line);
    }

    @Test
    public void canSetInitialPoint() {
        Point3D point = new Point3D(2, 2, 2);
        Point3D vector = new Point3D(0, 0, 0);
        Line3D line = new Line3D(point, vector);
        assertTrue(point.equals(line.getPoint()));
    }

    @Test
    public void canSetInitialVector() {
        Point3D point = new Point3D(2, 2, 2);
        Point3D vector = new Point3D(1, 1, 2);
        Line3D line = new Line3D(point, vector);
        assertTrue(vector.equals(line.getVector()));
    }

    @Test
    public void isCorrect() {
        Point3D point = new Point3D(2, 2, 2);
        Point3D vector = new Point3D(1, 1, 1);
        Line3D line = new Line3D(point, vector);
        assertTrue(line.isCorrect());
    }

    @Test
    public void isNotCorrectVector() {
        Point3D point = new Point3D(2, 2, 2);
        Point3D vector = new Point3D(0, 0, 0);
        Line3D line = new Line3D(point, vector);
        assertFalse(line.isCorrect());
    }
}
