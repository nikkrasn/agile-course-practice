package ru.unn.agile.BitArray.viewModel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.BitArray.model.BitArray;
import ru.unn.agile.BitArray.model.BitArray.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import static java.lang.Math.max;

public class ViewModel {
    private static final int ARRAYS_COUNT = 3;
    private int arraysSize;

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> bitOperationOnTwoFirstBitArrays =
            new SimpleObjectProperty<>();
    private final ObjectProperty<Operation> bitOperationWithThirdBitArray =
            new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final Vector<StringProperty> arrays = new Vector<>();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }

    public ViewModel() {
        arraysSize = 0;
        for (int i = 0; i < ARRAYS_COUNT; i++) {
            arrays.add(new SimpleStringProperty(""));
        }
        bitOperationOnTwoFirstBitArrays.set(Operation.AND);
        bitOperationWithThirdBitArray.set(Operation.OR);
        result.set("");
        status.set(InputStatus.WAITING.toString());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            for (StringProperty array : arrays) {
                add(array);
            }
        } };

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(arrays.get(0), arrays.get(1), arrays.get(2));
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == InputStatus.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        addListenersToInputTextFields(fields);
    }

    public void performNot(final int buttonNum) {
        if (checkArrayFieldIsEmpty(arrays.get(buttonNum))) {
            return;
        }
        arraysSize = getArraysSize();
        BitArray bitArray = new BitArray(arraysSize);
        bitArray.setBits(arrays.get(buttonNum).get().toCharArray());

        arrays.get(buttonNum).set(bitArray.not().toString());
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }
        arraysSize = getArraysSize();
        BitArray b1 = new BitArray(arraysSize);
        BitArray b2 = new BitArray(arraysSize);
        BitArray res = new BitArray(arraysSize);
        b1.setBits(getCharArrayFromField(arrays.get(0)));
        b2.setBits(getCharArrayFromField(arrays.get(1)));

        res = operationApplying(bitOperationOnTwoFirstBitArrays, b1, b2);
        if (getArrayInputStatus(arrays.get(2)) == InputStatus.READY) {
            BitArray b3 = new BitArray(arraysSize);
            b3.setBits(getCharArrayFromField(arrays.get(2)));
            result.set(operationApplying(bitOperationWithThirdBitArray, res, b3).toString());
        } else {
            result.set(res.toString());
        }
        status.set(InputStatus.SUCCESS.toString());
    }

    public StringProperty bitArray1StrValue() {
        return arrays.get(0);
    }

    public StringProperty bitArray2StrValue() {
        return arrays.get(1);
    }

    public StringProperty bitArray3StrValue() {
        return arrays.get(2);
    }

    public ObjectProperty<ObservableList<Operation>> bitOperations() {
        return operations;
    }

    public final ObservableList<Operation> getOperations() {
        return operations.get();
    }

    public ObjectProperty<Operation> firstBitOperation() {
        return bitOperationOnTwoFirstBitArrays;
    }

    public ObjectProperty<Operation> secondBitOperation() {
        return bitOperationWithThirdBitArray;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }

    public final String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    private BitArray operationApplying(final ObjectProperty<Operation> opProp,
                                       final BitArray b1, final BitArray b2) {
        Operation op = opProp.get();
        return op.apply(b1, b2);
    }

    private InputStatus getInputStatus() {
        InputStatus status = InputStatus.READY;
        if (checkArrayFieldIsEmpty(arrays.get(0)) || checkArrayFieldIsEmpty(arrays.get(1))) {
            status = InputStatus.WAITING;
        }
        if (!checkArrayFieldIsEmpty(arrays.get(0))
                && !getFieldContent(arrays.get(0)).matches("(0|1)*")) {
            status = InputStatus.BAD_FORMAT;
        }
        if (!checkArrayFieldIsEmpty(arrays.get(1))
                && !getFieldContent(arrays.get(1)).matches("(0|1)*")) {
            status = InputStatus.BAD_FORMAT;
        }
        if (!checkArrayFieldIsEmpty(arrays.get(2))
                && !getFieldContent(arrays.get(2)).matches("(0|1)*")) {
            status = InputStatus.BAD_FORMAT;
        }
        return status;
    }

    private String getFieldContent(final StringProperty field) {
        return field.get();
    }

    private char[] getCharArrayFromField(final StringProperty field) {
        return getFieldContent(field).toCharArray();
    }

    private boolean checkArrayFieldIsEmpty(final StringProperty arrayField) {
        String content = new String(arrayField.get());
        return content.isEmpty();
    }

    private int getArraysSize() {
        return max(max(getFieldContent(arrays.get(0)).length(),
                        getFieldContent(arrays.get(1)).length()),
                getFieldContent(arrays.get(2)).length());
    }

    private InputStatus getArrayInputStatus(final StringProperty array) {
        InputStatus status = InputStatus.READY;
        if (array.get().isEmpty()) {
            status = InputStatus.WAITING;
        }
        if (!array.get().isEmpty() && !array.get().matches("(0|1)*")) {
            status = InputStatus.BAD_FORMAT;
        }
        return status;
    }

    private void addListenersToInputTextFields(final List<StringProperty> fields) {
        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }
}

enum InputStatus {
    WAITING("Please input data to text fields"),
    READY("Press 'Calculate' or Enter"),
    BAD_FORMAT("Bad format of BitArray"),
    SUCCESS("Complete");

    private final String name;
    private InputStatus(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
