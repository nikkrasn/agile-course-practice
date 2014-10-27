package ru.unn.agile.Vector3D.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class Vector3DTest {

    private double delta=1e-10;

    @Test
    public void canGetNorm() {
        Vector3D vector = new Vector3D( 1.0, 0.0, 0.0 );

        double result = vector.GetNorm();

        assertEquals(result, 1, delta);
    }

    @Test
    public void canGetNormZeroVector() {
        Vector3D vector = new Vector3D( 0, 0, 0 );

        double result = vector.GetNorm();

        assertEquals(result, 0.0, delta);
    }

    @Test
    public void canGetNormNegativeNumbers() {
        Vector3D vector = new Vector3D( 0, -3, 0 );

        double result = vector.GetNorm();

        assertEquals(result,3.0 , delta);
    }

    @Test
    public void canNormalizeVectorNormOne() {
        Vector3D vector = new Vector3D( 0, 0, 1 );

        vector.Normalize();
        double x = vector.GetCoordinateX();
        double y = vector.GetCoordinateY();
        double z = vector.GetCoordinateZ();

        assertArrayEquals(new double[]{0, 0, 1}, new double[]{x, y, z}, delta);
    }

    @Test
    public void canNormalizeVectorNegativeNumbers() {
        Vector3D vector = new Vector3D( -1, -1, 0 );

        vector.Normalize();
        double x = vector.GetCoordinateX();
        double y = vector.GetCoordinateY();
        double z = vector.GetCoordinateZ();

        assertArrayEquals(new double[]{-Math.sqrt(2.)/2, -Math.sqrt(2.)/2, 0}, new double[]{x, y, z}, delta);
    }

    @Test
    public void canNormalizeZeroVector() {
        Vector3D vector = new Vector3D( 0, 0, 0 );

        vector.Normalize();
        double x = vector.GetCoordinateX();
        double y = vector.GetCoordinateY();
        double z = vector.GetCoordinateZ();

        assertArrayEquals(new double[]{0, 0, 0}, new double[]{x, y, z}, delta);
    }



}
