package ru.unn.agile.tddkata;

public class StringCalculator {
    public int add(String input) {
        if (input == "") return 0;
        String delimiter = extractDelimiter(input);
        String[] tokens = split(input, delimiter);
        return add(tokens);
    }

    private int add(String[] tokens) {
        int sum = 0;
        for (String token: tokens) {
            sum += Integer.parseInt(token);
        }
        return sum;
    }

    private String[] split(String input, String delimiter) {
        if (input.startsWith("//")) {
            input = input.substring(4);
        }

        return input.split(delimiter);
    }

    private String extractDelimiter(String input) {
        String delimiter = "[,\n]";
        if (input.startsWith("//")) {
            delimiter = input.substring(2, 3);
        }
        return delimiter;
    }
}
