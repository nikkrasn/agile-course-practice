package ru.unn.agile.ColorConverter.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class MockLogger implements ILogger {

    private final ArrayList<String> log;

    public MockLogger() {
        log = new ArrayList<>();
    }

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public void addToLog(final String message) {
        log.add(message);
    }
}
