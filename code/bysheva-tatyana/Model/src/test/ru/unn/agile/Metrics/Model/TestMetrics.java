package ru.unn.agile.Metrics.Model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class TestMetrics {

    private static final float EPSILON = 0.01f;

    private Vector<Float> vector1;
    private Vector<Float> vector2;

    @Test
    public void checkL1MetricForOneDimensionVectors() {
        createOneDimensionVectors();
        float metricL1 = Metrics.l1(vector1, vector2);
        assertEquals(1.0f, metricL1, EPSILON);
    }

    @Test
    public void checkL2MetricForOneDimensionVectors() {
        createOneDimensionVectors();
        float metricL2 = Metrics.l2(vector1, vector2);
        assertEquals(1.0f, metricL2, EPSILON);
    }

    @Test
    public void checkL3MetricForOneDimensionVectors() {
        createOneDimensionVectors();
        float metricL3 = Metrics.l3(vector1, vector2);
        assertEquals(1.0f, metricL3, EPSILON);
    }

    @Test
    public void checkL4MetricForOneDimensionVectors() {
        createOneDimensionVectors();
        float metricL4 = Metrics.l4(vector1, vector2);
        assertEquals(1.0f, metricL4, EPSILON);
    }

    @Test
    public void checkLInfMetricForOneDimensionVectors() {
        createOneDimensionVectors();
        float metricLInf = Metrics.lInf(vector1, vector2);
        assertEquals(1.0f, metricLInf, EPSILON);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL1MetricForDifferentDimensionVectors() {
        createVectorsWithDifferentDimension();
        Metrics.l1(vector1, vector2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL2MetricForDifferentDimensionVectors() {
        createVectorsWithDifferentDimension();
        Metrics.l2(vector1, vector2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL3MetricForDifferentDimensionVectors() {
        createVectorsWithDifferentDimension();
        Metrics.l3(vector1, vector2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL4MetricForDifferentDimensionVectors() {
        createVectorsWithDifferentDimension();
        Metrics.l4(vector1, vector2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkLInfMetricForDifferentDimensionVectors() {
        createVectorsWithDifferentDimension();
        Metrics.lInf(vector1, vector2);
    }

    @Test
    public void checkL1MetricForThreeDimensionVectors() {
        createThreeDimensionVectors();
        float metricL1 = Metrics.l1(vector1, vector2);
        assertEquals(9.0f, metricL1, EPSILON);
    }

    @Test
    public void checkL2MetricForThreeDimensionVectors() {
        createThreeDimensionVectors();
        float metricL2 = Metrics.l2(vector1, vector2);
        assertEquals(9.0f, metricL2, EPSILON);
    }

    @Test
    public void checkL3MetricForThreeDimensionVectors() {
        createThreeDimensionVectors();
        float metricL3 = Metrics.l3(vector1, vector2);
        assertEquals(9.0f, metricL3, EPSILON);
    }

    @Test
    public void checkL4MetricForThreeDimensionVectors() {
        createThreeDimensionVectors();
        float metricL4 = Metrics.l4(vector1, vector2);
        assertEquals(9.0f, metricL4, EPSILON);
    }

    @Test
    public void checkLInfMetricForThreeDimensionVectors() {
        createThreeDimensionVectors();
        float metricLInf = Metrics.lInf(vector1, vector2);
        assertEquals(3.0f, metricLInf, EPSILON);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL1MetricForEmptyVectors() {
        createEmptyVectors();
        Metrics.l1(vector1, vector2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL2MetricForEmptyVectors() {
        createEmptyVectors();
        Metrics.l2(vector1, vector2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL3MetricForEmptyVectors() {
        createEmptyVectors();
        Metrics.l3(vector1, vector2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkL4MetricForEmptyVectors() {
        createEmptyVectors();
        Metrics.l4(vector1, vector2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkLInfMetricForEmptyVectors() {
        createEmptyVectors();
        Metrics.lInf(vector1, vector2);
    }

    private void createThreeDimensionVectors() {
        vector1 = new Vector<>(Arrays.asList(new Float[] {1.0f, 2.0f, 3.0f}));
        vector2 = new Vector<>(Arrays.asList(new Float[] {4.0f, 5.0f, 6.0f}));
    }

    private void createOneDimensionVectors() {
        vector1 = new Vector<>(Arrays.asList(new Float[] {1.0f}));
        vector2 = new Vector<>(Arrays.asList(new Float[] {2.0f}));
    }

    private void createVectorsWithDifferentDimension() {
        vector1 = new Vector<>(Arrays.asList(new Float[] {1.0f}));
        vector2 = new Vector<>(Arrays.asList(new Float[] {4.0f, 5.0f, 6.0f}));
    }

    private void createEmptyVectors() {
        vector1 = new Vector<>(Arrays.asList(new Float[] {}));
        vector2 = new Vector<>(Arrays.asList(new Float[] {}));
    }
}
