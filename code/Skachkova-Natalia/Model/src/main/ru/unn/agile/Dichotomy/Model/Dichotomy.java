package ru.unn.agile.Dichotomy.Model;

public class Dichotomy {

    private void isArrayOrdered(final int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                throw new IllegalArgumentException("Out of array");
            }
        }
    }

    public int dichotomySearch(final int[] array, final int element) {
        isArrayOrdered(array);
        int length = array.length;
        int[] tempArray = new int[length];
        for (int i = 0; i < length; i++) {
            tempArray[i] = array [i];
        }
        while (length != 1) {
            if (element <= tempArray[length / 2 - 1]) {
                int temp = 0;
                for (int i = 0; i < length / 2; i++) {
                    temp++;
                }
                length = temp;
            } else {
                int temp = 0;
                for (int i = length / 2; i < length; i++) {
                    tempArray[i - length / 2] = tempArray[i];
                    temp++;
                }
                length = temp;
            }
        }
        if (tempArray[0] == element) {
            return tempArray[0];
        } else {
            return -1;
        }
    }
}
