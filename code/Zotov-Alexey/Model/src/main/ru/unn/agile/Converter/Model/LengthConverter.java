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

    private static double convertFromTo(final String from, final String to, final double value) {

        double check = koef.get(from) / koef.get(to);

        if (value < 0 || Double.MAX_VALUE / check < value) {
            throw new IllegalArgumentException();
        }

        return value * check;
    }

    public static double mToKm(final double val) {
        return convertFromTo("m", "km", val);
    }

    public static double mToMile(final double val) {
        return convertFromTo("m", "mile", val);
    }

    public static double mToInch(final double val) {
        return convertFromTo("m", "inch", val);
    }

    public static double kmToM(final double val) {
        return convertFromTo("km", "m", val);
    }

    public static double kmToMile(final double val) {
        return convertFromTo("km", "mile", val);
    }

    public static double kmToInch(final double val) {
        return convertFromTo("km", "inch", val);
    }

    public static double mileToM(final double val) {
        return convertFromTo("mile", "m", val);
    }

    public static double mileToKm(final double val) {
        return convertFromTo("mile", "km", val);
    }

    public static double mileToInch(final double val) {
        return convertFromTo("mile", "inch", val);
    }

    public static double inchToM(final double val) {
        return convertFromTo("inch", "m", val);
    }

    public static double inchToKm(final double val) {
        return convertFromTo("inch", "km", val);
    }

    public static double inchToMile(final double val) {
        return convertFromTo("inch", "mile", val);
    }
}
