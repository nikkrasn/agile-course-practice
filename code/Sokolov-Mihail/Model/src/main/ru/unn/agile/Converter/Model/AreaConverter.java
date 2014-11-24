package ru.unn.agile.Converter.Model;

import java.util.HashMap;
import java.util.Map;

public final class AreaConverter {
    private static final double KOEF_SQM = 1.0;
    private static final double KOEF_SQKM = 1000000.0;
    private static final double KOEF_HA = 10000.0;
    private static final double KOEF_ARE = 100.0;

    private static Map<Measures, Double> koef = new HashMap<Measures, Double>();
    static {
        koef.put(Measures.SquareMeter, KOEF_SQM);
        koef.put(Measures.SquareKilometer, KOEF_SQKM);
        koef.put(Measures.Hectare, KOEF_HA);
        koef.put(Measures.Are, KOEF_ARE);
    };

    private AreaConverter() { }

    public static double fromTo(final double val,
                                final Measures from,
                                final Measures to) {
        if (val < 0) {
            throw new IllegalArgumentException();
        }

        double k = koef.get(from) / koef.get(to);

        if (Double.MAX_VALUE / k < val) {
            throw new IllegalArgumentException();
        }

        return val * k;
    }

    public enum Measures {
        SquareMeter("Square meter"),
        SquareKilometer("Square kilometer"),
        Hectare("Hectare"),
        Are("Are");

        private final String name;

        private Measures(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
