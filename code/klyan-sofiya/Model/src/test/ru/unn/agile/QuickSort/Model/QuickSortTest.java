package ru.unn.agile.QuickSort.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuickSortTest {
    private final double delta = 0;

    @Test
    public void canSortZeroElementArray() {
        double[] values = {};
        QuickSorting.sort(values);
        assertEquals(0, values.length);
    }

    @Test
    public void canSortNullArray() {
        double[] values = null;
        QuickSorting.sort(values);
        assertEquals(null, values);
    }

    @Test
    public void canSortOneElementArray() {
        double[] values = {-4};
        QuickSorting.sort(values);
        assertArrayEquals(new double[]{-4}, values, delta);
    }

    @Test
    public void canSortTwoElementArray() {
        double[] values = {3, -4};
        QuickSorting.sort(values);
        assertArrayEquals(new double[]{-4, 3}, values, delta);
    }

    @Test
    public void canSortArray() {
        double[] values = {3, -4, 5, -100, 200, 200, -350, 6.7, -2.3};
        double[] javaSortedValues = values.clone();
        QuickSorting.sort(values);
        java.util.Arrays.sort(javaSortedValues);
        assertArrayEquals(javaSortedValues, values, delta);
    }
}
