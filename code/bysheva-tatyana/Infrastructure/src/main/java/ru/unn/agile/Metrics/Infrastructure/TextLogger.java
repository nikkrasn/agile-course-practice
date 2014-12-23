package ru.unn.agile.Metrics.Infrastructure;

import ru.unn.agile.Metrics.viewmodel.ILogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextLogger implements ILogger {
    private final List<String> log;
    private final BufferedWriter fileWriter;
    private final String filename;

    public TextLogger(final String logFilename) {
        filename = logFilename;
        log = new ArrayList<>();

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
        try {
            fileWriter.write(s);
            fileWriter.newLine();
            fileWriter.flush();
            log.add(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLastMessage() {
        return getMessage(log.size() - 1);
    }

    @Override
    public String getMessage(final Integer index) {
        if (index < 0 || index >= log.size()) {
            return "";
        }
        return log.get(index);
    }

    @Override
    public List<String> getFullLog() {
        return null;
    }
}
