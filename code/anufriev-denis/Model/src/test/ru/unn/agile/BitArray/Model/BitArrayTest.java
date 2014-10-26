package ru.unn.agile.BitArray.Model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Anufr_000 on 26.10.2014.
 */
public class BitArrayTest {
    @Test
    public void canCreateBitArray() throws Exception {
        BitArray array = new BitArray(8);
        assertNotNull(array);
    }

    @Test
    public void canCreateBitArrayWithNegativeSize() throws Exception {
        String message = "";

        try {
            BitArray array = new BitArray(-5);
        }
        catch (Exception e){
            message = e.getMessage();
        }

        assertEquals("Not positive initial size", message);
    }

    @Test
    public void isCreatedBitArrayEmpty() throws Exception {
        BitArray array1 = new BitArray(8);
        BitArray array2 = new BitArray(8);
        array2.clearBit(0).clearBit(1).clearBit(2).clearBit(3).clearBit(4).clearBit(5).clearBit(6).clearBit(7);

        assertArrayEquals(array2.getArray(),array1.getArray());
    }

    @Test
    public void canSetBit() throws Exception {
        BitArray array = new BitArray(8);

        array.setBit(4);

        assertEquals(true, array.get(4));
    }

    @Test
    public void canSetLastBit() throws Exception {
        BitArray array = new BitArray(32);

        array.setBit(31);

        assertEquals(true, array.get(31));
    }

    @Test
    public void canSetOutOfArrayBit() throws Exception {
        BitArray array = new BitArray(30);
        String message = "";

        try {
            array.setBit(31);
        }
        catch (Exception e){
            message = e.getMessage();
        }

        assertEquals("Out of array", message);
    }

    @Test
    public void canClearBit() throws Exception {
        BitArray array = new BitArray(8);

        array.clearBit(4);

        assertEquals(false,array.get(4));
    }

    @Test
    public void canClearOutOfArrayBit() throws Exception {
        BitArray array = new BitArray(8);
        String message = "";

        try {
            array.clearBit(-1);
        }
        catch (Exception e){
            message = e.getMessage();
        }

        assertEquals("Out of array",message);
    }

    @Test
    public void canGetElement() throws Exception {
        BitArray array = new BitArray(8);

        boolean res = array.get(4);

        assertEquals(false,res);
    }

    @Test
    public void canGetTrueElement() throws Exception {
        BitArray array = new BitArray(8);
        array.setBit(4);

        boolean res = array.get(4);

        assertEquals(true, res);
    }

    @Test
    public void canGetOutOfArrayElement() throws Exception {
        BitArray array = new BitArray(8);
        String message = "";

        try {
            array.get(-8);
        }
        catch (Exception e){
            message = e.getMessage();
        }

        assertEquals("Out of array",message);
    }

    @Test
    public void canPerformXor() throws Exception {
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
    public void canPerformOr() throws Exception {
        BitArray array1 = new BitArray(8);
        BitArray array2 = new BitArray(8);
        BitArray array3 = new BitArray(8);
        array1.setBit(0).setBit(1).setBit(2).setBit(3);
        array2.setBit(0).setBit(4).setBit(5).setBit(6);
        array3.setBit(0).setBit(1).setBit(2).setBit(3).setBit(4).setBit(5).setBit(6);

        array1.or(array2);

        assertArrayEquals(array3.getArray(), array1.getArray());
    }

    @Test
    public void canPerformAnd() throws Exception {
        BitArray array1 = new BitArray(8);
        BitArray array2 = new BitArray(8);
        BitArray array3 = new BitArray(8);
        array1.setBit(0).setBit(1).setBit(2).setBit(3);
        array2.setBit(1).setBit(4).setBit(5).setBit(6);
        array3.setBit(1);

        array1.and(array2);

        assertArrayEquals(array3.getArray(), array1.getArray());
    }

    @Test
    public void canPerformHardAnd() throws Exception {
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
    public void canPerformNot() throws Exception {
        BitArray array1 = new BitArray(8);
        array1.setBit(0).setBit(1).setBit(2).setBit(3).setBit(4).setBit(5).setBit(6).setBit(7);

        array1.not();

        assertEquals(Integer.toBinaryString(0), Integer.toBinaryString(array1.getArray()[0]));
    }

    @Test
    public void canPerformNotNot() throws Exception {
        BitArray array1 = new BitArray(8);
        BitArray array2 = new BitArray(8);

        array1.not().not();

        assertArrayEquals(array2.getArray(), array1.getArray());
    }

    @Test
    public void canGetArray() throws Exception {
        BitArray array1 = new BitArray(8);
        array1.setBit(0).setBit(1).setBit(2).setBit(3);

        array1.getArray();

        assertEquals(15,array1.getArray()[0]);
    }

    @Test
    public void canPerformSuperpositionOfFunctions() throws Exception {
        BitArray array1 = new BitArray(4);
        BitArray array2 = new BitArray(4);
        BitArray array3 = new BitArray(4);
        array1.setBit(0).setBit(2);
        array2.setBit(0).setBit(1).setBit(2).setBit(3);
        array3.setBit(0).setBit(2);

        array1.not().xor(array2);

        assertArrayEquals(array3.getArray(),array1.getArray());
    }

    @Test
    public void canPerformSeveralSuperpositionsOfFunctions() throws Exception {
        BitArray array1 = new BitArray(4);
        BitArray array2 = new BitArray(4);
        BitArray array3 = new BitArray(4);
        array1.setBit(0).setBit(2);
        array2.setBit(0).setBit(1).setBit(2).setBit(3);
        array3.setBit(0).setBit(1).setBit(3);

        array1.xor(array2).or(array3).not().xor(array3);

        assertArrayEquals(array2.getArray(),array1.getArray());
    }
}
