package ru.unn.agile.Metrics.Model;

import java.util.Vector;

public class Metrics {

    public float l1(final Vector<Float> vector1, final Vector<Float> vector2) {

        checkVectorsSizes(vector1, vector2);

        float metricL1 = 0.0f;

        for (int i = 0; i < vector1.size(); i++) {
            metricL1 += Math.abs(vector1.elementAt(i) - vector2.elementAt(i));
        }

        return metricL1;
    }

    public float l2(final Vector<Float> vector1, final Vector<Float> vector2) {

        checkVectorsSizes(vector1, vector2);

        float metricL2 = 0.0f;
        final float powerOfMetric = 2.0f;

        for (int i = 0; i < vector1.size(); i++) {
            metricL2 += Math.sqrt(
                    Math.pow(vector1.elementAt(i) - vector2.elementAt(i), powerOfMetric));
        }

        return metricL2;
    }

    public float l3(final Vector<Float> vector1, final Vector<Float> vector2) {

        checkVectorsSizes(vector1, vector2);

        float metricL3 = 0.0f;
        final float powerOfMetric = 3.0f;

        for (int i = 0; i < vector1.size(); i++) {
            metricL3 += Math.pow(
                    Math.pow(vector1.elementAt(i) - vector2.elementAt(i), powerOfMetric),
                    1.0f / powerOfMetric);
        }

        return metricL3;
    }

    public float l4(final Vector<Float> vector1, final Vector<Float> vector2) {

        checkVectorsSizes(vector1, vector2);

        float metricL4 = 0.0f;
        final float powerOfMetric = 4.0f;

        for (int i = 0; i < vector1.size(); i++) {
            metricL4 += Math.pow(
                    Math.pow(vector1.elementAt(i) - vector2.elementAt(i), powerOfMetric),
                    1.0f / powerOfMetric);
        }

        return metricL4;
    }

    public float lInf(final Vector<Float> vector1, final Vector<Float> vector2) {

        checkVectorsSizes(vector1, vector2);

        float metricLInf = Math.abs(vector1.elementAt(0) - vector2.elementAt(0));

        for (int i = 1; i < vector1.size(); i++) {
            metricLInf = Math.max(
                    Math.abs(vector1.elementAt(i) - vector2.elementAt(i)), metricLInf);
        }

        return metricLInf;
    }

    private void checkVectorsSizes(final Vector<Float> vector1, final Vector<Float> vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("Vectors sizes are not equal");
        }
    }
}
