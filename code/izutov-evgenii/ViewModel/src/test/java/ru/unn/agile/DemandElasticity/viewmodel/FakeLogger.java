package ru.unn.agile.DemandElasticity.viewmodel;


import java.util.ArrayList;
import java.util.List;

public final class FakeLogger implements ILogger {
    private final ArrayList<String> logData = new ArrayList<>();

    @Override
    public void logMessage(final String str) {
        logData.add(str);
    }

    @Override
    public List<String> getFullLog() {
        return logData;
    }
}
