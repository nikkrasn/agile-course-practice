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

    public static double squareMeterToSquareKilometer(final double val) {
        return fromTo(val, "sqm", "sqkm");
    }

    public static double squareMeterToHectare(final double val) {
        return fromTo(val, "sqm", "ha");
    }

    public static double squareMeterToAre(final double val) {
        return fromTo(val, "sqm", "are");
    }

    public static double squareKilometerToSquareMeter(final double val) {
        return fromTo(val, "sqkm", "sqm");
    }

    public static double squareKilometerToHectare(final double val) {
        return fromTo(val, "sqkm", "ha");
    }

    public static double squareKilometerToAre(final double val) {
        return fromTo(val, "sqkm", "are");
    }

    public static double hectareToSquareMeter(final double val) {
        return fromTo(val, "ha", "sqm");
    }

    public static double hectareToSquareKilometer(final double val) {
        return fromTo(val, "ha", "sqkm");
    }

    public static double hectareToAre(final double val) {
        return fromTo(val, "ha", "are");
    }

    public static double areToSquareMeter(final double val) {
        return fromTo(val, "are", "sqm");
    }

    public static double areToSquareKilometer(final double val) {
        return fromTo(val, "are", "sqkm");
    }

    public static double areToHectare(final double val) {
        return fromTo(val, "are", "ha");
    }

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
}
