package ru.unn.agile.ColorConverter.infrastructure;

import ru.unn.agile.ColorConverter.viewmodel.ILogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DatedTextLogger implements ILogger {

    private final BufferedWriter writer;
    private final String filename;

    public DatedTextLogger(String filename) {
        this.filename = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public List<String> getLog() {
        return null;
    }

    @Override
    public void addToLog(String s) {

    }
}
