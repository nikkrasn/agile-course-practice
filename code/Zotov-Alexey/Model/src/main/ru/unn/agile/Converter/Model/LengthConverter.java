package ru.unn.agile.Converter.Model;

import java.util.HashMap;
import java.util.Map;

public final class LengthConverter {

    private static final double KOEF_M = 1.;
    private static final double KOEF_KM = 1000.;
    private static final double KOEF_MILE = 1609.344;
    private static final double KOEF_INCH = 0.0254;

    private static Map<String, Double> koef = new HashMap<String, Double>();
    static {
        koef.put("m", KOEF_M);
        koef.put("km", KOEF_KM);
        koef.put("mile", KOEF_MILE);
        koef.put("inch", KOEF_INCH);
    }

    private LengthConverter() { }

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

    // From m
    public static double mToKm(final double val) {
        return fromTo(val, "m", "km");
    }
    public static double mToMile(final double val) {
        return fromTo(val, "m", "mile");
    }
    public static double mToInch(final double val) {
        return fromTo(val, "m", "inch");
    }

    // From km
    public static double kmToM(final double val) {
        return fromTo(val, "km", "m");
    }
    public static double kmToMile(final double val) {
        return fromTo(val, "km", "mile");
    }
    public static double kmToInch(final double val) {
        return fromTo(val, "km", "inch");
    }

    // From mile
    public static double mileToM(final double val) {
        return fromTo(val, "mile", "m");
    }
    public static double mileToKm(final double val) {
        return fromTo(val, "mile", "km");
    }
    public static double mileToInch(final double val) {
        return fromTo(val, "mile", "inch");
    }

    // From inch
    public static double inchToM(final double val) {
        return fromTo(val, "inch", "m");
    }
    public static double inchToKm(final double val) {
        return fromTo(val, "inch", "km");
    }
    public static double inchToMile(final double val) {
        return fromTo(val, "inch", "mile");
    }

}
