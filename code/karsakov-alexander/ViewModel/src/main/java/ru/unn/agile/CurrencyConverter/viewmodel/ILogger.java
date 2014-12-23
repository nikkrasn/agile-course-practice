package ru.unn.agile.CurrencyConverter.viewmodel;

import java.util.ArrayList;

public interface ILogger {
    void logEvent(String msg);
    void logError(String msg);

    String getLastLogMessage();
    ArrayList<String> getFullLog();
}
