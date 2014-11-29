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
        assertNotNull(viewModel.heapsProperty());
        assertEquals("", viewModel.keyProperty().get());
        assertEquals("", viewModel.valueProperty().get());
        assertEquals("", viewModel.newKeyProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals("", viewModel.statusProperty().get());
    }

    @Test
    public void canAddElement() {
        viewModel.keyProperty().set("1");
        viewModel.add();
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canValidateBeforeAddElement() {
        viewModel.add();
        assertEquals("Bad input", viewModel.statusProperty().get());
    }

    @Test
    public void canExtractByKey() {
        viewModel.keyProperty().set("1");
        viewModel.valueProperty().set("value");
        viewModel.add();

        viewModel.delete();

        assertEquals("Key: 1, Value: value", viewModel.resultProperty().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canValidateBeforeExtractByKey() {
        viewModel.keyProperty().set("1");
        viewModel.valueProperty().set("value");
        viewModel.add();
        viewModel.keyProperty().set("abra-kadabra");

        viewModel.delete();

        assertEquals("", viewModel.resultProperty().get());
        assertEquals("Bad input", viewModel.statusProperty().get());
    }

    @Test
    public void canCorrectExtractByNotContainedKey() {
        viewModel.keyProperty().set("3");
        viewModel.valueProperty().set("value");
        viewModel.add();
        viewModel.keyProperty().set("7");

        viewModel.delete();

        assertEquals("Heap not contain elements with key '7'", viewModel.resultProperty().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canGetMinimum() {
        viewModel.keyProperty().set("1");
        viewModel.valueProperty().set("value");
        viewModel.add();

        viewModel.getMinimum();

        assertEquals("Key: 1, Value: value", viewModel.resultProperty().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canGetMinimumFromEmptyHeap() {
        viewModel.getMinimum();

        assertEquals("Heap is empty", viewModel.resultProperty().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canCorrectGetMinimumFromEmptyHeap() {
        viewModel.getMinimum();

        assertEquals("Heap is empty", viewModel.resultProperty().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canMerge() {
        viewModel.merge();

        assertEquals("Merged", viewModel.resultProperty().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canDecreaseKey() {
        viewModel.keyProperty().set("5");
        viewModel.valueProperty().set("value");
        viewModel.add();

        viewModel.newKeyProperty().set("2");

        viewModel.decreaseKey();

        assertEquals("Key decreased", viewModel.resultProperty().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canNotDecreaseNotExistKey() {
        viewModel.keyProperty().set("3");
        viewModel.valueProperty().set("value");
        viewModel.add();

        viewModel.keyProperty().set("4");
        viewModel.newKeyProperty().set("2");

        viewModel.decreaseKey();

        assertEquals("Key not found", viewModel.resultProperty().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canNotDecreaseToBiggerKey() {
        viewModel.keyProperty().set("2");
        viewModel.valueProperty().set("value");
        viewModel.add();

        viewModel.newKeyProperty().set("6");

        viewModel.decreaseKey();

        assertEquals("New key must be less than current", viewModel.resultProperty().get());
        assertEquals("OK", viewModel.statusProperty().get());
    }

    @Test
    public void canValidateNewKeyInputBeforDecrease() {
        viewModel.keyProperty().set("1");
        viewModel.newKeyProperty().set("words");

        viewModel.decreaseKey();

        assertEquals("", viewModel.resultProperty().get());
        assertEquals("Bad input", viewModel.statusProperty().get());
    }

    @Test
    public void canValidateKeyInputBeforDecrease() {
        viewModel.keyProperty().set("this key");
        viewModel.newKeyProperty().set("4");

        viewModel.decreaseKey();

        assertEquals("", viewModel.resultProperty().get());
        assertEquals("Bad input", viewModel.statusProperty().get());
    }
}
