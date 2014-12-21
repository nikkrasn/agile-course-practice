package ru.unn.agile.Stack.ViewModel;

import java.util.List;

public interface ILogger {
    void log(final String s);

    List<String> getLog();
}
