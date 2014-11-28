package ru.unn.agile.tddkata;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;

public class WhenCalculateString {
    private StringCalculator stringCalculator;

    @Before
    public void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void emptyStringGivesZero() {
        assertAddReturns(0, "");
    }

    @Test
    public void numberGivesSameNumber() {
        assertAddReturns(2112, "2112");
    }

    @Test
    public void twoNumbersGivesSum() {
        assertAddReturns(2 + 3, "2,3");
    }

    @Test
    public void twoBigNumbersGivesSum() {
        assertAddReturns(21 + 35, "21,35");
    }

    @Test
    public void anyAmountOfNumbersGivesSum() {
        assertAddReturns(1 + 2 + 3 + 4 + 5, "1,2,3,4,5");
    }

    @Test
    public void canSeparateNumberWithNewLine() {
        assertAddReturns(1 + 2 + 3, "1\n2,3");
    }

    @Test
    public void canDefineCustomDelimiter() {
        assertAddReturns(1 + 2 + 3, "//;\n1;2;3");
    }

//    @Test
//    public void negativeNumbersAreNotAllowed() {
//         try {
//             stringCalculator.add("-1,2,-3");
//             fail("Runtime exception was expected");
//         } catch (RuntimeException e) {
//             assertEquals("Negatives -1, -3 are not allowed.", e.getMessage());
//         } catch (Exception e) {
//             fail("Unexpected exception: " + e.toString());
//         }
//    }

    private void assertAddReturns(final int expected, final String input) {
        assertEquals(expected, stringCalculator.add(input));
    }
}
