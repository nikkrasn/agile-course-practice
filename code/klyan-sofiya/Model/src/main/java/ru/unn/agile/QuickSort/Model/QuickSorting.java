package ru.unn.agile.QuickSort.Model;

public final class QuickSorting {

    private QuickSorting() { }
    public static void sort(final double[] values) {
        if (values == null || values.length == 0) {
            return;
        }
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.sortInInterval(0, values.length - 1);
    }
}
