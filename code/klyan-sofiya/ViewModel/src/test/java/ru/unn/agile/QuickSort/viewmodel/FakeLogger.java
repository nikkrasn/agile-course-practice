package ru.unn.agile.QuickSort.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> logList = new ArrayList<>();

    @Override
    public void log(final String s) {
        logList.add(s);
    }

    @Override
    public List<String> getLog() {
        return logList;
    }
}
