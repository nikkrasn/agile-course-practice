package ru.unn.agile.Dichotomy.viewmodel;

import javafx.beans.property.*;
import ru.unn.agile.Dichotomy.Model.Dichotomy;

public class ViewModel {

    private final StringProperty stringArray = new SimpleStringProperty();
    private final StringProperty stringElement = new SimpleStringProperty();
    private int[] array;

    private final StringProperty dichotomyResult = new SimpleStringProperty();
    private final StringProperty dichotomyStatus = new SimpleStringProperty();

    public ViewModel() {
        stringArray.set("");
        stringElement.set("");
        dichotomyResult.set("Result");
        dichotomyStatus.set(InputStatus.WAITING.toString());
    }

    public StringProperty stringArrayProperty() {
        return stringArray;
    }

    public StringProperty stringElementProperty() {
        return stringElement;
    }

    public int[] parseString() {
        String inputString = stringArray.get();
        String[] stringElements = inputString.split(" ");
        int stringElementsLength = stringElements.length;
        int[] elements = new int[stringElementsLength];
        //   for (int i=0;i<inputString.length();i++) {
//            if (inputString.indexOf(0) != 1) {
//                dichotomyStatus.set(InputStatus.BAD_FORMAT.toString());
//                break;
//            }
        //   }
        for (int i = 0; i < stringElementsLength; i++) {
            elements[i] = Integer.parseInt(stringElements[i]);
        }
        return elements;
    }

    public void findElement() {
        Dichotomy searching = new Dichotomy();
        int element = Integer.parseInt(stringElement.get());
        int result = searching.dichotomySearch(parseString(), element);
        if (result == element) {
            dichotomyResult.set(ResultStatus.CONTAIN.toString());
        } else {
            dichotomyResult.set(ResultStatus.NOT_CONTAIN.toString());
        }
    }

    public StringProperty dichotomyResultProperty() {
        return dichotomyResult;
    }

    public final String getDichotomyResult() {
        return dichotomyResult.get();
    }

    public StringProperty statusDichotomyProperty() {
        return dichotomyStatus;
    }

    public final String getDichotomyStatus() {
        return dichotomyStatus.get();
    }

}

enum InputStatus {
    WAITING("Please enter input sorted array"),
    WAITING_ELEMENT("Please enter element"),
    READY("Press 'Search'"),
    BAD_FORMAT_ARRAY("Bad format of array"),
    BAD_FORMAT_ELEMENT("Bad format of element"),
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
