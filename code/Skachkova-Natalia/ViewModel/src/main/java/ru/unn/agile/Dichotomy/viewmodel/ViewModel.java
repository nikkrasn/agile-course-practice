package ru.unn.agile.Dichotomy.viewmodel;

import javafx.beans.property.*;
import org.apache.tools.ant.input.InputRequest;
import ru.unn.agile.Dichotomy.Model.Dichotomy;

public class ViewModel {

    public final StringProperty stringArray = new SimpleStringProperty();
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
        for (int i = 0; i<stringElementsLength; i++) {
            elements[i] = Integer.parseInt(stringElements[i]);
        }
        return elements;
    }

    public void findElement() {
        Dichotomy searching = new Dichotomy();
        int element = Integer.parseInt(stringElement.get());
        int result = searching.dichotomySearch(parseString(), element);
        if (result == -1) {
            dichotomyResult.set(ResultStatus.NOT_CONTAIN.toString());
        }
        if (result == element) {
            dichotomyResult.set(ResultStatus.CONTAIN.toString());
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
    WAITING("Please enter input data"),
    READY("Press 'Search'"),
    UNSORTED("Array is unsorted"),
    BAD_FORMAT("Bad format"),
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