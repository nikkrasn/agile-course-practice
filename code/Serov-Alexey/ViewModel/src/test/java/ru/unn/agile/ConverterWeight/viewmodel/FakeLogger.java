package ru.unn.agile.ConverterWeight.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void log(final String str) {
        log.add(str);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
