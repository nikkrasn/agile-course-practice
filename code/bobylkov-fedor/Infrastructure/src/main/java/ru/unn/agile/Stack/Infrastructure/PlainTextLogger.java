package ru.unn.agile.Stack.Infrastructure;

import ru.unn.agile.Stack.ViewModel.ILogger;
import ru.unn.agile.Stack.ViewModel.LogMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlainTextLogger implements ILogger {
    private final BufferedWriter writer;
    private final String filename;

    public PlainTextLogger(final String logFilename) {
        filename = logFilename;

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer = bufferedWriter;
    }

    @Override
    public void log(final String message) {
        try {
            writer.write(new LogMessage(new Date(), message).toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LogMessage> getLog() {
        List<LogMessage> log = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return log;
        }

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                log.add(new LogMessage(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log;
    }
}

