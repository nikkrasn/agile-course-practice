package ru.unn.agile.StatisticalValues.Infrastructure;

import ru.unn.agile.StatisticalValues.viewmodel.ILogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleTxtLogger implements ILogger{
    private final BufferedWriter fileWriter;
    private final List<String> log = new ArrayList<>();

    public SimpleTxtLogger(final String logFilename) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(logFilename));
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
    public String getLastLoggedMessage() {
        return getLoggedMessage(log.size() - 1);
    }

    @Override
    public String getLoggedMessage(final Integer index) {
        if (index < 0 || index >= log.size()) {
            return "";
        }
        return log.get(index);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
