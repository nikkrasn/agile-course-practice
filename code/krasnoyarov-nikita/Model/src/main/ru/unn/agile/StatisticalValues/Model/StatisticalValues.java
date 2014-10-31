package ru.unn.agile.StatisticalValues.Model;

import java.util.Iterator;
import java.util.List;

public class StatisticalValues {
    private final List values;
    private final List probabilities;

    public StatisticalValues(final List values, final List probabilities) {
        this.values = values;
        this.probabilities = probabilities;
    }

    public double calculateExpectedValue() throws Exception{
        double expectedValue = 0;
        Iterator valuesIterator = values.iterator();
        Iterator probabilitiesIterator = probabilities.iterator();

        checkDifferentSizeOfLists();
        checkProbabilitiesValueMoreOne();
        checkNegativeProbabilitiesValue();
        checkSumProbabilitiesValue();
            while (valuesIterator.hasNext()) {
                expectedValue += (double) valuesIterator.next() * (double) probabilitiesIterator.next();
            }

        return expectedValue;
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
            if (isProbabilitiesValueMoreOne(probability)) {
                throw new Exception("One of probabilities is more than 1");
            }
        }
    }

    private boolean isProbabilitiesValueMoreOne(double probability) {
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

    private boolean isNegativeProbabilityValue(double probability) {
        return probability < 0;
    }

    private void checkSumProbabilitiesValue() throws Exception {
        Iterator probabilitiesIterator = probabilities.iterator();
        double sum = 0;

        while (probabilitiesIterator.hasNext()) {
            sum += (double) probabilitiesIterator.next();
        }

        if (!isSumProbabilitiesValueEqualsOne(sum))
        {
            throw new Exception("Sum of probabilities doesn't equal to 1");
        }
    }

    private boolean isSumProbabilitiesValueEqualsOne(double sum) {
        return sum == 1.0;
    }
}
