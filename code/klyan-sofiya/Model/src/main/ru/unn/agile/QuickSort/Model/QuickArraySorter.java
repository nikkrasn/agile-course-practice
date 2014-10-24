package ru.unn.agile.QuickSort.Model;

public class QuickArraySorter {
    private double[] sortingValues;

    public QuickArraySorter(final double[] values) {
        sortingValues = values;
    }

    public void sortInInterval(final int inputLeftInd, final int inputRightInd) {
        int leftInd = (inputLeftInd >= 0) ? inputLeftInd : 0;
        int rightInd = inputRightInd;
        if (rightInd > sortingValues.length - 1) {
            rightInd = sortingValues.length - 1;
        }
        if (leftInd >= rightInd) {
            return;
        }

        int currLeftIndex = leftInd;
        int currRightIndex = rightInd;
        final double pivot = sortingValues[currLeftIndex + (currRightIndex - currLeftIndex) / 2];

        while (currLeftIndex <= currRightIndex) {
            while (sortingValues[currLeftIndex] < pivot) {
                currLeftIndex++;
            }
            while (sortingValues[currRightIndex] > pivot) {
                currRightIndex--;
            }
            if (currLeftIndex <= currRightIndex) {
                swapByInds(currLeftIndex, currRightIndex);
                currLeftIndex++;
                currRightIndex--;
            }
        }

        if (leftInd < currRightIndex) {
            sortInInterval(leftInd, currRightIndex);
        }
        if (currLeftIndex < rightInd) {
            sortInInterval(currLeftIndex, rightInd);
        }
    }

    public void swapByInds(final int i, final int j) {
        if (i == j) {
            return;
        }
        if (checkIfOutOfRange(i) || checkIfOutOfRange(j)) {
            return;
        }

        double temp = sortingValues[i];
        sortingValues[i] = sortingValues[j];
        sortingValues[j] = temp;
    }

    private boolean checkIfOutOfRange(final int index) {
        if ((index < 0) || (index >= sortingValues.length)) {
            System.out.println("Index is out of range: " + index);
            return true;
        }
        return false;
    }
}
