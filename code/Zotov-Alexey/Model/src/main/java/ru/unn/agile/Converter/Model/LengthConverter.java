package ru.unn.agile.Converter.Model;

import java.util.HashMap;
import java.util.Map;

public final class LengthConverter {

    private static final double KOEF_M = 1.;
    private static final double KOEF_KM = 1000.;
    private static final double KOEF_MILE = 1609.344;
    private static final double KOEF_INCH = 0.0254;

    private static Map<Measure, Double> koef = new HashMap<Measure, Double>();
    static {
        koef.put(Measure.METER, KOEF_M);
        koef.put(Measure.KILOMETER, KOEF_KM);
        koef.put(Measure.MILE, KOEF_MILE);
        koef.put(Measure.INCH, KOEF_INCH);
    }

    public enum Measure {
        METER("Meter"),
        KILOMETER("Kilometer"),
        MILE("Mile"),
        INCH("Inch");

        public String toString() {
            return name;
        }

        private final String name;

        private Measure(final String name) {
            this.name = name;
        }
    }

    private LengthConverter() { }

    public static double convertFromTo(final Measure from, final Measure to, final double value) {

        double check = koef.get(from) / koef.get(to);

        if (value < 0 || Double.MAX_VALUE / check < value) {
            throw new IllegalArgumentException();
        }

        return value * check;
    }
}
