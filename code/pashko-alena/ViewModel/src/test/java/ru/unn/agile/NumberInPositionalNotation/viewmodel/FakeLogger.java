package ru.unn.agile.NumberInPositionalNotation.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> logs = new ArrayList<>();

    @Override
    public List<String> getLog() {
        return logs;
    }

    @Override
    public void log(final String s) {
        logs.add(s);
    }
}

