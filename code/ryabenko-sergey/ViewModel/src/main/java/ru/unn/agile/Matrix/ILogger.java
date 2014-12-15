package ru.unn.agile.Matrix;

import java.util.List;

public interface ILogger {
    void createLog(final String logString);
    List<String> getLog();
}
