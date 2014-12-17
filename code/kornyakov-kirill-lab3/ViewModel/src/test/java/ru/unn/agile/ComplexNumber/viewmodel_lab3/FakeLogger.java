package ru.unn.agile.ComplexNumber.viewmodel_lab3;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
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
