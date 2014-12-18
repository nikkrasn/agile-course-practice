package ru.unn.agile.Matrix;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void createLog(final String logString) {
        log.add(logString);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
