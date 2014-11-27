package ru.unn.agile.ConverterWeight.Model;

import java.util.HashMap;
import java.util.Map;

public final class ConverterWeight {

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

    public static double converter(final UnitWeight from, final UnitWeight to, final double input) {
        double coefficient = koef.get(from.toString()) / koef.get(to.toString());

        if (input < 0 || input * coefficient > Double.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        return input * coefficient;
    }

    public enum UnitWeight {
        GRAMM("GRAMM"),
        KILOGRAM("KILOGRAM"),
        CENTNER("CENTNER"),
        TON("TON");
        private final String name;

        private UnitWeight(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    private ConverterWeight() { }
}
