package ru.unn.agile.Triangle.viewmodel;

import java.util.List;

public interface LoggerInterface {
    void logRecord(final String s);

    List<String> getLogRecords();
}
