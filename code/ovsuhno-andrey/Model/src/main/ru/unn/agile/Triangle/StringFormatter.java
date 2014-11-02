package ru.unn.agile.Triangle;

public final class StringFormatter {

    private StringFormatter() { }

    public static String precisionFormat(final double value) {
        Integer cutter = (int) value;
        String buffer = cutter.toString();

        buffer += ".";

        final int precisionDegree = 100;
        long multipliedValue = Math.round(Math.abs(value * precisionDegree));
        cutter = ((int) multipliedValue) % precisionDegree;

        if (cutter < 10) {
            buffer += "0";
        }
        buffer += cutter.toString();

        return buffer;
    }

    public static String format(final Point certainPoint) {
        String buffer = new String();
        buffer = "(";
        String x = precisionFormat(certainPoint.getX());
        buffer += x + ", ";
        String y = precisionFormat(certainPoint.getY());
        buffer += y + ")";
        return buffer;
    }

}
