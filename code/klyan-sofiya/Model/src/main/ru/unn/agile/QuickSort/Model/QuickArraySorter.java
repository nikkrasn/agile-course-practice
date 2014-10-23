package ru.unn.agile.QuickSort.Model;

public class QuickArraySorter {
    private double[] sortingValues;
  
    public QuickArraySorter(double[] values) {
        sortingValues = values;
    }    

    public void SortInInterval(final int inputLeftInd, final int inputRightInd) {
        int leftInd = (inputLeftInd >= 0) ? inputLeftInd : 0;
        int rightInd = (inputRightInd <= sortingValues.length - 1) ? inputRightInd : sortingValues.length - 1;
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
                SwapByInds(currLeftIndex, currRightIndex);
                currLeftIndex++;
                currRightIndex--;
            }
        }

        if (leftInd < currRightIndex) {
            SortInInterval(leftInd, currRightIndex);
        }
        if (currLeftIndex < rightInd) {
            SortInInterval(currLeftIndex, rightInd);
        }
    }

    public void SwapByInds(final int i, final int j) {
        if (i == j) {
            return;
        }
        if (CheckIfOutOfRange(i) || CheckIfOutOfRange(j)) {
            return;
        }

        double temp = sortingValues[i];
        sortingValues[i] = sortingValues[j];
        sortingValues[j] = temp;
    }

    private boolean CheckIfOutOfRange(final int index) {
        if ((index < 0) || (index >= sortingValues.length)) {
            System.out.println("Index is out of range: " + index);
            return true;
        }
        return false;
    }
}
