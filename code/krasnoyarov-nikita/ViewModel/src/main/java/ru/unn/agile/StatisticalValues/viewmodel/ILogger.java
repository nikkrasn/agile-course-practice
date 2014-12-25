package ru.unn.agile.StatisticalValues.viewmodel;

import java.util.List;

public interface ILogger {

    void log(final String s);

    List<String> getLog();
}
