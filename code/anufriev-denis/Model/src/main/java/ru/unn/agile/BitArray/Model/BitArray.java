package ru.unn.agile.BitArray.model;

public class BitArray {

    public static final int BIT_PER_INT = 32;
    public static final int BIT_MASK = 0x00000001;


    private final int size;
    private int[] array;

    public BitArray(final int size) {
        checkPositiveSizeOfArray(size);
        this.size = size;
        this.array = new int[(size + BIT_PER_INT - 1) / BIT_PER_INT];
    }

    public int[] getArray() {
        return this.array;
    }

    public BitArray setBit(final int index) {
        checkOutOfArray(index);
        int intArrayIndex = index / BIT_PER_INT;
        int position = index % BIT_PER_INT;

        int bitMask = BIT_MASK << position;
        this.array[intArrayIndex] = this.array[intArrayIndex] | bitMask;
        return this;
    }

    public BitArray clearBit(final int index) {
        checkOutOfArray(index);
        int intArrayIndex = index / BIT_PER_INT;
        int position = index % BIT_PER_INT;

        int bitMask = BIT_MASK << position;
        bitMask = ~bitMask;

        this.array[intArrayIndex] = this.array[intArrayIndex] & bitMask;
        return this;
    }

    public BitArray setBits(final char[] array) {
        checkOutOfArray(array.length - 1);
        checkStringCorrectness(array);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '1') {
                this.setBit(i);
            } else {
                this.clearBit(i);
            }
        }
        return this;
    }

    public boolean get(final int index) {
        checkOutOfArray(index);
        int intArrayIndex = index / BIT_PER_INT;
        int position = index % BIT_PER_INT;

        int bitMask = BIT_MASK << position;

        return (this.array[intArrayIndex] & bitMask) != 0;
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

    public String toString() {
        String result = "";
        for (int i = 0; i < this.size; i++) {
            if (this.get(i)) {
                result += "1";
            } else {
                result += "0";
            }
        }
        return result;
    }

    public enum Operation {
        AND("And") {
            public BitArray apply(final BitArray l, final BitArray r) {
                return l.and(r);
            }
        },
        OR("Or") {
            public BitArray apply(final BitArray l, final BitArray r) {
                return l.or(r);
            }
        },
        XOR("Xor") {
            public BitArray apply(final BitArray l, final BitArray r) {
                return l.xor(r);
            }
        };

        private final String name;
        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract BitArray apply(final BitArray l, final BitArray r);
    }

    private void checkOutOfArray(final int index) {
        if (index >= this.size || index < 0) {
            throw new IllegalArgumentException("Out of array");
        }
    }

    private void checkStringCorrectness(final char[] array) {
        String s = String.copyValueOf(array);
        if (!s.matches("(0|1)*")) {
            throw new IllegalArgumentException("String should consist of following characters:0,1");
        }
    }

    private void checkPositiveSizeOfArray(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Not positive initial size");
        }
    }

    private int getBitMask(final int size) {
        int position = size % BIT_PER_INT;
        return (BIT_MASK << position) - 1;
    }
}
