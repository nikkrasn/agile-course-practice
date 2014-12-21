package ru.unn.agile.QuickSort.viewmodel;

import java.util.List;

public interface ILogger {
    void log(final String s);

    List<String> getLog();
}
