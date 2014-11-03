package ru.unn.agile.Triangle;

public final class StringFormatter {

    private StringFormatter() { }

    public static String precisionFormat(final double value) {
        Integer cutter = (int) value;
        StringBuilder builder = new StringBuilder();
        builder.append(cutter.toString() + ".");

        final int precisionDegree = 100;
        long multipliedValue = Math.round(Math.abs(value * precisionDegree));
        cutter = ((int) multipliedValue) % precisionDegree;

        final int ten = 10;
        if (cutter < ten) {
            builder.append("0");
        }
        builder.append(cutter.toString());

        return builder.toString();
    }

    public static String format(final Point certainPoint) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        String x = precisionFormat(certainPoint.getX());
        builder.append(x + ", ");
        String y = precisionFormat(certainPoint.getY());
        builder.append(y + ")");
        return builder.toString();
    }
}
