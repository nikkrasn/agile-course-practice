package ru.unn.agile.BitArray.Model;

/**
 * Created by Anufr_000 on 26.10.2014.
 */
public class BitArray {

    public static final int BIT_PER_INT = 32;
    public static final int BIT_MASK = 0x00000001;


    private int size;
    private int[] array;

    public BitArray(int size) throws Exception {
        checkPositiveSizeOfArray(size);
        this.size=size;
        this.array = new int[(size+BIT_PER_INT-1)/BIT_PER_INT];
    }

    public void setBit(int index) throws Exception {
        checkOutOfArray(index);
        int intIndex=index/BIT_PER_INT;
        index%=BIT_PER_INT;

        int bitMask = BIT_MASK << index;
        this.array[intIndex] = this.array[intIndex] | bitMask;
    }

    public void clearBit(int index) throws Exception {
        checkOutOfArray(index);
        int intIndex=index/BIT_PER_INT;
        index%=BIT_PER_INT;

        int bitMask = BIT_MASK << index;
        bitMask = ~bitMask;

        this.array[intIndex] = this.array[intIndex] & bitMask;
    }

    public boolean get(int index) throws Exception {
        checkOutOfArray(index);
        int intIndex=index/BIT_PER_INT;
        index%=BIT_PER_INT;

        int bitMask = BIT_MASK << index;

        return (this.array[intIndex] & bitMask)==0 ? false : true;
    }

    private void checkOutOfArray(int index) throws Exception {
        if (index >= this.size || index < 0) {
            throw new Exception("Out of array");
        }
    }

    private void checkPositiveSizeOfArray(int size) throws Exception {
        if (size <= 0) {
            throw new Exception("Not positive initial size");
        }
    }

}
