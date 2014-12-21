package ru.unn.agile.Stack;

import ru.unn.agile.Stack.ViewModel.ILogger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlainTextLogger implements ILogger {
    private final String filename;
    private final BufferedWriter writer;

    public PlainTextLogger(final String logFilename) {
        filename = logFilename;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.writer = writer;
    }

    @Override
    public void log(final String string) {
        try {
            writer.write(string);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getLog() {
        List<String> log = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return log;
        }

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                log.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log;
    }
}
