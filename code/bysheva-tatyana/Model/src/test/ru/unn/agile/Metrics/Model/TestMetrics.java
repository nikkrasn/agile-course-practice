package ru.unn.agile.Metrics.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class TestMetrics {

    private final float epsilon = 0.01f;

    private Vector<Float> vector1;
    private Vector<Float> vector2;

    private Metrics metrics;

    @Before
    public void testSetUp() {
        Float[] array = new Float[] {0.0f};

        vector1 = new Vector<>(Arrays.asList(array));
        vector2 = new Vector<>(Arrays.asList(array));

        metrics = new Metrics();
    }

    @Test
    public void checkL1MetricForOneDimensionVectors() {
        float metricL1 = metrics.l1(vector1, vector2);
        assertEquals(0.0f, metricL1, epsilon);
    }

    @Test
    public void checkL2MetricForOneDimensionVectors() {
        float metricL2 = metrics.l2(vector1, vector2);
        assertEquals(0.0f, metricL2, epsilon);
    }

    @Test
    public void checkL3MetricForOneDimensionVectors() {
        float metricL3 = metrics.l3(vector1, vector2);
        assertEquals(0.0f, metricL3, epsilon);
    }

    @Test
    public void checkL4MetricForOneDimensionVectors() {
        float metricL4 = metrics.l4(vector1, vector2);
        assertEquals(0.0f, metricL4, epsilon);
    }

    @Test
    public void checkLInfMetricForOneDimensionVectors() {
        float metricLInf = metrics.lInf(vector1, vector2);
        assertEquals(0.0f, metricLInf, epsilon);
    }
}
