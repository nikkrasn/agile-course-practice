package ru.unn.agile.Metrics.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MetricsTest {

    private static final float EPSILON = 0.01f;

    private ArrayList<Float> vector0D;

    private ArrayList<Float> firstVector1D;
    private ArrayList<Float> secondVector1D;

    private ArrayList<Float> firstVector3D;
    private ArrayList<Float> secondVector3D;

    @Before
    public void setUp() {
        firstVector1D = new ArrayList<>(Arrays.asList(new Float[] {1.0f}));
        secondVector1D = new ArrayList<>(Arrays.asList(new Float[] {2.0f}));

        firstVector3D = new ArrayList<>(Arrays.asList(new Float[] {1.0f, 2.0f, 3.0f}));
        secondVector3D = new ArrayList<>(Arrays.asList(new Float[] {4.0f, 5.0f, 6.0f}));

        vector0D = new ArrayList<>(Arrays.asList(new Float[] {}));
    }

    @Test
    public void resultIsCorrectWithL1In1D() {
        float metricL1 = Metrics.l1(firstVector1D, secondVector1D);
        assertEquals(1.0f, metricL1, EPSILON);
    }

    @Test
    public void resultIsCorrectWithL2In1D() {
        float metricL2 = Metrics.l2(firstVector1D, secondVector1D);
        assertEquals(1.0f, metricL2, EPSILON);
    }

    @Test
    public void resultIsCorrectWithL3In1D() {
        float metricL3 = Metrics.l3(firstVector1D, secondVector1D);
        assertEquals(1.0f, metricL3, EPSILON);
    }

    @Test
    public void resultIsCorrectWithL4In1D() {
        float metricL4 = Metrics.l4(firstVector1D, secondVector1D);
        assertEquals(1.0f, metricL4, EPSILON);
    }

    @Test
    public void resultIsCorrectWithLInfIn1D() {
        float metricLInf = Metrics.lInf(firstVector1D, secondVector1D);
        assertEquals(1.0f, metricLInf, EPSILON);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL1MetricForDifferentDimensionVectors() {
        Metrics.l1(firstVector1D, secondVector3D);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL2MetricForDifferentDimensionVectors() {
        Metrics.l2(firstVector1D, secondVector3D);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL3MetricForDifferentDimensionVectors() {
        Metrics.l3(firstVector1D, secondVector3D);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL4MetricForDifferentDimensionVectors() {
        Metrics.l4(firstVector1D, secondVector3D);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkLInfMetricForDifferentDimensionVectors() {
        Metrics.lInf(firstVector1D, secondVector3D);
    }

    @Test
    public void resultIsCorrectWithL1In3D() {
        float metricL1 = Metrics.l1(firstVector3D, secondVector3D);
        assertEquals(9.0f, metricL1, EPSILON);
    }

    @Test
    public void resultIsCorrectWithL2In3D() {
        float metricL2 = Metrics.l2(firstVector3D, secondVector3D);
        assertEquals(9.0f, metricL2, EPSILON);
    }

    @Test
    public void resultIsCorrectWithL3In3D() {
        float metricL3 = Metrics.l3(firstVector3D, secondVector3D);
        assertEquals(9.0f, metricL3, EPSILON);
    }

    @Test
    public void resultIsCorrectWithL4In3D() {
        float metricL4 = Metrics.l4(firstVector3D, secondVector3D);
        assertEquals(9.0f, metricL4, EPSILON);
    }

    @Test
    public void resultIsCorrectWithLInfIn3D() {
        float metricLInf = Metrics.lInf(firstVector3D, secondVector3D);
        assertEquals(3.0f, metricLInf, EPSILON);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL1MetricForNullObjects() {
        Metrics.l1(null, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL2MetricForNullObjects() {
        Metrics.l2(null, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL3MetricForNullObjects() {
        Metrics.l3(null, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL4MetricForNullObjects() {
        Metrics.l4(null, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkLInfMetricForNullObjects() {
        Metrics.lInf(null, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL1MetricForEmptyVectors() {
        Metrics.l1(vector0D, vector0D);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL2MetricForEmptyVectors() {
        Metrics.l2(vector0D, vector0D);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL3MetricForEmptyVectors() {
        Metrics.l3(vector0D, vector0D);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL4MetricForEmptyVectors() {
        Metrics.l4(vector0D, vector0D);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkLInfMetricForEmptyVectors() {
        Metrics.lInf(vector0D, vector0D);
    }
}
