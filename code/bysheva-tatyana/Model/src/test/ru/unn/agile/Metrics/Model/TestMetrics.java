package ru.unn.agile.Metrics.Model;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestMetrics {

    public final float epsilon = 0.01f;

    @Test
    public void checkL1MetricForOneDimensionVectors() {
        Float[] array = new Float[]{0.0f};

        Vector<Float> vector1 = new Vector<Float>(Arrays.asList(array));
        Vector<Float> vector2 = new Vector<Float>(Arrays.asList(array));

        float metricL1 = Metrics.L1(vector1, vector2);

        assertEquals(0.0f, metricL1, epsilon);
    }

    @Test
    public void checkL2MetricForOneDimensionVectors() {
        Float[] array = new Float[]{0.0f};

        Vector<Float> vector1 = new Vector<Float>(Arrays.asList(array));
        Vector<Float> vector2 = new Vector<Float>(Arrays.asList(array));

        float metricL2 = Metrics.L2(vector1, vector2);

        assertEquals(0.0f, metricL2, epsilon);
    }

    @Test
    public void checkL3MetricForOneDimensionVectors() {
        Float[] array = new Float[]{0.0f};

        Vector<Float> vector1 = new Vector<Float>(Arrays.asList(array));
        Vector<Float> vector2 = new Vector<Float>(Arrays.asList(array));

        float metricL3 = Metrics.L3(vector1, vector2);

        assertEquals(0.0f, metricL3, epsilon);
    }

    @Test
    public void checkL4MetricForOneDimensionVectors() {
        Float[] array = new Float[]{0.0f};

        Vector<Float> vector1 = new Vector<Float>(Arrays.asList(array));
        Vector<Float> vector2 = new Vector<Float>(Arrays.asList(array));

        float metricL4 = Metrics.L4(vector1, vector2);

        assertEquals(0.0f, metricL4, epsilon);
    }
}