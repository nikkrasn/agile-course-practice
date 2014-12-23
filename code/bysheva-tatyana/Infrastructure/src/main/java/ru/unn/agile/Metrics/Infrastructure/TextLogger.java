package ru.unn.agile.Metrics.Infrastructure;

import ru.unn.agile.Metrics.viewmodel.ILogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextLogger implements ILogger {
    private BufferedWriter fileWriter;
    private String filename;

    public TextLogger(final String logFilename) {
        filename = logFilename;

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileWriter = writer;
    }

    @Override
    public void log(final String s) {

    }

    @Override
    public List<String> getLog() {
        return null;
    }
}
