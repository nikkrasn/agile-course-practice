package ru.unn.agile.Converter.Model;

import java.util.HashMap;
import java.util.Map;

public final class Converter {

    private static final double KOEF_M = 1.0;
    private static final double KOEF_KM = 1000000.0;
    private static final double KOEF_HA = 10000.0;
    private static final double KOEF_ARE = 100.0;

    private static Map<String, Double> koef = new HashMap<String, Double>();
    static {
        koef.put("sqm", KOEF_M);
        koef.put("sqkm", KOEF_KM);
        koef.put("ha", KOEF_HA);
        koef.put("are", KOEF_ARE);
    };

    private Converter() { }

    private static double fromTo(final double val, final String from, final String to) {
        if (val < 0) {
            throw new IllegalArgumentException();
        }

        double k = koef.get(from) / koef.get(to);

        if (Double.MAX_VALUE / k < val) {
            throw new IllegalArgumentException();
        }

        return val * k;
    }

    // From sqm
    public static double sqmToSqkm(final double val) {
        return fromTo(val, "sqm", "sqkm");
    }
    public static double sqmToHa(final double val) {
        return fromTo(val, "sqm", "ha");
    }
    public static double sqmToAre(final double val) {
        return fromTo(val, "sqm", "are");
    }

    // From sqkm
    public static double sqkmToSqm(final double val) {
        return fromTo(val, "sqkm", "sqm");
    }
    public static double sqkmToHa(final double val) {
        return fromTo(val, "sqkm", "ha");
    }
    public static double sqkmToAre(final double val) {
        return fromTo(val, "sqkm", "are");
    }

    // From ha
    public static double haToSqm(final double val) {
        return fromTo(val, "ha", "sqm");
    }
    public static double haToSqkm(final double val) {
        return fromTo(val, "ha", "sqkm");
    }
    public static double haToAre(final double val) {
        return fromTo(val, "ha", "are");
    }

    // From are
    public static double areToSqm(final double val) {
        return fromTo(val, "are", "sqm");
    }
    public static double areToSqkm(final double val) {
        return fromTo(val, "are", "sqkm");
    }
    public static double areToHa(final double val) {
        return fromTo(val, "are", "ha");
    }
}
