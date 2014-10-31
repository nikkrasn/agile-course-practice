package ru.unn.agile.Converter.Model;

/**
 * Created by Алексей on 28.10.2014.
 */
import java.util.HashMap;
import java.util.Map;

public final class Converter {

    private static final double KOEFM = 1.;
    private static final double KOEFKM = 1000.;
    private static final double KOEFMILE = 1609.344;
    private static final double KOEFINCH = 0.0254;

    private static Map<String, Double> koef = new HashMap<String, Double>();
    static {
        koef.put("m", KOEFM);
        koef.put("km", KOEFKM);
        koef.put("mile", KOEFMILE);
        koef.put("inch", KOEFINCH);
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

    // From m
    public static double mTokm(final double val) {
        return fromTo(val, "m", "km");
    }
    public static double mTomile(final double val) {
        return fromTo(val, "m", "mile");
    }
    public static double mToinch(final double val) {
        return fromTo(val, "m", "inch");
    }

    // From km
    public static double kmTom(final double val) {
        return fromTo(val, "km", "m");
    }
    public static double kmTomile(final double val) {
        return fromTo(val, "km", "mile");
    }
    public static double kmToinch(final double val) {
        return fromTo(val, "km", "inch");
    }

    // From mile
    public static double mileTom(final double val) {
        return fromTo(val, "mile", "m");
    }
    public static double mileTokm(final double val) {
        return fromTo(val, "mile", "km");
    }
    public static double mileToinch(final double val) {
        return fromTo(val, "mile", "inch");
    }

    // From inch
    public static double inchTom(final double val) {
        return fromTo(val, "inch", "m");
    }
    public static double inchTokm(final double val) {
        return fromTo(val, "inch", "km");
    }
    public static double inchTomile(final double val) {
        return fromTo(val, "inch", "mile");
    }

}
