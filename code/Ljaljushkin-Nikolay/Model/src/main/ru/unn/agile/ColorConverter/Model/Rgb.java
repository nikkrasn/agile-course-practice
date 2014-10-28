package ru.unn.agile.ColorConverter.Model;

public class Rgb {

    public static final double MAX_RGB = 255.0;
    public static final double R_DIVIDER = 6.0;
    public static final int G_OFFSET = 2;
    public static final int B_OFFSET = 4;
    public static final int DEGREES = 60;

    private final double r;
    private final double g;
    private final double b;

    public double getG() {
        return g;
    }

    public double getR() {
        return r;
    }

    public double getB() {
        return b;
    }

    public Rgb(final double r, final double g, final double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Hsv toHsv() {
        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));
        double delta = max - min;

        double h = 0;
        if (delta != 0) {
            if (max == r) {
                h = DEGREES * ((r - b) / delta % R_DIVIDER);
            } else if (max == g) {
                h = DEGREES * ((b - r) / delta + G_OFFSET);
            } else if (max == b) {
                h = DEGREES * ((r - g) / delta + B_OFFSET);
            }
        }

        double s = max <= 0 ? 0 : 1 - min / max;
        double v = max / MAX_RGB;

        return new Hsv(h, s, v);
    }
}
