package ru.unn.agile.QuickSort.Model;

import org.junit.Test;
import ru.unn.agile.QuickSort.Model.QuickSorting;

import static org.junit.Assert.*;

public class QuickSortTest {
    private final double delta = 0;

    @Test
    public void canCreateQuickSort() {
        QuickSorting quickSort = new QuickSorting();
        assertNotNull(quickSort);
    }

    @Test
    public void canSortZeroElementArray() {
        QuickSorting quickSort = new QuickSorting();
        double[] values = {};
        quickSort.Sort(values);
        assertEquals(0, values.length);
    }

    @Test
    public void canSortNullArray() {
        QuickSorting quickSort = new QuickSorting();
        double[] values = null;
        quickSort.Sort(values);
        assertEquals(null, values);
    }

    @Test
    public void canSortOneElementArray() {
        QuickSorting quickSort = new QuickSorting();
        double[] values = {-4};
        quickSort.Sort(values);
        assertArrayEquals(new double[]{-4}, values, delta);
    }

    @Test
    public void canSortTwoElementArray() {
        QuickSorting quickSort = new QuickSorting();
        double[] values = {3, -4};
        quickSort.Sort(values);
        assertArrayEquals(new double[]{-4, 3}, values, delta);
    }

    @Test
    public void canSortArray() {
        QuickSorting quickSort = new QuickSorting();
        double[] values = {3, -4, 5, -100, 200, 200, -350, 6.7, -2.3};
        double[] javaSortedValues = values.clone();
        quickSort.Sort(values);
        java.util.Arrays.sort(javaSortedValues);
        assertArrayEquals(javaSortedValues, values, delta);
    }
}
