package ru.unn.agile.ColorConverter.Model;

public class Hsv extends ColorSpace {

    @Override
    public void initialize(final Rgb color) {
        HsvConverter.fromRgbToColorSpace(color, this);
    }

    @Override
    public Rgb toRgb() {
        return HsvConverter.toRgbColor(this);
    }

    private double h;
    private double s;
    private double v;

    public double getH() {
        return h;
    }

    public double getS() {
        return s;
    }

    public double getV() {
        return v;
    }

    public void setH(final double h) {
        this.h = h;
    }

    public void setS(final double s) {
        this.s = s;
    }

    public void setV(final double v) {
        this.v = v;
    }

    Hsv() {
        h = 0;
        s = 0;
        v = 0;
    }
    Hsv(final double h, final double s, final double v) {
        this.h = h;
        this.s = s;
        this.v = v;
    }
}
