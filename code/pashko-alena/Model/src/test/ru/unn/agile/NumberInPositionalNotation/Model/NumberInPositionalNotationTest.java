package ru.unn.agile.NumberInPositionalNotation.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberInPositionalNotationTest {

    @Test
    public void canCreateNotationBinaryNumber() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("0", Notation.BINARY);
        assertNotNull(number);
    }

    @Test
    public void canCreateNullNumber() {
        NumberInPositionalNotation number = new NumberInPositionalNotation();
        assertEquals(new NumberInPositionalNotation("0", Notation.DECIMAL), number);
    }

    @Test
    public void canCreateWithoutNotation() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("10.Notation.BINARY");
        assertEquals(new NumberInPositionalNotation("10.Notation.BINARY", Notation.DECIMAL),
                number);
    }

    @Test
    public void canSetValue() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1", Notation.BINARY);
        assertEquals("1", number.getValue());
    }

    @Test
    public void canSetNotation() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1", Notation.BINARY);
        assertEquals(Notation.BINARY, number.getNotation());
    }

    @Test
    public void canConvertZeroBinaryToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("0", Notation.BINARY);
        Notation notation = Notation.DECIMAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("0.0", Notation.DECIMAL), out);
    }

    @Test
    public void canConvertNonZeroBinaryToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1", Notation.BINARY);
        Notation notation = Notation.DECIMAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("1.0", Notation.DECIMAL), out);
    }

    @Test
    public void canConvertAnotherBinaryToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("10", Notation.BINARY);
        Notation notation = Notation.DECIMAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("2.0", Notation.DECIMAL), out);
    }

    @Test
    public void canConvertNonIntegerBinaryToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1.1", Notation.BINARY);
        Notation notation = Notation.DECIMAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("1.5", Notation.DECIMAL), out);
    }

    @Test
    public void canConvertDecimalToBinary() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("0", Notation.DECIMAL);
        Notation notation = Notation.BINARY;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("0.0", Notation.BINARY), out);
    }

    @Test
    public void canConvertNonZeroDecimalToBinary() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1", Notation.DECIMAL);
        Notation notation = Notation.BINARY;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("1.0", Notation.BINARY), out);
    }

    @Test
    public void canConvertNonIntegerDecimalToBinary() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1.5", Notation.DECIMAL);
        Notation notation = Notation.BINARY;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("1.1", Notation.BINARY), out);
    }

    @Test
    public void canConvertOctalToDecimal() {
        NumberInPositionalNotation number = new NumberInPositionalNotation("1.1", Notation.OCTAL);
        Notation notation = Notation.DECIMAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("1.125", Notation.DECIMAL), out);
    }

    @Test
    public void canConvertHexadecimalToDecimal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("1.1", Notation.HEXADECIMAL);
        Notation notation = Notation.DECIMAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("1.0625", Notation.DECIMAL), out);
    }

    @Test
    public void canConvertDecimalToOctal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("1.5", Notation.DECIMAL);
        Notation notation = Notation.OCTAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("1.4", Notation.OCTAL), out);
    }

    @Test
    public void canConvertDecimalToHexadecimal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("2748.625", Notation.DECIMAL);
        Notation notation = Notation.HEXADECIMAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("abc.a", Notation.HEXADECIMAL), out);
    }

    @Test
    public void canConvertOctalToHexadecimal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("656.12", Notation.OCTAL);
        Notation notation = Notation.HEXADECIMAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("1ae.28", Notation.HEXADECIMAL), out);
    }

    @Test
    public void canConvertBinaryToHexadecimal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("1010.1", Notation.BINARY);
        Notation notation = Notation.HEXADECIMAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("a.8", Notation.HEXADECIMAL), out);
    }

    @Test
    public void canConvertBinaryToOctal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("1010.1", Notation.BINARY);
        Notation notation = Notation.OCTAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("12.4", Notation.OCTAL), out);
    }

    @Test
    public void canConvertHexadecimalToOctal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("ae.2", Notation.HEXADECIMAL);
        Notation notation = Notation.OCTAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("256.1", Notation.OCTAL), out);
    }

    @Test
    public void canConvertNonIntegerHexadecimalToOctal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("ae.2", Notation.HEXADECIMAL);
        Notation notation = Notation.BINARY;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("10101110.001", Notation.BINARY), out);
    }

    @Test
    public void canConvertNotationOctalToBinary() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("524.23", Notation.OCTAL);
        Notation notation = Notation.BINARY;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("101010100.010011", Notation.BINARY), out);
    }

    @Test
    public void canConvertBinaryToBinary() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("1010.001", Notation.BINARY);
        Notation notation = Notation.BINARY;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("1010.001", Notation.BINARY), out);
    }

    @Test
    public void canConvertWrongBinary() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("21.1", Notation.BINARY);
        Notation notation = Notation.DECIMAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("21.1", Notation.BINARY), out);
    }

    @Test
    public void canConvertWrongHexadecimal() {
        NumberInPositionalNotation number = new
                NumberInPositionalNotation("ag.1", Notation.HEXADECIMAL);
        Notation notation = Notation.DECIMAL;

        NumberInPositionalNotation out = number.convertToNotation(notation);

        assertEquals(new NumberInPositionalNotation("ag.1", Notation.HEXADECIMAL), out);
    }

    @Test
    public void canConvertBinaryToDecimal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("10.001", Notation.BINARY);

        NumberInPositionalNotation out = number.convertToDecimal();

        assertEquals(new NumberInPositionalNotation("2.125", Notation.DECIMAL), out);
    }

    @Test
    public void canConvertFromNotationHexadecimalToDEcimal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("abc.3", Notation.HEXADECIMAL);

        NumberInPositionalNotation out = number.convertToDecimal();

        assertEquals(new NumberInPositionalNotation("2748.1875", Notation.DECIMAL), out);
    }

    @Test
    public void canConvertNumberWithWrongValueToDecimal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("abc.3", Notation.OCTAL);

        NumberInPositionalNotation out = number.convertToDecimal();

        assertEquals(new NumberInPositionalNotation("abc.3", Notation.OCTAL), out);
    }

    @Test
    public void canConvertFromDecimalToOctal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("10.25", Notation.DECIMAL);
        Notation notation = Notation.OCTAL;

        NumberInPositionalNotation out = number.convertFromDecimal(notation);

        assertEquals(new NumberInPositionalNotation("12.2", Notation.OCTAL), out);
    }

    @Test
    public void canConvertFromDecimalToDecimal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("10.25", Notation.DECIMAL);
        Notation notation = Notation.DECIMAL;

        NumberInPositionalNotation out = number.convertFromDecimal(notation);

        assertEquals(new NumberInPositionalNotation("10.25", Notation.DECIMAL), out);
    }


    @Test
    public void canConvertNumberWithWrongValueFromDecimal() {
        NumberInPositionalNotation number =
                new NumberInPositionalNotation("a.25", Notation.DECIMAL);
        Notation notation = Notation.BINARY;

        NumberInPositionalNotation out = number.convertFromDecimal(notation);

        assertEquals(new NumberInPositionalNotation("a.25", Notation.DECIMAL), out);
    }
}
