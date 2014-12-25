package ru.unn.agile.Metrics.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger extends ILogger {
    private final List<String> log = new ArrayList<>();

    @Override
    public void log(final String s) {
        log.add(addDateTimeToMessage(s));
    }

    @Override
    public final List<String> getLog() {
        return log;
    }
}
