package ru.unn.agile.Vector3D.model;

import org.junit.Test;
import static org.junit.Assert.*;



public class Vector3DTest {

    private double delta = 1e-10;

    @Test
    public void canGetNormVectorOneCoordinate() {
        Vector3D vector = new Vector3D(1.0, 0.0, 0.0);

        double result = vector.getNorm();

        assertEquals(result, 1.0, delta);
    }

    @Test
    public void canGetNormVectorZeroVector() {
        Vector3D vector = new Vector3D(0.0, 0.0, 0.0);

        double result = vector.getNorm();

        assertEquals(result, 0.0, delta);
    }

    @Test
    public void canGetNormVectorNegativeNumbers() {
        Vector3D vector = new Vector3D(0.0, -3.0, 0.0);

        double result = vector.getNorm();

        assertEquals(result, 3.0, delta);
    }

    @Test
    public void canNormalizeVectorNormOneCoordinate() {
        Vector3D vector = new Vector3D(0.0, 0.0, 1.0);

        vector.normalize();
        double x = vector.getCoordinateX();
        double y = vector.getCoordinateY();
        double z = vector.getCoordinateZ();

        assertArrayEquals(new double[]{0.0, 0.0, 1.0}, new double[]{x, y, z}, delta);
    }

    @Test
    public void canNormalizeVectorNegativeNumbers() {
        Vector3D vector = new Vector3D(-6.0, -8.0, 0.0);

        vector.normalize();
        double x = vector.getCoordinateX();
        double y = vector.getCoordinateY();
        double z = vector.getCoordinateZ();

        assertArrayEquals(new double[]{-0.6, -0.8, 0.}, new double[]{x, y, z}, delta);
    }

    @Test
    public void canNormalizeVectorZeroVector() {
        Vector3D vector = new Vector3D(0.0, 0.0, 0.0);

        vector.normalize();
        double x = vector.getCoordinateX();
        double y = vector.getCoordinateY();
        double z = vector.getCoordinateZ();

        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, new double[]{x, y, z}, delta);
    }

    @Test
    public void canDotProductVectorsOneCoordinate() {
        Vector3D vector1 = new Vector3D(1.0, 0.0, 0.0);
        Vector3D vector2 = new Vector3D(2.0, 0.0, 0.0);

        double result = Vector3D.dotProduct(vector1, vector2);

        assertEquals(result, 2.0, delta);
    }

    @Test
    public void canDotProductVectorsNegativeNumbers() {
        Vector3D vector1 = new Vector3D(-1.0, 0.0, -3.0);
        Vector3D vector2 = new Vector3D(1.0, 1.0, -1.0);

        double result = Vector3D.dotProduct(vector1, vector2);

        assertEquals(result, 2.0, delta);
    }

    @Test
    public void canDotProductVectorsOrthogonalVectors() {
        Vector3D vector1 = new Vector3D(1.0, 0.0, 3.0);
        Vector3D vector2 = new Vector3D(0.0, 5.0, 0.0);

        double result = Vector3D.dotProduct(vector1, vector2);

        assertEquals(result, 0.0, delta);
    }

    @Test
    public void canCrossProductVectorsTwoCoordinates() {
        Vector3D vector1 = new Vector3D(0.0, 1.0, 2.0);
        Vector3D vector2 = new Vector3D(0.0, 2.0, 3.0);

        Vector3D result = Vector3D.crossProduct(vector1, vector2);
        double x = result.getCoordinateX();
        double y = result.getCoordinateY();
        double z = result.getCoordinateZ();

        assertArrayEquals(new double[]{-1.0, 0.0, 0.0}, new double[]{x, y, z}, delta);
    }

    @Test
    public void canCrossProductCollinearVectors() {
        Vector3D vector1 = new Vector3D(2.0, 8.0, 5.0);
        Vector3D vector2 = new Vector3D(1.0, 4.0, 2.5);

        Vector3D result = Vector3D.crossProduct(vector1, vector2);
        double x = result.getCoordinateX();
        double y = result.getCoordinateY();
        double z = result.getCoordinateZ();

        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, new double[]{x, y, z}, delta);
    }

    @Test
    public void canCrossProductVectorsNegativeNumbers() {
        Vector3D vector1 = new Vector3D(-1.0, -2.0, -3.0);
        Vector3D vector2 = new Vector3D(-5.0, -8.0, 1);

        Vector3D result = Vector3D.crossProduct(vector1, vector2);
        double x = result.getCoordinateX();
        double y = result.getCoordinateY();
        double z = result.getCoordinateZ();

        assertArrayEquals(new double[]{-26.0, 16.0, -2}, new double[]{x, y, z}, delta);
    }

}
