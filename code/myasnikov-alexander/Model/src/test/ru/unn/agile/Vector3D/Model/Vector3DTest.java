package ru.unn.agile.Vector3D.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class Vector3DTest {

    private double delta=0;

    @Test
    public void canGetNormDouble() {
        Vector3D vector = new Vector3D( 1.0, 0.0, 0.0 );

        double result = vector.GetNorm();

        assertEquals(result, 1, delta);
    }

    @Test
    public void canGetNormInt() {
        Vector3D vector = new Vector3D( 3, 0, 4 );

        double result = vector.GetNorm();

        assertEquals(result, 5.0, delta);
    }

    @Test
    public void canGetNormNegativeNumbers() {
        Vector3D vector = new Vector3D( 0, -3, 0 );

        double result = vector.GetNorm();

        assertEquals(result,3.0 , delta);
    }
}
