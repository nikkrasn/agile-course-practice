package ru.unn.agile.QuickSort.Model;

public final class QuickSorting {

    public QuickSorting() {}
    public void Sort(double[] values) {
        if (values == null || values.length == 0) {
            return;
        }
        QuickArraySorter sorter = new QuickArraySorter(values);
        sorter.SortInInterval(0, values.length - 1);
    }
}
