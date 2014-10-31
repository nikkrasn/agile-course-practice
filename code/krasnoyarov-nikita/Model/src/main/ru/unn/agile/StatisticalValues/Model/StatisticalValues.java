package ru.unn.agile.StatisticalValues.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StatisticalValues {
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

    public double calculateExpectedValue() throws Exception {
        double expectedValue = 0;
        Iterator valuesIterator = values.iterator();
        Iterator probabilitiesIterator = probabilities.iterator();

        checkProbabilities();

        while (valuesIterator.hasNext()) {
            expectedValue += (double) valuesIterator.next() * (double) probabilitiesIterator.next();
        }

        return expectedValue;
    }

    private void checkProbabilities() throws Exception {
        checkDifferentSizeOfLists();
        checkProbabilitiesValueMoreOne();
        checkNegativeProbabilitiesValue();
        checkSumProbabilitiesValue();
    }

    private void checkDifferentSizeOfLists() throws Exception {
        if (isDifferentSizeOfLists()) {
            throw new Exception("Size of values and probabilities lists doesn't match");
        }
    }

    private boolean isDifferentSizeOfLists() {
        return values.size() != probabilities.size();
    }

    private void checkProbabilitiesValueMoreOne() throws Exception {
        Iterator probabilitiesIterator = probabilities.iterator();
        double probability = 0;

        while (probabilitiesIterator.hasNext()) {
            probability = (double) probabilitiesIterator.next();
            if (isProbabilitiesCorrect(probability)) {
                throw new Exception("One of probabilities is more than 1");
            }
        }
    }

    private boolean isProbabilitiesCorrect(final double probability) {
        return probability > 1.0;
    }

    private void checkNegativeProbabilitiesValue() throws Exception {
        Iterator probabilitiesIterator = probabilities.iterator();
        double probability = 0;

        while (probabilitiesIterator.hasNext()) {
            probability = (double) probabilitiesIterator.next();
            if (isNegativeProbabilityValue(probability)) {
                throw new Exception("One of probabilities is negative");
            }
        }
    }

    private boolean isNegativeProbabilityValue(final double probability) {
        return probability < 0;
    }

    private void checkSumProbabilitiesValue() throws Exception {
        Iterator probabilitiesIterator = probabilities.iterator();
        double sum = 0;

        while (probabilitiesIterator.hasNext()) {
            sum += (double) probabilitiesIterator.next();
        }

        if (!isSumProbabilitiesValueEqualsOne(sum)) {
            throw new Exception("Sum of probabilities doesn't equal to 1");
        }
    }

    private boolean isSumProbabilitiesValueEqualsOne(final double sum) {
        return sum == 1.0;
    }

    public double calculateVariance() throws Exception {
        double variance = 0;
        Iterator valuesIterator = values.iterator();
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

    public double calculateInitialMoment(final int k) throws Exception {
        double moment = 0;
        Iterator valuesIterator = values.iterator();
        List<Double> momentVals = new ArrayList<>();

        while (valuesIterator.hasNext()) {
            momentVals.add(Math.pow((double) valuesIterator.next(), k));
        }

        StatisticalValues statisticalCalculator = new StatisticalValues(momentVals, probabilities);

        moment = statisticalCalculator.calculateExpectedValue();

        return moment;
    }
}
