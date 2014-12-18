package ru.unn.agile.Dichotomy.Model;

public class Dichotomy {

    public boolean isArrayOrdered(final int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public int dichotomySearch(final int[] array, final int element) {
        int length = array.length;
        int[] tempArray = new int[length];
        for (int i = 0; i < length; i++) {
            tempArray[i] = array [i];
        }
        while (length != 1) {
            if (element <= tempArray[length / 2 - 1]) {
                int tempLength = 0;
                for (int i = 0; i < length / 2; i++) {
                    tempLength++;
                }
                length = tempLength;
            } else {
                int tempLength = 0;
                for (int i = length / 2; i < length; i++) {
                    tempArray[i - length / 2] = tempArray[i];
                    tempLength++;
                }
                length = tempLength;
            }
        }
        if (tempArray[0] == element) {
            return tempArray[0];
        } else {
            return -1;
        }
    }
}
