package ru.unn.agile.ComplexNumber.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class MockLogger implements ILogger {
    private final ArrayList<String> log = new ArrayList<>();

    @Override
    public void log(final String s) {
        log.add(s);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
