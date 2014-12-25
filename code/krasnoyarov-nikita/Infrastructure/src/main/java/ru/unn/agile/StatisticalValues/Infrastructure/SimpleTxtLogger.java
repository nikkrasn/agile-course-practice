package ru.unn.agile.StatisticalValues.Infrastructure;

import ru.unn.agile.StatisticalValues.viewmodel.ILogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SimpleTxtLogger implements ILogger {
    private final String LOG_SEPARATOR = " :: ";
    private final BufferedWriter fileWriter;
    private final List<String> log = new ArrayList<>();
    private final SimpleDateFormat dateTimeFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    public SimpleTxtLogger(final String logFilename) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(logFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileWriter = writer;
    }

    public String mergeDataWithMessage(final String logMessage) {
        return dateTimeFormat.format(new Date()) + LOG_SEPARATOR + logMessage;
    }

    public String getLoggedMessageText(final Integer index) {
        return getLoggedMessage(index).split(LOG_SEPARATOR)[1];
    }

    public String getLoggedMessageDate(final Integer index) {
        return getLoggedMessage(index).split(LOG_SEPARATOR)[0];
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
