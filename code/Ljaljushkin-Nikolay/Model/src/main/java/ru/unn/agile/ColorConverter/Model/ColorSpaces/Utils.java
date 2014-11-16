package ru.unn.agile.ColorConverter.Model.ColorSpaces;

public final class Utils {

    private static final double EPS = 0.01;

    public static boolean isCloseEnough(final double a, final double b) {
        double difference = Math.abs(a * EPS);
        if (a == 0.0 || b == 0.0) {
            return Math.abs(a - b) <= EPS;
        } else {
            return Math.abs(a - b) <= difference;
        }
    }

    private Utils() {
    }
}
