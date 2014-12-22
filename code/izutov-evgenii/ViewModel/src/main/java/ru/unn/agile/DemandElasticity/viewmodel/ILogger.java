package ru.unn.agile.DemandElasticity.viewmodel;

import java.util.List;

public interface ILogger {
    void logMessage(final String str);

    List<String> getFullLog();
}
