package ru.unn.agile.DeterminatorIntersection.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeterminatorIntersectionTest {

    @Test
    public void canCreateDeterminator() {
        Point3D lineVector = new Point3D(0, 0, 0);
        Point3D linePoint = new Point3D(0, 0, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 0);
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
        Point3D lineVector = new Point3D(1, 2, 3);
        Point3D linePoint = new Point3D(4, 5, 6);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(plane.equals(determ.getPlane()));
    }

    @Test
    public void IsCorrectDeterminationWithIncorrectLineVector() {
        Point3D lineVector = new Point3D(0, 0, 0);
        Point3D linePoint = new Point3D(0, 0, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertFalse(determ.isIntersection());
    }

    @Test
    public void IsCorrectDeterminationWithCorrectLineVector() {
        Point3D lineVector = new Point3D(1, 0, 0);
        Point3D linePoint = new Point3D(0, 0, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(determ.isIntersection());
    }

    @Test
    public void IsCorrectDeterminationWithCorrectPlane() {
        Point3D lineVector = new Point3D(1, 0, 0);
        Point3D linePoint = new Point3D(0, 0, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(determ.isIntersection());
    }

    @Test
    public void IsCorrectDeterminationWithNotCorrectPlane() {
        Point3D lineVector = new Point3D(1, 0, 0);
        Point3D linePoint = new Point3D(0, 0, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 1);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertFalse(determ.isIntersection());
    }

    @Test
    public void IsCorrectDeterminationWithNullPlane() {
        Point3D lineVector = new Point3D(1, 0, 0);
        Point3D linePoint = new Point3D(0, 0, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(0, 0, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(determ.isIntersection());
    }

    @Test
    public void IsCorrectDeterminationWithNoIntersection() {
        Point3D lineVector = new Point3D(1, 1, 0);
        Point3D linePoint = new Point3D(3, 5, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(-1, 1, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertFalse(determ.isIntersection());
    }

    @Test
    public void IsCorrectDeterminationWithLineBelongPlane() {
        Point3D lineVector = new Point3D(1, 1, 0);
        Point3D linePoint = new Point3D(1, 1, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(-1, 1, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(determ.isIntersection());
    }

    @Test
    public void IsCorrectDeterminationWithIntersectionInOnePoint() {
        Point3D lineVector = new Point3D(1, 1, 0);
        Point3D linePoint = new Point3D(1, 1, 0);
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(1, 1, 0, 0);
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        assertTrue(determ.isIntersection());
    }
}
