package ru.unn.agile.Dichotomy.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.Dichotomy.Model.Dichotomy;
import java.util.ArrayList;
import java.util.List;

public class ViewModel {

    private final StringProperty stringArray = new SimpleStringProperty();
    private final StringProperty stringElement = new SimpleStringProperty();
    private int[] elementsArray;
    private final StringProperty dichotomyResult = new SimpleStringProperty();
    private final StringProperty dichotomyStatus = new SimpleStringProperty();
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();
    private final BooleanProperty inputArrayDisabled = new SimpleBooleanProperty();
    private final BooleanProperty applyDisabled = new SimpleBooleanProperty();
    private final BooleanProperty inputElementDisabled = new SimpleBooleanProperty();
    private final BooleanProperty searchDisabled = new SimpleBooleanProperty();
    private boolean isArrayEntered = false;
    private ILogger logger;

    public void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    public ViewModel() {
        initialize();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        initialize();
    }

    private void initialize() {
        setInitialCondition();
        dichotomyStatus.set(InputStatus.WAITING.toString());
        applyDisabled.set(true);
        searchDisabled.set(true);
        bindingInit();
        listenerInit();
    }

    public StringProperty stringArrayProperty() {
        return stringArray;
    }

    public StringProperty stringElementProperty() {
        return stringElement;
    }

    public void parseString() {
        if (!isArrayEntered) {
            Dichotomy searching = new Dichotomy();
            String[] stringElements = splitString();
            int stringElementsLength = stringElements.length;
            int[] elements = new int[stringElementsLength];
            for (int i = 0; i < stringElementsLength; i++) {
                elements[i] = Integer.parseInt(stringElements[i]);
            }
            if (searching.isArrayOrdered(elements)) {
                dichotomyStatus.set(InputStatus.WAITING_ELEMENT.toString());
                inputElementDisabled.setValue(false);
                inputArrayDisabled.set(true);
                isArrayEntered = true;
            } else {
                dichotomyStatus.set(InputStatus.UNSORTED.toString());
                inputElementDisabled.setValue(true);
            }
            elementsArray = elements;
        }
    }

    public void findElement() {
        Dichotomy searching = new Dichotomy();
        int element = Integer.parseInt(stringElement.get());
        int result = searching.dichotomySearch(elementsArray, element);
        if (result == element) {
            dichotomyResult.set(ResultStatus.CONTAIN.toString());
        } else {
            dichotomyResult.set(ResultStatus.NOT_CONTAIN.toString());
        }
        dichotomyStatus.set(InputStatus.SUCCESS.toString());
    }

    public void enterNewArray() {
        setInitialCondition();
        isArrayEntered = false;
    }

    public final List<String> getLog() {
        return logger.getLog();
    }

    public StringProperty dichotomyResultProperty() {
        return dichotomyResult;
    }

    public final String getDichotomyResult() {
        return dichotomyResult.get();
    }

    public StringProperty dichotomyStatusProperty() {
        return dichotomyStatus;
    }

    public final String getDichotomyStatus() {
        return dichotomyStatus.get();
    }

    public BooleanProperty inputArrayDisabledProperty() {
        return inputArrayDisabled;
    }

    public final boolean getInputArrayDisabled() {
        return inputArrayDisabled.get();
    }

    public BooleanProperty applyDisabledProperty() {
        return applyDisabled;
    }

    public final boolean getApplyDisabled() {
        return applyDisabled.get();
    }

    public BooleanProperty inputElementDisabledProperty() {
        return inputElementDisabled;
    }

    public final boolean getInputElementDisabled() {
        return inputElementDisabled.get();
    }

    public BooleanProperty searchDisabledProperty() {
        return searchDisabled;
    }

    public final boolean getSearchDisabled() {
        return searchDisabled.get();
    }

    private InputStatus getInputStatus() {
        InputStatus inputStatus = InputStatus.READY;
        String[] stringElements = splitString();
        int stringElementsLength = stringElements.length;
        if (stringArray.get().isEmpty()) {
            inputStatus = InputStatus.WAITING;
        }
        try {
            if (!stringArray.get().isEmpty()) {
                for (int i = 0; i < stringElementsLength; i++) {
                    Integer.parseInt(stringElements[i]);
                }
                if (stringElement.get().isEmpty()) {
                    if (isArrayEntered) {
                        inputStatus = InputStatus.WAITING_ELEMENT;
                    } else {
                        inputStatus = InputStatus.APPLY;
                    }
                }
            }
        } catch (NumberFormatException nfe) {
            inputStatus = InputStatus.BAD_FORMAT_ARRAY;
            return inputStatus;
        }
        try {
            if (!stringElement.get().isEmpty()) {
                Integer.parseInt(stringElement.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = InputStatus.BAD_FORMAT_ELEMENT;
        }
        return inputStatus;
    }

    private void bindingInit() {
        BooleanBinding couldApply = new BooleanBinding() {
            {
                super.bind(stringArray);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == InputStatus.APPLY;
            }
        };
        applyDisabled.bind(couldApply.not());

        BooleanBinding couldSearch = new BooleanBinding() {
            {
                super.bind(stringElement);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == InputStatus.READY;
            }
        };
        searchDisabled.bind(couldSearch.not());
    }

    private void listenerInit() {
        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(stringArray);
            add(stringElement);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    private void setInitialCondition() {
        stringArray.set("");
        stringElement.set("");
        dichotomyResult.set("");
        inputArrayDisabled.set(false);
        inputElementDisabled.set(true);
    }

    private String[] splitString() {
        String inputString = stringArray.get();
        return inputString.split(" ");
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            dichotomyStatus.set(getInputStatus().toString());
        }
    }
}

enum InputStatus {
    WAITING("Please enter input sorted array"),
    WAITING_ELEMENT("Please enter element"),
    APPLY("Press 'Apply'"),
    READY("Press 'Search'"),
    BAD_FORMAT_ARRAY("Bad format of array"),
    BAD_FORMAT_ELEMENT("Bad format of element"),
    UNSORTED("Array is unsorted"),
    SUCCESS("Success");

    private final String name;
    private InputStatus(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

enum ResultStatus {
    CONTAIN("Array contain an element"),
    NOT_CONTAIN("Array don't contain an element");

    private final String name;
    private ResultStatus(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
