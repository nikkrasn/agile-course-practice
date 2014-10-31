package ru.unn.agile.ColorConverter.Model;

public class Hsv {

    private final double h;
    private final double s;
    private final double v;

    public double getH() {
        return h;
    }

    public double getS() {
        return s;
    }

    public double getV() {
        return v;
    }

    Hsv(final double h, final double s, final double v) {
        this.h = h;
        this.s = s;
        this.v = v;
    }

    public Rgb toRgb() {
        final double degrees = 60.0;
        final double maxRGB = 255.0;
        final int rDivider = 6;

        int range = (int) (Math.floor(h / degrees)) % rDivider;
        double f = h / degrees - Math.floor(h / degrees);

        double vv = v * maxRGB;
        double p = vv * (1 - s);
        double q = vv * (1 - f * s);
        double t = vv * (1 - (1 - f) * s);

        final int firstSixth  = 0;
        final int secondSixth = 1;
        final int thirdSixth  = 2;
        final int fourthSixth = 3;
        final int fifthSixth  = 4;

        switch (range) {
            case firstSixth:
                return new Rgb(vv, t, p);
            case secondSixth:
                return new Rgb(q, vv, p);
            case thirdSixth:
                return new Rgb(p, vv, t);
            case fourthSixth:
                return new Rgb(p, q, vv);
            case fifthSixth:
                return new Rgb(t, p, vv);
            default:
                return new Rgb(vv, p, q);
        }
    }

    public Lab toLab() {
        return this.toRgb().toLab();
    }


}
