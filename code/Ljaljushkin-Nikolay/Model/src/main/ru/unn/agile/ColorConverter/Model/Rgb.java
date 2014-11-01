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

}
