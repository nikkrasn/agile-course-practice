package ru.unn.agile.AreaConverter.viewmodel;

import java.util.List;

public interface ILogger {
    void log(final String s);

    List<String> getLog();
}
