package ru.unn.agile.ComplexNumber.model_lab3_legacy;

public final class Formatter {

    private Formatter() { }

    public static String formatPositiveDouble(final double value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }

        Integer i = (int) value;
        String buffer = i.toString();

        final int hundred = 100;
        final int ten = 10;

        double val = value * hundred; // We want only 2 digits
        i = (int) val % hundred;
        buffer += ".";
        if (i == 0) {
            buffer += "0";
        } else if (i < ten) {
            buffer += "0";
            buffer += i.toString();
        } else {
            if (i % ten == 0) {
                i /= ten;
            }
            buffer += i.toString();
        }

        return buffer;
    }

    public static String getFormatted(final ComplexNumber z) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(z.getReal() < 0 ? "-" : "");
        String re = formatPositiveDouble(Math.abs(z.getReal()));
        String im = formatPositiveDouble(Math.abs(z.getImaginary()));
        buffer.append(re);
        buffer.append(z.getImaginary() < 0 ? " - " : " + ");
        buffer.append(im + "i");
        return buffer.toString();
    }

}
