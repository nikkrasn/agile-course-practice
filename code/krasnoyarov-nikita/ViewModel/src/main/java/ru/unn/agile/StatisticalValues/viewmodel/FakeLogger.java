package ru.unn.agile.StatisticalValues.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger extends SimpleLogger {
    private final List<String> log = new ArrayList<>();

    @Override
    public void log(final String logMessage) {
        log.add(mergeDateWithMessage(logMessage));
    }

    @Override
    public final List<String> getLog() {
        return log;
    }
}
