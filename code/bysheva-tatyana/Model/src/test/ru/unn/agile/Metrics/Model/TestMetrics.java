package ru.unn.agile.Metrics.Model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class TestMetrics {

    private final float epsilon = 0.01f;

    private Vector<Float> vector1;
    private Vector<Float> vector2;

    @Test
    public void checkL1MetricForOneDimensionVectors() {
        fillOneDimensionVectorsBySomeValues();
        float metricL1 = Metrics.l1(vector1, vector2);
        assertEquals(1.0f, metricL1, epsilon);
    }

    @Test
    public void checkL2MetricForOneDimensionVectors() {
        fillOneDimensionVectorsBySomeValues();
        float metricL2 = Metrics.l2(vector1, vector2);
        assertEquals(1.0f, metricL2, epsilon);
    }

    @Test
    public void checkL3MetricForOneDimensionVectors() {
        fillOneDimensionVectorsBySomeValues();
        float metricL3 = Metrics.l3(vector1, vector2);
        assertEquals(1.0f, metricL3, epsilon);
    }

    @Test
    public void checkL4MetricForOneDimensionVectors() {
        fillOneDimensionVectorsBySomeValues();
        float metricL4 = Metrics.l4(vector1, vector2);
        assertEquals(1.0f, metricL4, epsilon);
    }

    @Test
    public void checkLInfMetricForOneDimensionVectors() {
        fillOneDimensionVectorsBySomeValues();
        float metricLInf = Metrics.lInf(vector1, vector2);
        assertEquals(1.0f, metricLInf, epsilon);
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
        fillThreeDimensionVectorsBySomeValues();
        float metricL1 = Metrics.l1(vector1, vector2);
        assertEquals(9.0f, metricL1, epsilon);
    }

    @Test
    public void checkL2MetricForThreeDimensionVectors() {
        fillThreeDimensionVectorsBySomeValues();
        float metricL2 = Metrics.l2(vector1, vector2);
        assertEquals(9.0f, metricL2, epsilon);
    }

    @Test
    public void checkL3MetricForThreeDimensionVectors() {
        fillThreeDimensionVectorsBySomeValues();
        float metricL3 = Metrics.l3(vector1, vector2);
        assertEquals(9.0f, metricL3, epsilon);
    }

    @Test
    public void checkL4MetricForThreeDimensionVectors() {
        fillThreeDimensionVectorsBySomeValues();
        float metricL4 = Metrics.l4(vector1, vector2);
        assertEquals(9.0f, metricL4, epsilon);
    }

    @Test
    public void checkLInfMetricForThreeDimensionVectors() {
        fillThreeDimensionVectorsBySomeValues();
        float metricLInf = Metrics.lInf(vector1, vector2);
        assertEquals(3.0f, metricLInf, epsilon);
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

    private void fillThreeDimensionVectorsBySomeValues() {
        vector1 = new Vector<>(Arrays.asList(new Float[] {1.0f, 2.0f, 3.0f}));
        vector2 = new Vector<>(Arrays.asList(new Float[] {4.0f, 5.0f, 6.0f}));
    }

    private void fillOneDimensionVectorsBySomeValues() {
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
