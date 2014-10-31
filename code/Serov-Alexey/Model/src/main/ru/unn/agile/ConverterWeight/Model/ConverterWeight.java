package ru.unn.agile.ConverterWeight.Model;

public class ConverterWeight {

private static final double GRAMM_AND_KILLOGRAM     = 1000;
private static final double GRAMM_AND_CENTNER       = 100000;
private static final double GRAMM_AND_TON           = 1000000;
private static final double KILLOGRAM_AND_CENTNER   = 100;
private static final double KILLOGRAM_AND_TON       = 1000;
private static final double CENTNER_AND_TON         = 10;

    public double grammToKilogram(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) / GRAMM_AND_KILLOGRAM;
        } else {
            return 0.0;
            }
    }

    public double grammToCentner(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) / GRAMM_AND_CENTNER;
        } else {
            return 0.0;
            }
    }

    public double grammToTon(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) / GRAMM_AND_TON;
        } else {
            return 0.0;
            }
    }

    public double kilogramToGram(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) * GRAMM_AND_KILLOGRAM;
        } else {
            return 0.0;
            }
    }

    public double kilogramToCentner(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) / KILLOGRAM_AND_CENTNER;
        } else {
            return 0.0;
            }
    }

    public double kilogramToTon(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) / KILLOGRAM_AND_TON;
        } else {
            return 0.0;
            }
    }

    public double centnerToGram(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) * GRAMM_AND_CENTNER;
        } else {
            return 0.0;
            }
    }

    public double centnerToKilogram(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) * KILLOGRAM_AND_CENTNER;
        } else {
            return 0.0;
            }
    }

    public double centnerToTon(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) / CENTNER_AND_TON;
        } else {
            return 0.0;
            }
    }

    public double tonToGram(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) * GRAMM_AND_TON;
        } else {
            return 0.0;
            }
    }

    public double tonToKilogram(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) * KILLOGRAM_AND_TON;
        } else {
            return 0.0;
            }
    }

    public double tonToCentner(final String input) {

        if (isNotEmpty(input) && isPositivNumber(input)) {
            return Double.parseDouble(input) * CENTNER_AND_TON;
        } else {
            return 0.0;
            }
    }

    private boolean isNotEmpty(final String input) {
        if (input == "") {
            return false;
        }
        return true;
    }

    private boolean isPositivNumber(final String input) {
        if (Double.parseDouble(input) > 0) {
            return true;
        }
        return false;
     }

}
