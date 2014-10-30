package ru.unn.agile.BitArray.Model;

/**
 * Created by Anufr_000 on 26.10.2014.
 */
public class BitArray {

    public static final int BIT_PER_INT = 32;
    public static final int BIT_MASK = 0x00000001;


    private final int size;
    private int[] array;

    public BitArray(final int size) throws Exception {
        checkPositiveSizeOfArray(size);
        this.size = size;
        this.array = new int[(size + BIT_PER_INT - 1) / BIT_PER_INT];
    }

    public int[] getArray() {
        return this.array;
    }

    public BitArray setBit(final int index) throws Exception {
        checkOutOfArray(index);
        int intArrayIndex = index / BIT_PER_INT;
        int position = index % BIT_PER_INT;

        int bitMask = BIT_MASK << position;
        this.array[intArrayIndex] = this.array[intArrayIndex] | bitMask;
        return this;
    }

    public BitArray clearBit(final int index) throws Exception {
        checkOutOfArray(index);
        int intArrayIndex = index / BIT_PER_INT;
        int position = index % BIT_PER_INT;

        int bitMask = BIT_MASK << position;
        bitMask = ~bitMask;

        this.array[intArrayIndex] = this.array[intArrayIndex] & bitMask;
        return this;
    }

    public boolean get(final int index) throws Exception {
        checkOutOfArray(index);
        int intArrayIndex = index / BIT_PER_INT;
        int position = index % BIT_PER_INT;

        int bitMask = BIT_MASK << position;

        return (this.array[intArrayIndex] & bitMask) == 0 ? false : true;
    }

    public BitArray xor(final BitArray array) {
        for (int i = 0; i < (size + BIT_PER_INT - 1) / BIT_PER_INT; i++) {
            this.array[i] = this.array[i] ^ array.getArray()[i];
        }
        return this;
    }

    public BitArray or(final BitArray array) {
        for (int i = 0; i < (size + BIT_PER_INT - 1) / BIT_PER_INT; i++) {
            this.array[i] = this.array[i] | array.getArray()[i];
        }
        return this;
    }

    public BitArray and(final BitArray array) {
        for (int i = 0; i < (size + BIT_PER_INT - 1) / BIT_PER_INT; i++) {
            this.array[i] = this.array[i] & array.getArray()[i];
        }
        return this;
    }

    public BitArray not() {
        for (int i = 0; i < (this.size + BIT_PER_INT - 1) / BIT_PER_INT; i++) {
            this.array[i] = ~this.array[i];
        }
        int bitMask = getBitMask(this.size);
        this.array[(size + BIT_PER_INT - 1) / BIT_PER_INT - 1] &= bitMask;
        return this;
    }

    private void checkOutOfArray(final int index) throws Exception {
        if (index >= this.size || index < 0) {
            throw new Exception("Out of array");
        }
    }

    private void checkPositiveSizeOfArray(final int size) throws Exception {
        if (size <= 0) {
            throw new Exception("Not positive initial size");
        }
    }

    private int getBitMask(final int size) {
        int position = size % BIT_PER_INT;
        return (BIT_MASK << position) - 1;
    }
}
