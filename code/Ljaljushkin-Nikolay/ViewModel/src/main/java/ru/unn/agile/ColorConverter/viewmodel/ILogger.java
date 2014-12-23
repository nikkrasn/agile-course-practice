package ru.unn.agile.ColorConverter.viewmodel;

import java.util.List;

public interface ILogger {

    List<String> getLog();

    void addToLog(final String s);
}

