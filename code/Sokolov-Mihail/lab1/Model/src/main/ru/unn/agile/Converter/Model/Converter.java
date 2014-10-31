package ru.unn.agile.Converter.Model;

import java.util.HashMap;
import java.util.Map;

public final class Converter {

    private static final double KOEFM = 1.0;
    private static final double KOEFKM = 1000000.0;
    private static final double KOEFHA = 10000.0;
    private static final double KOEFARE = 100.0;

    private static Map<String, Double> koef = new HashMap<String, Double>();
    static {
        koef.put("sqm", KOEFM);
        koef.put("sqkm", KOEFKM);
        koef.put("ha", KOEFHA);
        koef.put("are", KOEFARE);
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
    public static double sqmTosqkm(final double val) {
        return fromTo(val, "sqm", "sqkm");
    }
    public static double sqmToha(final double val) {
        return fromTo(val, "sqm", "ha");
    }
    public static double sqmToare(final double val) {
        return fromTo(val, "sqm", "are");
    }

    // From sqkm
    public static double sqkmTosqm(final double val) {
        return fromTo(val, "sqkm", "sqm");
    }
    public static double sqkmToha(final double val) {
        return fromTo(val, "sqkm", "ha");
    }
    public static double sqkmToare(final double val) {
        return fromTo(val, "sqkm", "are");
    }

    // From ha
    public static double haTosqm(final double val) {
        return fromTo(val, "ha", "sqm");
    }
    public static double haTosqkm(final double val) {
        return fromTo(val, "ha", "sqkm");
    }
    public static double haToare(final double val) {
        return fromTo(val, "ha", "are");
    }

    // From are
    public static double areTosqm(final double val) {
        return fromTo(val, "are", "sqm");
    }
    public static double areTosqkm(final double val) {
        return fromTo(val, "are", "sqkm");
    }
    public static double areToha(final double val) {
        return fromTo(val, "are", "ha");
    }
}
