package ru.unn.agile.StatisticalValues.model;

public enum Operation {
    EXPECTED_VALUE("Expected value") {
        public Double apply(final StatisticalValues values) {
            return values.calculateExpectedValue();
        }
    },
    VARIANCE("Variance") {
        public Double apply(final StatisticalValues values) {
            return values.calculateVariance();
        }
    },
    INITIAL_MOMENT("Initial moment") {
        public Double apply(final StatisticalValues values) {
            return values.calculateInitialMoment(2);
        }
    };

    private final String name;

    Operation(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract Double apply(final StatisticalValues values);
}
