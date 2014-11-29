package ru.unn.agile.Metrics.Model;

import java.util.List;

public final class Metrics {

    public static float l1(final List<Float> vector1, final List<Float> vector2) {
        checkVectorsSizes(vector1, vector2);

        float metricL1 = 0.0f;

        for (int i = 0; i < vector1.size(); i++) {
            metricL1 += Math.abs(vector1.get(i) - vector2.get(i));
        }

        return metricL1;
    }

    public static float l2(final List<Float> vector1, final List<Float> vector2) {
        checkVectorsSizes(vector1, vector2);

        float metricL2 = 0.0f;
        final float powerOfMetric = 2.0f;

        for (int i = 0; i < vector1.size(); i++) {
            metricL2 += Math.sqrt(
                    Math.pow(vector1.get(i) - vector2.get(i), powerOfMetric));
        }

        return metricL2;
    }

    public static float l3(final List<Float> vector1, final List<Float> vector2) {
        checkVectorsSizes(vector1, vector2);

        float metricL3 = 0.0f;
        final float powerOfMetric = 3.0f;

        for (int i = 0; i < vector1.size(); i++) {
            metricL3 += Math.pow(
                    Math.pow(Math.abs(vector1.get(i) - vector2.get(i)), powerOfMetric),
                    1.0f / powerOfMetric);
        }

        return metricL3;
    }

    public static float l4(final List<Float> vector1, final List<Float> vector2) {
        checkVectorsSizes(vector1, vector2);

        float metricL4 = 0.0f;
        final float powerOfMetric = 4.0f;

        for (int i = 0; i < vector1.size(); i++) {
            metricL4 += Math.pow(
                    Math.pow(vector1.get(i) - vector2.get(i), powerOfMetric),
                    1.0f / powerOfMetric);
        }

        return metricL4;
    }

    public static float lInf(final List<Float> vector1, final List<Float> vector2) {
        checkVectorsSizes(vector1, vector2);

        float metricLInf = Math.abs(vector1.get(0) - vector2.get(0));

        for (int i = 1; i < vector1.size(); i++) {
            metricLInf = Math.max(
                    Math.abs(vector1.get(i) - vector2.get(i)), metricLInf);
        }

        return metricLInf;
    }

    private static void checkVectorsSizes(final List<Float> vector1,
                                          final List<Float> vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("One or both vectors are null");
        }
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("Vectors sizes are not equal");
        }
        if (vector1.isEmpty() && vector2.isEmpty()) {
            throw new IllegalArgumentException("Vectors are empty");
        }
    }

    private Metrics() {
    }

    public enum Operation {
        METRIC_L1("L1") {
            public Float apply(final List<Float> vector1, final List<Float> vector2) {
                return Metrics.l1(vector1, vector2);
            }
        },
        METRIC_L2("L2") {
            public Float apply(final List<Float> vector1, final List<Float> vector2) {
                return Metrics.l2(vector1, vector2);
            }
        },
        METRIC_L3("L3") {
            public Float apply(final List<Float> vector1, final List<Float> vector2) {
                return Metrics.l3(vector1, vector2);
            }
        },
        METRIC_L4("L4") {
            public Float apply(final List<Float> vector1, final List<Float> vector2) {
                return Metrics.l4(vector1, vector2);
            }
        },
        METRIC_LINF("LINF") {
            public Float apply(final List<Float> vector1, final List<Float> vector2) {
                return Metrics.lInf(vector1, vector2);
            }
        };

        private final String name;
        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract Float apply(final List<Float> vector1, final List<Float> vector2);
    }

}
