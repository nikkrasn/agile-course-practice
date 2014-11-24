package ru.unn.agile.Converter.Model;

import java.util.HashMap;
import java.util.Map;

public final class AreaConverter {
    private static final double KOEF_SQM = 1.0;
    private static final double KOEF_SQKM = 1000000.0;
    private static final double KOEF_HA = 10000.0;
    private static final double KOEF_ARE = 100.0;

    private static Map<MeasureOfArea, Double> koef = new HashMap<MeasureOfArea, Double>();
    static {
        koef.put(MeasureOfArea.SquareMeter, KOEF_SQM);
        koef.put(MeasureOfArea.SquareKilometer, KOEF_SQKM);
        koef.put(MeasureOfArea.Hectare, KOEF_HA);
        koef.put(MeasureOfArea.Are, KOEF_ARE);
    };

    private AreaConverter() { }

    public static double fromTo(final double val, final MeasureOfArea from, final MeasureOfArea to) {
        if (val < 0) {
            throw new IllegalArgumentException();
        }

        double k = koef.get(from) / koef.get(to);

        if (Double.MAX_VALUE / k < val) {
            throw new IllegalArgumentException();
        }

        return val * k;
    }

    public enum MeasureOfArea {
        SquareMeter("Square meter"),
        SquareKilometer("Square kilometer"),
        Hectare("Hectare"),
        Are("Are");

        private final String name;

        private MeasureOfArea(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
