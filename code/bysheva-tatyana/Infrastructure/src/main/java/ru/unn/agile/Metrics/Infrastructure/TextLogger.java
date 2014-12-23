package ru.unn.agile.Metrics.Infrastructure;

import ru.unn.agile.Metrics.viewmodel.ILogger;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TextLogger implements ILogger {
    private final BufferedWriter bufferedFileWriter;
    private final String filename;
    private final SimpleDateFormat dateTimeFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    public TextLogger(final String logFilename) {
        filename = logFilename;

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
            bufferedFileWriter.write(dateTimeFormat.format(new Date()) + " : " + s);
            bufferedFileWriter.newLine();
            bufferedFileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getLog() {
        List<String> log = new ArrayList<>();

        BufferedReader bufferedFileReader;
        try {
            bufferedFileReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return log;
        }

        String line;
        try {
            while ((line = bufferedFileReader.readLine()) != null) {
                log.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log;
    }
}
