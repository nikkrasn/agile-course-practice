package ru.unn.agile.Metrics.Infrastructure;

import ru.unn.agile.Metrics.viewmodel.ILogger;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextLogger extends ILogger {
    private final BufferedWriter bufferedFileWriter;
    private final List<String> log = new ArrayList<>();

    public TextLogger(final String filename) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferedFileWriter = writer;
    }

    @Override
    public void log(final String s) {
        try {
            String message = addDateTimeToMessage(s);
            bufferedFileWriter.write(message);
            bufferedFileWriter.newLine();
            bufferedFileWriter.flush();
            log.add(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final List<String> getLog() {
        return log;
    }
}
