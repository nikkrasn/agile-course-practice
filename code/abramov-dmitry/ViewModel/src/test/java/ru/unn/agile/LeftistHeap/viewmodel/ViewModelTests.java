package ru.unn.agile.LeftistHeap.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.key1Property().get());
        assertEquals("", viewModel.key2Property().get());
        assertEquals("", viewModel.value1Property().get());
        assertEquals("", viewModel.value2Property().get());
        assertEquals("", viewModel.newKey1Property().get());
        assertEquals("", viewModel.newKey2Property().get());
        assertEquals("", viewModel.result1Property().get());
        assertEquals("", viewModel.result2Property().get());
        assertEquals("", viewModel.statusProperty().get());
    }

    @Test
    public void canAddElement() {
        viewModel.key1Property().set("1");
        viewModel.add1();
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canValidateBeforeAddElement() {
        viewModel.add1();
        assertEquals("Bad input", viewModel.statusProperty().get());
    }

    @Test
    public void canExtractByKey() {
        viewModel.key1Property().set("1");
        viewModel.value1Property().set("value");
        viewModel.add1();

        viewModel.delete1();

        assertEquals("Key: 1, Value: value", viewModel.result1Property().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canValidateBeforeExtractByKey() {
        viewModel.key1Property().set("1");
        viewModel.value1Property().set("value");
        viewModel.add1();
        viewModel.key1Property().set("abra-kadabra");

        viewModel.delete1();

        assertEquals("", viewModel.result1Property().get());
        assertEquals("Bad input", viewModel.statusProperty().get());
    }

    @Test
    public void canCorrectExtractByNotContainedKey() {
        viewModel.key1Property().set("3");
        viewModel.value1Property().set("value");
        viewModel.add1();
        viewModel.key1Property().set("7");

        viewModel.delete1();

        assertEquals("Heap not contain elements with key '7'", viewModel.result1Property().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canGetMinimum() {
        viewModel.key1Property().set("1");
        viewModel.value1Property().set("value");
        viewModel.add1();

        viewModel.getMinimum1();

        assertEquals("Key: 1, Value: value", viewModel.result1Property().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canGetMinimumFromEmptyHeap() {
        viewModel.getMinimum1();

        assertEquals("Heap is empty", viewModel.result1Property().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canCorrectGetMinimumFromEmptyHeap() {
        viewModel.getMinimum1();

        assertEquals("Heap is empty", viewModel.result1Property().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canMerge() {
        viewModel.merge1();

        assertEquals("Merged with left heap", viewModel.result2Property().get());
        assertEquals("Heap is empty", viewModel.result1Property().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canDecreaseKey() {
        viewModel.key1Property().set("5");
        viewModel.value1Property().set("value");
        viewModel.add1();

        viewModel.newKey1Property().set("2");

        viewModel.decreaseKey1();

        assertEquals("Key decreased", viewModel.result1Property().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canNotDecreaseNotExistKey() {
        viewModel.key1Property().set("3");
        viewModel.value1Property().set("value");
        viewModel.add1();

        viewModel.key1Property().set("4");
        viewModel.newKey1Property().set("2");

        viewModel.decreaseKey1();

        assertEquals("Key not found", viewModel.result1Property().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canNotDecreaseToBiggerKey() {
        viewModel.key1Property().set("2");
        viewModel.value1Property().set("value");
        viewModel.add1();

        viewModel.newKey1Property().set("6");

        viewModel.decreaseKey1();

        assertEquals("New key must be less than current", viewModel.result1Property().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canValidateNewKeyInputBeforDecrease() {
        viewModel.key1Property().set("1");
        viewModel.newKey1Property().set("words");

        viewModel.decreaseKey1();

        assertEquals("", viewModel.result1Property().get());
        assertEquals("Bad input", viewModel.statusProperty().get());
    }

    @Test
    public void canValidateKeyInputBeforDecrease() {
        viewModel.key1Property().set("this key");
        viewModel.newKey1Property().set("4");

        viewModel.decreaseKey1();

        assertEquals("", viewModel.result1Property().get());
        assertEquals("Bad input", viewModel.statusProperty().get());
    }
}
