package ru.unn.agile.StatisticalValues.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger extends ILogger {
    private final List<String> log = new ArrayList<>();

    @Override
    public void log(final String logMessage) {
        log.add(mergeDataWithMessage(logMessage));
    }

    @Override
    public final List<String> getLog() {
        return log;
    }
}
