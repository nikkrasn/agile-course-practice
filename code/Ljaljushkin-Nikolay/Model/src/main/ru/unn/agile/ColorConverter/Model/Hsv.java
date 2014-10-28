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

}
