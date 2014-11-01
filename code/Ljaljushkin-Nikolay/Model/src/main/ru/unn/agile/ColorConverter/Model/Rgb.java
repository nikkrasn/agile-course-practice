package ru.unn.agile.ColorConverter.Model;

public class Rgb extends ColorSpace {

    @Override
    public void initialize(final Rgb color) {
        RgbConverter.fromRgbToColorSpace(color, this);
    }

    @Override
    public Rgb toRgb() {
        return RgbConverter.toRgbColor(this);
    }

    public static final double[][] TRANS_MAT = {
            {0.4124, 0.3576, 0.1805},
            {0.2126, 0.7152, 0.0722},
            {0.0193, 0.1192, 0.9505}
    };
    public static final double MAX_RGB = 255.0;
    public static final double R_DIVIDER = 6.0;
    public static final int G_OFFSET = 2;
    public static final int B_OFFSET = 4;
    public static final int DEGREES = 60;
    public static final double EPS = 0.0001;
    public static final double THRESHOLD = 0.04045;

    private double r;
    private double g;
    private double b;

    public double getG() {
        return g;
    }

    public double getR() {
        return r;
    }

    public double getB() {
        return b;
    }

    public void setR(final double r) {
        this.r = r;
    }

    public void setG(final double g) {
        this.g = g;
    }

    public void setB(final double b) {
        this.b = b;
    }

    Rgb() {
        r = 0;
        g = 0;
        b = 0;
    }

    Rgb(final double r, final double g, final double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

//    public Hsv toHsv() {
//        double arithmeticR = r / MAX_RGB;
//        double arithmeticG = g / MAX_RGB;
//        double arithmeticB = b / MAX_RGB;
//
//        double max = Math.max(arithmeticR, Math.max(arithmeticG, arithmeticB));
//        double min = Math.min(arithmeticR, Math.min(arithmeticG, arithmeticB));
//        double delta = max - min;
//
//        double h = 0;
//        if (delta >= EPS) {
//            if (max == arithmeticR) {
//                h = DEGREES * ((arithmeticG - arithmeticB) / delta % R_DIVIDER);
//            } else if (max == arithmeticG) {
//                h = DEGREES * ((arithmeticB - arithmeticR) / delta + G_OFFSET);
//            } else if (max == arithmeticB) {
//                h = DEGREES * ((arithmeticR - arithmeticG) / delta + B_OFFSET);
//            }
//        }
//
//        double s = max <= 0 ? 0 : 1 - min / max;
//        double v = max;
//
//        return new Hsv(h, s, v);
//    }

//    public Lab toLab() {
//        return this.toXyz().toLab();
//    }

}
