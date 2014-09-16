package ru.unn.agile.tddkata;

public class StringCalculator {
    public int add(final String input) {
        if (input == "") {
            return 0;
        }
        String delimiter = extractDelimiter(input);
        String[] tokens = split(input, delimiter);
        return add(tokens);
    }

    private int add(final String[] tokens) {
        int sum = 0;
        for (String token: tokens) {
            sum += Integer.parseInt(token);
        }
        return sum;
    }

    private String[] split(final String input, final String delimiter) {
        String s = new String(input);

        if (s.startsWith("//")) {
            final int position = 4;
            s = s.substring(position);
        }

        return s.split(delimiter);
    }

    private String extractDelimiter(final String input) {
        String delimiter = "[,\n]";
        if (input.startsWith("//")) {
            final int position = 3;
            delimiter = input.substring(2, position);
        }
        return delimiter;
    }
}
