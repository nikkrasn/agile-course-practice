package ru.unn.agile.CurrencyConverter.viewmodel;

import java.util.ArrayList;

public class MockLogger implements ILogger {
    private final ArrayList<String> log = new ArrayList<>();
    private String lastMessage = null;

    @Override
    public void logEvent(final String msg) {
        lastMessage = "Event: " + msg;
        log.add(lastMessage);
    }

    @Override
    public void logError(final String msg) {
        lastMessage = "Error: " + msg;
        log.add(lastMessage);
    }

    @Override
    public String getLastLogMessage() {
        return lastMessage;
    }

    @Override
    public ArrayList<String> getFullLog() {
        return log;
    }
}
