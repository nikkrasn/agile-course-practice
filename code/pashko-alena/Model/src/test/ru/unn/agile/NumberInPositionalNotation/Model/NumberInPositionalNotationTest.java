package ru.unn.agile.NumberInPositionalNotation.Model;

/**
 * Created by Алена on 25.10.2014.
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class NumberInPositionalNotationTest {

    @Test
    public void canCreateBinaryNumber() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("0", 2);
        assertNotNull(number);
    }

    @Test
    public void canCreateNullNumber() {
        NumberInPositionalNotation number = new NumberInPositionalNotation();
        assertEquals(new NumberInPositionalNotation("0", 10), number);
    }

    @Test
    public void canCreateWithoutNotation() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("10.2");
        assertEquals(new NumberInPositionalNotation("10.2", 10), number);
    }

    @Test
    public void canSetValue() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1", 2);
        assertEquals("1", number.getValue());
    }

    @Test
    public void canSetNotation() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1", 2);
        assertEquals(2, number.getNotation());
    }

    @Test
    public void canConvertZeroBinaryToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("0", 2);
        int notation = 10;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("0.0", 10), out);
    }

    @Test
    public void canConvertNonZeroBinaryToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1", 2);
        int notation = 10;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("1.0", 10), out);
    }

    @Test
         public void canConvertAnotherBinaryToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("10", 2);
        int notation = 10;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("2.0", 10), out);
    }

    @Test
    public void canConvertNonIntegerBinaryToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1.1", 2);
        int notation = 10;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("1.5", 10), out);
    }

    @Test
    public void canConvertDecimalToBinary() {
        int notation = 2;
        NumberInPositionalNotation number = new NumberInPositionalNotation("0", 10);
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("0.0", 2), out);
    }

    @Test
    public void canConvertNonZeroDecimalToBinary() {
        int notation = 2;
        NumberInPositionalNotation number = new NumberInPositionalNotation("1", 10);
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("1.0", 2), out);
    }

    @Test
    public void canConvertNonIntegerDecimalToBinary() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1.5", 10);
        int notation = 2;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("1.1", 2), out);
    }

    @Test
    public void canConvertrOctalToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1.1", 8);
        int notation = 10;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("1.125", 10), out);
    }

    @Test
    public void canConvertHexadecimalToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1.1", 16);
        int notation = 10;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("1.0625", 10), out);
    }

    @Test
    public void canConvertDecimalToOctal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1.5", 10);
        int notation = 8;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("1.4", 8), out);
    }

    @Test
    public void canConvertDecimalToHexadecimal() {
       NumberInPositionalNotation number = new NumberInPositionalNotation("2748.625", 10);
        int notation = 16;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("abc.a", 16), out);
    }

    @Test
    public void canConvertOctalToHexadecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("656.12", 8);
        int notation = 16;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("1ae.28", 16), out);
    }

    @Test
    public void canConvertBinaryHexadecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1010.1", 2);
        int notation = 16;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("a.8", 16), out);
    }

    @Test
    public void canConvertBinaryToOctal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1010.1", 2);
        int notation = 8;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("12.4", 8), out);
    }

    @Test
    public void canConvertHexadecimalToOctal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("ae.2", 16);
        int notation = 8;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("256.1", 8), out);
    }

    @Test
    public void canConvertHexadecimalToBinary() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("ae.2", 16);
        int notation = 2;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("10101110.001", 2), out);
    }

    @Test
    public void canConvertOctalToBinary() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("524.23", 8);
        int notation = 2;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("101010100.010011", 2), out);
    }

    @Test
    public void canConvertBinaryToBinary() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1010.001", 2);
        int notation = 2;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("1010.001", 2), out);
    }

    @Test
    public void canConvertWrongBinary() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("21.1", 2);
        int notation = 10;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("21.1", 2), out);
    }

    @Test
    public void canConvertWrongHexadecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("ag.1", 16);
        int notation = 10;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("ag.1", 16), out);
    }

    @Test
    public void canConvertWrongNotation() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("ab.1", 15);
        int notation = 10;
        NumberInPositionalNotation out = number.convertToNotation(notation);
        assertEquals(new NumberInPositionalNotation("ab.1", 15), out);
    }

    @Test
    public void canConvertFromBinaryToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("10.001", 2);
        NumberInPositionalNotation out = number.convertToDecimal();
        assertEquals(new NumberInPositionalNotation("2.125", 10), out);
    }

    @Test
    public void canConvertFromHexadecimalToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("abc.3", 16);
        NumberInPositionalNotation out = number.convertToDecimal();
        assertEquals(new NumberInPositionalNotation("2748.1875", 10), out);
    }

    @Test
    public void canConvertFromWrongNotationToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("abc.3", -10);
        NumberInPositionalNotation out = number.convertToDecimal();
        assertEquals(new NumberInPositionalNotation("abc.3", -10), out);
    }

    @Test
    public void canConvertNumberWithWrongValueToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("abc.3", 8);
        NumberInPositionalNotation out = number.convertToDecimal();
        assertEquals(new NumberInPositionalNotation("abc.3", 8), out);
    }

    @Test
    public void canConvertFromDecimalToOctal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("10.25", 10);
        int notation = 8;
        NumberInPositionalNotation out = number.convertFromDecimal(notation);
        assertEquals(new NumberInPositionalNotation("12.2", 8), out);
    }

    @Test
    public void canConvertFromDecimalToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("10.25", 10);
        int notation = 10;
        NumberInPositionalNotation out = number.convertFromDecimal(notation);
        assertEquals(new NumberInPositionalNotation("10.25", 10), out);
    }

    @Test
    public void canConvertFromDecimalToWrongNotation() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("10.25", 10);
        int notation = 100;
        NumberInPositionalNotation out = number.convertFromDecimal(notation);
        assertEquals(new NumberInPositionalNotation("10.25", 10), out);
    }

    @Test
    public void canConvertNumberWithWrongValueFromDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("a.25", 10);
        int notation = 2;
        NumberInPositionalNotation out = number.convertFromDecimal(notation);
        assertEquals(new NumberInPositionalNotation("a.25", 10), out);
    }

}
