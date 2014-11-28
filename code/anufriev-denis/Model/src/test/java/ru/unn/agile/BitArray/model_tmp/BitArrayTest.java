package ru.unn.agile.BitArray.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitArrayTest {
    @Test
    public void canCreateBitArray() {
        BitArray array = new BitArray(8);
        assertNotNull(array);
    }

    @Test
    public void canCreateBigBitArray() {
        BitArray array = new BitArray(32000);
        assertNotNull(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantCreateBitArrayWithNegativeSize() {
        new BitArray(-5);
    }

    @Test
    public void isCreatedBitArrayEmpty() {
        BitArray array1 = new BitArray(5);
        BitArray array2 = new BitArray(5);
        array2.clearBit(0).clearBit(1).clearBit(2).clearBit(3).clearBit(4);

        assertArrayEquals(array2.getArray(), array1.getArray());
    }

    @Test
    public void canSetBit() {
        BitArray array = new BitArray(8);

        array.setBit(4);

        assertEquals(true, array.get(4));
    }

    @Test
      public void canSetLastBit() {
        BitArray array = new BitArray(32);

        array.setBit(31);

        assertEquals(true, array.get(31));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantSetOutOfArrayBit() {
        BitArray array = new BitArray(30);

        array.setBit(31);
    }

    @Test
    public void canSetBits() {
        BitArray array = new BitArray(6);
        String str = "111001";

        array.setBits(str.toCharArray());

        assertEquals("111001", array.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantSetOutOfArrayBits() {
        BitArray array = new BitArray(6);
        String str = "1110011";

        array.setBits(str.toCharArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantSetIncorrectBits() {
        BitArray array = new BitArray(6);
        String str = "abcdef";

        array.setBits(str.toCharArray());
    }

    @Test
    public void canFormatToString() {
        BitArray array = new BitArray(6);
        String str = "111001";
        array.setBits(str.toCharArray());

        String result = array.toString();

        assertEquals("111001", result);
    }

    @Test
    public void canFormatEmptyArrayToString() {
        BitArray array = new BitArray(6);

        String result = array.toString();

        assertEquals("000000", result);
    }

    @Test
    public void canClearBit() {
        BitArray array = new BitArray(8);

        array.clearBit(4);

        assertEquals(false, array.get(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantClearOutOfArrayBit() {
        BitArray array = new BitArray(8);

        array.clearBit(-1);
    }

    @Test
    public void canGetElement() {
        BitArray array = new BitArray(8);

        boolean res = array.get(4);

        assertEquals(false, res);
    }

    @Test
    public void canGetLastElementFromBigBitArray() {
        BitArray array = new BitArray(32000);

        boolean res = array.get(31999);

        assertEquals(false, res);
    }

    @Test
    public void canGetTrueElement() {
        BitArray array = new BitArray(8);
        array.setBit(4);

        boolean res = array.get(4);

        assertEquals(true, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantGetOutOfArrayElement() {
        BitArray array = new BitArray(8);

        array.get(-8);
    }

    @Test
    public void canPerformXor() {
        BitArray array1 = new BitArray(8);
        BitArray array2 = new BitArray(8);
        BitArray array3 = new BitArray(8);
        array1.setBit(0).setBit(1).setBit(2).setBit(3);
        array2.setBit(0).setBit(4).setBit(5).setBit(6);
        array3.setBit(1).setBit(2).setBit(3).setBit(4).setBit(5).setBit(6);

        array1.xor(array2);

        assertArrayEquals(array3.getArray(), array1.getArray());
    }

    @Test
    public void canPerformOr() {
        BitArray array1 = new BitArray(9);
        BitArray array2 = new BitArray(9);
        BitArray array3 = new BitArray(9);
        array1.setBit(1).setBit(2).setBit(3);
        array2.setBit(4).setBit(7).setBit(8);
        array3.setBit(1).setBit(2).setBit(3).setBit(4).setBit(7).setBit(8);

        array1.or(array2);

        assertArrayEquals(array3.getArray(), array1.getArray());
    }

    @Test
    public void canPerformAnd() {
        BitArray array1 = new BitArray(10);
        BitArray array2 = new BitArray(10);
        BitArray array3 = new BitArray(10);
        array1.setBit(0).setBit(1).setBit(2).setBit(3).setBit(9);
        array2.setBit(1).setBit(4).setBit(5).setBit(6).setBit(9);
        array3.setBit(1).setBit(9);

        array1.and(array2);

        assertArrayEquals(array3.getArray(), array1.getArray());
    }

    @Test
    public void canPerformHardAnd() {
        BitArray array1 = new BitArray(33);
        BitArray array2 = new BitArray(33);
        BitArray array3 = new BitArray(33);
        array1.setBit(0).setBit(1).setBit(2).setBit(3).setBit(32).setBit(30);
        array2.setBit(1).setBit(4).setBit(5).setBit(6).setBit(32).setBit(30);
        array3.setBit(1).setBit(30).setBit(32);

        array1.and(array2);

        assertArrayEquals(array3.getArray(), array1.getArray());
    }

    @Test
    public void canPerformNot() {
        BitArray array1 = new BitArray(8);
        array1.setBit(0).setBit(1).setBit(2).setBit(3).setBit(4).setBit(5).setBit(6).setBit(7);

        array1.not();

        assertEquals(Integer.toBinaryString(0), Integer.toBinaryString(array1.getArray()[0]));
    }

    @Test
    public void canPerformNotNot() {
        BitArray array1 = new BitArray(8);
        BitArray array2 = new BitArray(8);

        array1.not().not();

        assertArrayEquals(array2.getArray(), array1.getArray());
    }

    @Test
    public void canGetArray() {
        BitArray array1 = new BitArray(8);
        array1.setBit(0).setBit(1).setBit(2).setBit(3);

        array1.getArray();

        assertEquals(15, array1.getArray()[0]);
    }

    @Test
    public void canGetBigBitArray() {
        BitArray array1 = new BitArray(99999);
        array1.setBit(0).setBit(1).setBit(2).setBit(3);

        array1.getArray();

        assertEquals(15, array1.getArray()[0]);
    }

    @Test
    public void canPerformSuperpositionOfFunctions() {
        BitArray array1 = new BitArray(5);
        BitArray array2 = new BitArray(5);
        BitArray array3 = new BitArray(5);
        array1.setBit(0).setBit(2).setBit(4);
        array2.setBit(0).setBit(1).setBit(2).setBit(3).setBit(4);
        array3.setBit(0).setBit(2).setBit(4);

        array1.not().xor(array2);

        assertArrayEquals(array3.getArray(), array1.getArray());
    }

    @Test
    public void canPerformSuperpositionOfFunctionsOnBigBitArray() {
        BitArray array1 = new BitArray(32000);
        BitArray array2 = new BitArray(32000);
        BitArray array3 = new BitArray(32000);
        array1.setBit(12).setBit(14).setBit(31999);
        array2.setBit(12).setBit(13).setBit(14).setBit(15).setBit(31999);
        array3.setBit(13).setBit(15);

        array1.not().and(array2);

        assertArrayEquals(array3.getArray(), array1.getArray());
    }

    @Test
    public void canPerformSeveralSuperpositionsOfFunctions() {
        BitArray array1 = new BitArray(4);
        BitArray array2 = new BitArray(4);
        BitArray array3 = new BitArray(4);
        array1.setBit(0).setBit(2);
        array2.setBit(0).setBit(1).setBit(2).setBit(3);
        array3.setBit(0).setBit(1).setBit(3);

        array1.xor(array2).or(array3).not().xor(array3);

        assertArrayEquals(array2.getArray(), array1.getArray());
    }

    @Test
    public void canPerformSeveralSuperpositionsOfFunctionsOnBigBitArray() {
        BitArray array1 = new BitArray(99999);
        BitArray array2 = new BitArray(99999);
        BitArray array3 = new BitArray(99999);
        BitArray array4 = new BitArray(99999);
        array1.setBit(66).setBit(68).setBit(99998);
        array2.setBit(66).setBit(67).setBit(68).setBit(69).setBit(99998);
        array3.setBit(66).setBit(67).setBit(69).setBit(99998);
        array4.not();

        array1.xor(array2).or(array3).not().xor(array3);

        assertArrayEquals(array4.getArray(), array1.getArray());
    }
}
