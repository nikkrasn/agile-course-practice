package ru.unn.agile.ConverterWeight.Model;

import java.util.HashMap;
import java.util.Map;

public final class ConverterWeight {
    private static final double GRAMM           = 1.;
    private static final double KILOGRAMM       = 1000.;
    private static final double CENTNER         = 100000.;
    private static final double TON             = 1000000.;

    private static Map<UnitWeight, Double> koef = new HashMap<UnitWeight, Double>();
    static {
        koef.put(UnitWeight.GRAMM, GRAMM);
        koef.put(UnitWeight.KILOGRAMM, KILOGRAMM);
        koef.put(UnitWeight.CENTNER, CENTNER);
        koef.put(UnitWeight.TON, TON);
    }

    public static double converter(final UnitWeight from, final UnitWeight to, final double input) {
        double coefficient = koef.get(from) / koef.get(to);

        if (input < 0 || input * coefficient > Double.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        return input * coefficient;
    }

    public enum UnitWeight {
        GRAMM("GRAMM"),
        KILOGRAMM("KILOGRAMM"),
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
