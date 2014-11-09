package ru.unn.agile.ColorConverter.Model.TestUtilities;

import ru.unn.agile.ColorConverter.Model.ColorSpaces.*;

public final class KnownColors {

    public static final double X_WHITE = 95.05;
    public static final double Y_WHITE = 100.0;
    public static final double Z_WHITE = 108.9;
    public static final Xyz WHITE_XYZ = new Xyz(X_WHITE, Y_WHITE, Z_WHITE);

    public static final double L_WHITE = 100;
    public static final double A_WHITE = 0.01;
    public static final double B_WHITE = -0.01;
    public static final Lab WHITE_LAB = new Lab(L_WHITE, A_WHITE, B_WHITE);

    public static final double H_WHITE = 0;
    public static final double S_WHITE = 0;
    public static final double V_WHITE = 1;
    public static final Hsv WHITE_HSV = new Hsv(H_WHITE, S_WHITE, V_WHITE);

    public static final double MAX_RGB = 255;
    public static final Rgb WHITE_RGB = new Rgb(MAX_RGB, MAX_RGB, MAX_RGB);

    public static final double BLACK = 0;
    public static final Xyz BLACK_XYZ = new Xyz(BLACK, BLACK, BLACK);
    public static final Hsv BLACK_HSV = new Hsv(BLACK, BLACK, BLACK);
    public static final Lab BLACK_LAB = new Lab(BLACK, BLACK, BLACK);
    public static final Rgb BLACK_RGB = new Rgb(BLACK, BLACK, BLACK);

    public static final double L_DARK_RED = 28.0847;
    public static final double A_DARK_RED = 51.0104;
    public static final double B_DARK_RED = 41.2945;
    public static final Lab DARK_RED_LAB = new Lab(L_DARK_RED, A_DARK_RED, B_DARK_RED);

    public static final double X_DARK_RED = 10.6474;
    public static final double Y_DARK_RED = 5.4889;
    public static final double Z_DARK_RED = 0.4982;
    public static final Xyz DARK_RED_XYZ = new Xyz(X_DARK_RED, Y_DARK_RED, Z_DARK_RED);

    public static final double H_DARK_RED = 0;
    public static final double S_DARK_RED = 1;
    public static final double V_DARK_RED = 0.55;
    public static final Hsv DARK_RED_HSV = new Hsv(H_DARK_RED, S_DARK_RED, V_DARK_RED);

    public static final double R_DARK_RED = 139;
    public static final Rgb DARK_RED_RGB = new Rgb(R_DARK_RED, 0, 0);

    public static final double X_ORANGE = 54.69;
    public static final double Y_ORANGE = 48.17;
    public static final double Z_ORANGE = 6.41;
    public static final Xyz ORANGE_XYZ = new Xyz(X_ORANGE, Y_ORANGE, Z_ORANGE);

    public static final double R_ORANGE = 255;
    public static final double G_ORANGE = 165;
    public static final double B_ORANGE = 0;
    public static final Rgb ORANGE_RGB = new Rgb(R_ORANGE, G_ORANGE, B_ORANGE);

    public static final double L_ORANGE = 74.93;
    public static final double A_ORANGE = 23.94;
    public static final double LAB_B_ORANGE = 78.96;
    public static final Lab ORANGE_LAB = new Lab(L_ORANGE, A_ORANGE, LAB_B_ORANGE);

    public static final double R_AQUAMARINE = 127;
    public static final double G_AQUAMARINE = 255;
    public static final double B_AQUAMARINE = 212;
    public static final Rgb AQUAMARINE_RGB = new Rgb(R_AQUAMARINE, G_AQUAMARINE, B_AQUAMARINE);

    public static final double H_AQUAMARINE = 159.84;
    public static final double S_AQUAMARINE = 0.5;
    public static final double V_AQUAMARINE = 1;
    public static final Hsv AQUAMARINE_HSV = new Hsv(H_AQUAMARINE, S_AQUAMARINE, V_AQUAMARINE);

    public static final double L_AQUAMARINE = 92.04;
    public static final double A_AQUAMARINE = -45.52;
    public static final double LAB_B_AQUAMARINE = 9.71;
    public static final Lab AQUAMARINE_LAB = new Lab(L_AQUAMARINE, A_AQUAMARINE, LAB_B_AQUAMARINE);

    private KnownColors() {
    }
}
