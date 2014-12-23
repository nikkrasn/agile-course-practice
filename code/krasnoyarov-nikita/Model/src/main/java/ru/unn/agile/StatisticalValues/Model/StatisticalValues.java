package ru.unn.agile.StatisticalValues.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StatisticalValues {
    public static final double EPSILON = 0.0001;

    private List<Double> values;
    private List<Double> probabilities;

    public StatisticalValues(final List<Double> values, final List<Double> probabilities) {
        this.values = values;
        this.probabilities = probabilities;
    }

    public List getValues() {
        return this.values;
    }

    public List getProbabilities() {
        return this.probabilities;
    }

    public void setProbabilities(final List<Double> probabilities) {
        this.probabilities = probabilities;
    }

    public void setValues(final List<Double> values) {
        this.values = values;
    }

    public double calculateExpectedValue() {
        double expectedValue = 0;
        Iterator valuesIterator = values.iterator();
        Iterator probabilitiesIterator = probabilities.iterator();

        checkProbabilities();

        while (valuesIterator.hasNext()) {
            expectedValue += (double) valuesIterator.next() * (double) probabilitiesIterator.next();
        }

        return expectedValue;
    }

    public double calculateVariance() {
        double variance = 0;
        Iterator valuesIterator = values.iterator();

        checkProbabilities();

        double expectedValue = calculateExpectedValue();
        double squaredExpectedValue = 0;
        List<Double> squaredVals = new ArrayList<>();

        while (valuesIterator.hasNext()) {
            squaredVals.add(Math.pow((double) valuesIterator.next(), 2));
        }

        StatisticalValues statisticalCalculator = new StatisticalValues(squaredVals, probabilities);
        squaredExpectedValue = statisticalCalculator.calculateExpectedValue();

        variance = squaredExpectedValue - Math.pow(expectedValue, 2);

        return variance;
    }

    public double calculateInitialMoment(final int k) {
        double moment = 0;
        Iterator valuesIterator = values.iterator();
        List<Double> momentVals = new ArrayList<>();

        checkProbabilities();

        while (valuesIterator.hasNext()) {
            momentVals.add(Math.pow((double) valuesIterator.next(), k));
        }

        StatisticalValues statisticalCalculator = new StatisticalValues(momentVals, probabilities);

        moment = statisticalCalculator.calculateExpectedValue();

        return moment;
    }

    public enum Operation {
        EXPECTED_VALUE("Expected value"){
            public Double apply(final StatisticalValues values) {
                return values.calculateExpectedValue();
            }
        },
        VARIANCE("Variance"){
            public Double apply(final StatisticalValues values) {
                return values.calculateVariance();
            }
        },
        INITIAL_MOMENT("Initial moment"){
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

    private boolean isSumProbabilitiesValueEqualsOne(final double sum) {
        return Math.abs(1.0 - sum) < EPSILON;
    }

    private boolean isNegativeProbabilityValue(final double probability) {
        return probability < 0;
    }

    private void checkSumProbabilitiesValue() {
        Iterator probabilitiesIterator = probabilities.iterator();
        double sum = 0;

        while (probabilitiesIterator.hasNext()) {
            sum += (double) probabilitiesIterator.next();
        }

        if (!isSumProbabilitiesValueEqualsOne(sum)) {
            throw new IllegalArgumentException("Sum of probabilities doesn't equal to 1");
        }
    }


    private void checkProbabilities() {
        checkDifferentSizeOfLists();
        checkProbabilitiesValueMoreThanOne();
        checkNegativeProbabilitiesValue();
        checkSumProbabilitiesValue();
    }

    private void checkDifferentSizeOfLists() {
        if (isDifferentSizeOfLists()) {
            throw new IllegalArgumentException("Size of lists doesn't match");
        }
    }

    private boolean isDifferentSizeOfLists() {
        return values.size() != probabilities.size();
    }

    private void checkProbabilitiesValueMoreThanOne() {
        Iterator probabilitiesIterator = probabilities.iterator();
        double probability = 0;

        while (probabilitiesIterator.hasNext()) {
            probability = (double) probabilitiesIterator.next();
            if (isProbabilityIncorrect(probability)) {
                throw new IllegalArgumentException("One of probabilities is more than 1");
            }
        }
    }

    private boolean isProbabilityIncorrect(final double probability) {
        return probability > 1.0;
    }

    private void checkNegativeProbabilitiesValue() {
        Iterator probabilitiesIterator = probabilities.iterator();
        double probability = 0;

        while (probabilitiesIterator.hasNext()) {
            probability = (double) probabilitiesIterator.next();
            if (isNegativeProbabilityValue(probability)) {
                throw new IllegalArgumentException("One of probabilities is negative");
            }
        }
    }
}


