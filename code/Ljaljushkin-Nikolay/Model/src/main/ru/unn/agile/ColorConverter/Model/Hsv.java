//package ru.unn.agile.ColorConverter.Model;
//
//public class Hsv {
//
//    private final double h;
//    private final double s;
//    private final double v;
//
//    public double getH() {
//        return h;
//    }
//
//    public double getS() {
//        return s;
//    }
//
//    public double getV() {
//        return v;
//    }
//
//    Hsv(final double h, final double s, final double v) {
//        this.h = h;
//        this.s = s;
//        this.v = v;
//    }
//
//    public Rgb toRgb() {
//        final double degrees = 60.0;
//        final double maxRGB = 255.0;
//        final int numberOfSection = 6;
//
//        int section = (int) (Math.floor(h / degrees)) % numberOfSection;
//        double fractionalPartOfHue = h / degrees - Math.floor(h / degrees);
//
//        double p = v * (1 - s);
//        double q = v * (1 - fractionalPartOfHue * s);
//        double t = v * (1 - (1 - fractionalPartOfHue) * s);
//
//        final int firstSection = 0;
//        final int secondSection = 1;
//        final int thirdSection = 2;
//        final int fourthSection = 3;
//        final int fifthSection = 4;
//
//        double r, g, b;
//        switch (section) {
//            case firstSection:
//                r = v;
//                g = t;
//                b = p;
//                break;
//            case secondSection:
//                r = q;
//                g = v;
//                b = p;
//                break;
//            case thirdSection:
//                r = p;
//                g = v;
//                b = t;
//                break;
//            case fourthSection:
//                r = p;
//                g = q;
//                b = v;
//                break;
//            case fifthSection:
//                r = t;
//                g = p;
//                b = v;
//                break;
//            default:
//                r = v;
//                g = p;
//                b = q;
//        }
//        return new Rgb(r * maxRGB, g * maxRGB, b * maxRGB);
//    }
//
//    public Lab toLab() {
//        return this.toRgb().toLab();
//    }
//
//
//}
