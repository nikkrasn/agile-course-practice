package ru.unn.agile.QuickSort.Model;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class QuickArraySorterTest {
    private final double delta = 0;

    @Test
    public void canCreateQuickArraySorter() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        assertNotNull(sorter);
    }

    @Test
    public void canSwapNegativeFirstElement() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SwapByInds(-1, 2);
        assertArrayEquals(new double[]{3, -4, 5}, values, delta);
    }

    @Test
    public void canSwapNegativeSecondElement() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SwapByInds(2, -1);
        assertArrayEquals(new double[]{3, -4, 5}, values, delta);
    }

    @Test
    public void canSwapBothNegativeElements() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SwapByInds(-2, -1);
        assertArrayEquals(new double[]{3, -4, 5}, values, delta);
    }

    @Test
    public void canSwapOutOfRangeFirstElement() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SwapByInds(7, 2);
        assertArrayEquals(new double[]{3, -4, 5}, values, delta);
    }

    @Test
    public void canSwapOutOfRangeSecondElement() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SwapByInds(2, 7);
        assertArrayEquals(new double[]{3, -4, 5}, values, delta);
    }

    @Test
    public void canSwapOutOfRangeBothElements() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SwapByInds(5, 7);
        assertArrayEquals(new double[]{3, -4, 5}, values, delta);
    }

    @Test
    public void canSwapDifferentElements() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SwapByInds(0, 2);
        assertArrayEquals(new double[]{5, -4, 3}, values, delta);
    }

    @Test
    public void canSwapTheSameElements() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SwapByInds(1, 1);
        assertArrayEquals(new double[]{3, -4, 5}, values, delta);
    }

    @Test
    public void canSwapReverseOrderedElements() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SwapByInds(2, 0);
        assertArrayEquals(new double[]{5, -4, 3}, values, delta);
    }

    @Test
    public void canSortInIntervalWithNegativeLeftBorder() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SortInInterval(-1, values.length - 1);
        assertArrayEquals(new double[]{-4, 3, 5}, values, delta);
    }

    @Test
    public void canSortInIntervalWithOutOfRangeRightBorder() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SortInInterval(0, values.length + 1);
        assertArrayEquals(new double[]{-4, 3, 5}, values, delta);
    }

    @Test
    public void canSortInIntervalWithDerangedBorders() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SortInInterval(values.length - 1, 0);
        assertArrayEquals(new double[]{3, -4, 5}, values, delta);
    }

    @Test
    public void canSortInIntervalWithCoincidingBorders() {
        double[] values = {3, -4, 5};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SortInInterval(1, 1);
        assertArrayEquals(new double[]{3, -4, 5}, values, delta);
    }

    @Test
    public void canSortInIntervalWithInternalBorders() {
        double[] values = {3, -4, 5, -100, 200, 200, -350, 6.7, -2.3};
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SortInInterval(2, 6);
        assertArrayEquals(new double[]{3, -4, -350, -100, 5, 200, 200, 6.7, -2.3}, values, delta);
    }

    @Test
    public void canSortInFullLengthInterval() {
        double[] values = {3, -4, 5, -100, 200, 200, -350, 6.7, -2.3};
        QuickArraySorter sorter = new QuickArraySorter(values);
        double[] javaSortedValues = values.clone();
        sorter.SortInInterval(0, values.length - 1);
        java.util.Arrays.sort(javaSortedValues);
        assertArrayEquals(javaSortedValues, values, delta);
    }
}
