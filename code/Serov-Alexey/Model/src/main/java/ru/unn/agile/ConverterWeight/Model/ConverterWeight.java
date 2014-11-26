package ru.unn.agile.ConverterWeight.Model;

import java.util.HashMap;
import java.util.Map;

public class ConverterWeight {
    private static final double GRAMM           = 1.;
    private static final double KILLOGRAM       = 1000.;
    private static final double CENTNER         = 100000.;
    private static final double TON             = 1000000.;

    private static Map<String, Double> koef = new HashMap<String, Double>();
    static {
        koef.put("GRAMM", GRAMM);
        koef.put("KILOGRAM", KILLOGRAM);
        koef.put("CENTNER", CENTNER);
        koef.put("TON", TON);
    }

    public static double converter(final String from, final String to, final double input) {
        double coefficient = koef.get(from) / koef.get(to);

        if (input < 0 || input * coefficient > Double.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        return input * coefficient;
    }

    public double grammToKilogram(final double input) {
        return converter("GRAMM", "KILOGRAM", input);
    }

    public double grammToCentner(final double input) {
        return converter("GRAMM", "CENTNER", input);
    }

    public double grammToTon(final double input) {
        return converter("GRAMM", "TON", input);
    }

    public double kilogramToGramm(final double input) {
        return converter("KILOGRAM", "GRAMM", input);
    }

    public double kilogramToCentner(final double input) {
        return converter("KILOGRAM", "CENTNER", input);
    }

    public double kilogramToTon(final double input) {
        return converter("KILOGRAM", "TON", input);
    }

    public double centnerToGramm(final double input) {
        return converter("CENTNER", "GRAMM", input);
    }

    public double centnerToKilogram(final double input) {
        return converter("CENTNER", "KILOGRAM", input);
    }

    public double centnerToTon(final double input) {
        return converter("CENTNER", "TON", input);
    }

    public double tonToGramm(final double input) {
        return converter("TON", "GRAMM", input);
    }

    public double tonToKilogram(final double input) {
        return converter("TON", "KILOGRAM", input);
    }

    public double tonToCentner(final double input) {
        return converter("TON", "CENTNER", input);
    }
}
