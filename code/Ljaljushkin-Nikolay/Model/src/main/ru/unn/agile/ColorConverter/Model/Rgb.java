package ru.unn.agile.ColorConverter.Model;

public class Rgb {

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
            final int degrees = 60;
            final int rDivider = 6;
            final int gOffset = 2;
            final int bOffset = 4;

            if (max == r) {
                h = degrees * ((r - b) / delta % rDivider);
            } else if (max == g) {
                h = degrees * ((b - r) / delta + gOffset);
            } else if (max == b) {
                h = degrees * ((r - g) / delta + bOffset);
            }
        }

        final int maxRGB = 255;

        double s = max <= 0 ? 0 : 1 - min / max;
        double v = max / maxRGB;

        return new Hsv(h, s, v);
    }
}
