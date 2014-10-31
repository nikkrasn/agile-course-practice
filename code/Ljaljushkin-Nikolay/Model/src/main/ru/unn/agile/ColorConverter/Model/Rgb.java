package ru.unn.agile.ColorConverter.Model;

public class Rgb {

    public static final double MAX_RGB = 255.0;
    public static final double R_DIVIDER = 6.0;
    public static final int G_OFFSET = 2;
    public static final int B_OFFSET = 4;
    public static final int DEGREES = 60;
    public static final double EPS = 0.0001;

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
        double eps = EPS;
        double rr = r / MAX_RGB;
        double gg = g / MAX_RGB;
        double bb = b / MAX_RGB;

        double max = Math.max(rr, Math.max(gg, bb));
        double min = Math.min(rr, Math.min(gg, bb));
        double delta = max - min;

        double h = 0;
        if (delta >= EPS) {
            if (max == rr) {
                h = DEGREES * ((gg - bb) / delta % R_DIVIDER);
            } else if (max == gg) {
                h = DEGREES * ((bb - rr) / delta + G_OFFSET);
            } else if (max == bb) {
                h = DEGREES * ((rr - gg) / delta + B_OFFSET);
            }
        }

        double s = max <= 0 ? 0 : 1 - min / max;
        double v = max;

        return new Hsv(h, s, v);
    }
}
