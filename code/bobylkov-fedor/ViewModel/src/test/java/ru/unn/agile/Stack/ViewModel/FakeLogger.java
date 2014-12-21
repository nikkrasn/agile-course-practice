package ru.unn.agile.Stack.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakeLogger implements ILogger {
    private final List<LogMessage> log = new ArrayList<>();

    @Override
    public void log(final String s) {
        log.add(new LogMessage(new Date(), s));
    }

    @Override
    public List<LogMessage> getLog() {
        return log;
    }
}
