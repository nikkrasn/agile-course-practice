package ru.unn.agile.Metrics.Model;

import java.util.Vector;

public class Metrics {

    public static float L1(Vector<Float> vector1, Vector<Float> vector2) {
        float metricL1 = 0.0f;

        for (int i = 0; i < vector1.size(); i++) {
            metricL1 += Math.abs(vector1.elementAt(i)-vector2.elementAt(i));
        }

        return metricL1;
    }

    public static float L2(Vector<Float> vector1, Vector<Float> vector2) {
        float metricL2 = 0.0f;

        for (int i = 0; i < vector1.size(); i++) {
            metricL2 += Math.sqrt(Math.pow(vector1.elementAt(i)-vector2.elementAt(i), 2));
        }

        return metricL2;
    }
}
