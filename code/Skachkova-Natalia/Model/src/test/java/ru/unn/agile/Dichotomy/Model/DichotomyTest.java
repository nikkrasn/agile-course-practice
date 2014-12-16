package ru.unn.agile.Dichotomy.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DichotomyTest {

    @Test
    public void canCreateDichotomy() {
        Dichotomy search = new Dichotomy();
        assertNotNull(search);
    }

    @Test
    public void canFindElementFromOne() {
        Dichotomy search = new Dichotomy();
        int[] array = new int[1];
        array[0] = 1;
        int result = search.dichotomySearch(array, 1);
        assertEquals(1, result);
    }

    @Test
    public void canFindElementFromLeftHalf() {
        Dichotomy search = new Dichotomy();
        int[] array = new int[5];
        array = createTestArray();
        int result = search.dichotomySearch(array, 3);
        assertEquals(3, result);
    }

    @Test
    public void canFindElementFromRightHalf() {
        Dichotomy search = new Dichotomy();
        int[] array = new int[10];
        array = createTestArray();
        int result = search.dichotomySearch(array, 8);
        assertEquals(8, result);
    }

    @Test
    public void canFindMissingElement() {
        Dichotomy search = new Dichotomy();
        int[] array = new int[10];
        array = createTestArray();
        int result = search.dichotomySearch(array, 12);
        assertEquals(-1, result);
    }

    @Test
    public void cantGetOutOfArrayElement() {
        Dichotomy search = new Dichotomy();
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = 10 - i;
        }
        boolean result = search.isArrayOrdered(array);
        assertEquals(false, result);
    }

    private int[] createTestArray() {
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = i + 1;
        }
        return array;
    }
}
