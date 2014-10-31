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

        checkSumProbabilitiesValue();
            while (valuesIterator.hasNext()) {
                expectedValue += (double) valuesIterator.next() * (double) probabilitiesIterator.next();
            }

        return expectedValue;
    }

    private void checkSumProbabilitiesValue() throws Exception{
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
        return sum == 1 ? true : false;
    }
}
