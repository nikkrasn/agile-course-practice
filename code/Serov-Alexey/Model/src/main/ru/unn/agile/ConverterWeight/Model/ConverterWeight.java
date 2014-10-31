package ru.unn.agile.ConverterWeight.Model;

public class ConverterWeight {

private static final double GRAMM_AND_KILLOGRAM     = 1000;
private static final double GRAMM_AND_CENTNER       = 100000;
private static final double GRAMM_AND_TON           = 1000000;
private static final double KILLOGRAM_AND_CENTNER   = 100;
private static final double KILLOGRAM_AND_TON       = 1000;
private static final double CENTNER_AND_TON         = 10;

    public double grammToKilogram(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * GRAMM_AND_KILLOGRAM <= Double.MAX_VALUE) {
            return forTrans / GRAMM_AND_KILLOGRAM;
        } else {
            throw new IllegalArgumentException();
            }
    }

    public double grammToCentner(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * GRAMM_AND_CENTNER <= Double.MAX_VALUE) {
            return forTrans / GRAMM_AND_CENTNER;
        } else {
            throw new IllegalArgumentException();
            }
    }

    public double grammToTon(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * GRAMM_AND_TON <= Double.MAX_VALUE) {
            return forTrans / GRAMM_AND_TON;
        } else {
            throw new IllegalArgumentException();
            }
    }

    public double kilogramToGram(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * GRAMM_AND_KILLOGRAM <= Double.MAX_VALUE) {
            return forTrans * GRAMM_AND_KILLOGRAM;
        } else {
            throw new IllegalArgumentException();
            }
    }

    public double kilogramToCentner(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * KILLOGRAM_AND_CENTNER <= Double.MAX_VALUE) {
            return forTrans / KILLOGRAM_AND_CENTNER;
        } else {
            throw new IllegalArgumentException();
            }
    }

    public double kilogramToTon(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * KILLOGRAM_AND_TON <= Double.MAX_VALUE) {
            return forTrans / KILLOGRAM_AND_TON;
        } else {
            throw new IllegalArgumentException();
            }
    }

    public double centnerToGram(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * GRAMM_AND_CENTNER <= Double.MAX_VALUE) {
            return forTrans * GRAMM_AND_CENTNER;
        } else {
            throw new IllegalArgumentException();
            }
    }

    public double centnerToKilogram(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * KILLOGRAM_AND_CENTNER <= Double.MAX_VALUE) {
            return forTrans * KILLOGRAM_AND_CENTNER;
        } else {
            throw new IllegalArgumentException();
            }
    }

    public double centnerToTon(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * CENTNER_AND_TON <= Double.MAX_VALUE) {
            return forTrans / CENTNER_AND_TON;
        } else {
            throw new IllegalArgumentException();
            }
    }

    public double tonToGram(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * GRAMM_AND_TON <= Double.MAX_VALUE) {
            return forTrans * GRAMM_AND_TON;
        } else {
            throw new IllegalArgumentException();
            }
    }

    public double tonToKilogram(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * KILLOGRAM_AND_TON <= Double.MAX_VALUE) {
            return forTrans * KILLOGRAM_AND_TON;
        } else {
            throw new IllegalArgumentException();
            }
    }

    public double tonToCentner(final String input) {
        double forTrans = Double.parseDouble(input);
        if (isToTranslate(input) && forTrans * CENTNER_AND_TON <= Double.MAX_VALUE) {
            return forTrans * CENTNER_AND_TON;
        } else {
            throw new IllegalArgumentException();
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

    private boolean isToTranslate(final String input) {
        if (isNotEmpty(input) && isPositivNumber(input)) {
            return true;
        }
            return false;

    }

}
