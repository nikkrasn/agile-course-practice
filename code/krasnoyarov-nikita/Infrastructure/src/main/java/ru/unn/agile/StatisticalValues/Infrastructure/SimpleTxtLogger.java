package ru.unn.agile.StatisticalValues.infrastructure;

import ru.unn.agile.StatisticalValues.viewmodel.ILogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleTxtLogger extends ILogger {
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
    public void log(final String logMessage) {
        String mergedLogMessage = mergeDataWithMessage(logMessage);

        try {
            fileWriter.write(mergedLogMessage);
            fileWriter.newLine();
            fileWriter.flush();
            log.add(mergedLogMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
