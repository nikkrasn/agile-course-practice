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

    public double calculateExpectedValue() {
        double expectedValue = 0;
        Iterator valuesIterator = values.iterator();
        Iterator probabilitiesIterator = probabilities.iterator();

        while (valuesIterator.hasNext()) {
            expectedValue += (double) valuesIterator.next() * (double) probabilitiesIterator.next();
        }
        return expectedValue;
    }
}
