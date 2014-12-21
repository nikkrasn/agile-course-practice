package ru.unn.agile.TemperatureConverter.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> fakeLog = new ArrayList<String>();

    @Override
    public void log(final String s) {
        fakeLog.add(s);
    }

    @Override
    public List<String> getLog() {
        return fakeLog;
    }
}
