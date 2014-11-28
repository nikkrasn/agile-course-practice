package ru.unn.agile.Matrix;

public final class Converter {

    private Converter() {
    }

    public static double[][] stringToArray(final String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        String[] textLine = text.split("[\n]+");
        double[][] data = new double[textLine.length][textLine.length];
        for (int i = 0; i < textLine.length; i++) {
            textLine[i] = textLine[i].trim();
            String[] textValue = textLine[i].split(" +");
            if (textValue.length > textLine.length) {
                throw new IllegalArgumentException();
            }
            for (int j = 0; j < textValue.length; j++) {
                data[i][j] = Double.parseDouble(textValue[j]);
            }
        }
        return  data;
    }

}
