package ru.unn.agile.ColorConverter.viewmodel;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void addToLog(final String s) {
        String s1 = log.get(0);
    }
}
