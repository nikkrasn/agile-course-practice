package ru.unn.agile.BitArray.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
}
