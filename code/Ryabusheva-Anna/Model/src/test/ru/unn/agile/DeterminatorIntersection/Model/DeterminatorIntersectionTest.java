package ru.unn.agile.DeterminatorIntersection.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeterminatorIntersectionTest {

    @Test
    public void canCreateDeterminator() {
        Point3D lineVector = new Point3D(2, 4, 1);
        Point3D linePoint = new Point3D(3, 3, 1);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(2, 5, 6, 1);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertNotNull(determ);
    }

    @Test
    public void canSetInitialLine() {
        Point3D lineVector = new Point3D(1, 2, 3);
        Point3D linePoint = new Point3D(4, 5, 6);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(line.equals(determ.getLine()));
    }

    @Test
    public void canSetInitialPlane() {
        Point3D lineVector = new Point3D(11, 12, 23);
        Point3D linePoint = new Point3D(41, 245, 46);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(50, 40, 60, 70);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(plane.equals(determ.getPlane()));
    }

    @Test
    public void isCorrectDeterminationWithIncorrectLineVector() {
        Point3D lineVector = new Point3D(0, 0, 0);
        Point3D linePoint = new Point3D(3, 3, 3);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertFalse(determ.isIntersection());
    }

    @Test
    public void isCorrectDeterminationWithCorrectLineVector() {
        Point3D lineVector = new Point3D(5, 1, 0);
        Point3D linePoint = new Point3D(1, 2, 10);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(determ.isIntersection());
    }

    @Test
    public void isCorrectDeterminationWithCorrectPlane() {
        Point3D lineVector = new Point3D(4, 0, 0);
        Point3D linePoint = new Point3D(0, 3, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(determ.isIntersection());
    }

    @Test
    public void isCorrectDeterminationWithNotCorrectPlane() {
        Point3D lineVector = new Point3D(1, 0, 0);
        Point3D linePoint = new Point3D(0, 0, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 1);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertFalse(determ.isIntersection());
    }

    @Test
    public void isCorrectDeterminationWithNullPlane() {
        Point3D lineVector = new Point3D(1, 0, 7);
        Point3D linePoint = new Point3D(7, 7, 7);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(determ.isIntersection());
    }

    @Test
    public void isCorrectDeterminationWithNoIntersection() {
        Point3D lineVector = new Point3D(1, 1, 0);
        Point3D linePoint = new Point3D(3, 5, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(-1, 1, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertFalse(determ.isIntersection());
    }

    @Test
    public void isCorrectDeterminationWithLineBelongPlane() {
        Point3D lineVector = new Point3D(1, 1, 0);
        Point3D linePoint = new Point3D(1, 1, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(-1, 1, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(determ.isIntersection());
    }

    @Test
    public void isCorrectDeterminationWithIntersectionInOnePoint() {
        Point3D lineVector = new Point3D(1, 1, 0);
        Point3D linePoint = new Point3D(1, 1, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(1, 1, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(determ.isIntersection());
    }
}
