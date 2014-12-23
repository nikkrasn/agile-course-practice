package ru.unn.agile.Metrics.viewmodel;

import java.util.List;

public interface ILogger {
    void log(final String s);
    String getLastMessage();
    String getMessage(final Integer index);
    List<String> getFullLog();
}
