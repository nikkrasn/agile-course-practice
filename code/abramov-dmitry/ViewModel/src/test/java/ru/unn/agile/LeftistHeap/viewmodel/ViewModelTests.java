package ru.unn.agile.LeftistHeap.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.LeftistHeap.Model.LeftistHeap;

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
        assertNotNull(viewModel.heapProperty());
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

        assertEquals("New element added to heap 'heap 1'", viewModel.resultProperty().get());
        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canValidateBeforeAddElement() {
        viewModel.add();
        assertEquals(Status.BAD_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canExtractByKey() {
        viewModel.keyProperty().set("1");
        viewModel.valueProperty().set("value");
        viewModel.add();

        viewModel.extract();

        assertEquals("Key: 1, Value: value", viewModel.resultProperty().get());
        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canValidateBeforeExtractByKey() {
        viewModel.keyProperty().set("1");
        viewModel.valueProperty().set("value");
        viewModel.add();
        viewModel.keyProperty().set("abra-kadabra");

        viewModel.extract();

        assertEquals(
                "Any element was not deleted",
                viewModel.resultProperty().get());
        assertEquals(Status.BAD_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canCorrectExtractByNotContainedKey() {
        viewModel.keyProperty().set("3");
        viewModel.valueProperty().set("value");
        viewModel.add();
        viewModel.keyProperty().set("7");

        viewModel.extract();

        assertEquals(
                "Heap 'heap 1' not contain elements with key '7'",
                viewModel.resultProperty().get());
        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canGetMinimum() {
        viewModel.keyProperty().set("2");
        viewModel.valueProperty().set("big value");
        viewModel.add();

        viewModel.extractMinimum();

        assertEquals("Key: 2, Value: big value", viewModel.resultProperty().get());
        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canGetMinimumFromEmptyHeap() {
        viewModel.extractMinimum();

        assertEquals("Heap 'heap 1' is empty", viewModel.resultProperty().get());
        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canMerge() {
        viewModel.merge();

        assertEquals("Merged heap 'heap 2' with heap 'heap 1'", viewModel.resultProperty().get());
        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canDecreaseKey() {
        viewModel.keyProperty().set("5");
        viewModel.valueProperty().set("value");
        viewModel.add();

        viewModel.newKeyProperty().set("2");

        viewModel.decreaseKey();

        assertEquals("Key decreased", viewModel.resultProperty().get());
        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canNotDecreaseNotExistKey() {
        viewModel.keyProperty().set("3");
        viewModel.valueProperty().set("value");
        viewModel.add();

        viewModel.keyProperty().set("4");
        viewModel.newKeyProperty().set("2");

        viewModel.decreaseKey();

        assertEquals("Key '4' not found in heap 'heap 1'", viewModel.resultProperty().get());
        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canNotDecreaseToBiggerKey() {
        viewModel.keyProperty().set("2");
        viewModel.valueProperty().set("value");
        viewModel.add();

        viewModel.newKeyProperty().set("6");

        viewModel.decreaseKey();

        assertEquals("New key must be less than current", viewModel.resultProperty().get());
        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canValidateNewKeyInputBeforeDecrease() {
        viewModel.keyProperty().set("1");
        viewModel.newKeyProperty().set("words");

        viewModel.decreaseKey();

        assertEquals("Any key was not decreased", viewModel.resultProperty().get());
        assertEquals(Status.BAD_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canValidateKeyInputBeforeDecrease() {
        viewModel.keyProperty().set("this key");
        viewModel.newKeyProperty().set("4");

        viewModel.decreaseKey();

        assertEquals("Any key was not decreased", viewModel.resultProperty().get());
        assertEquals(Status.BAD_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canChangeSelectedHeap() {
        viewModel.heapProperty().set(new LeftistHeap<String>("new heap"));
        viewModel.keyProperty().set("5");
        viewModel.valueProperty().set("value");
        viewModel.add();

        assertEquals("New element added to heap 'new heap'", viewModel.resultProperty().get());
        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
    }
}
