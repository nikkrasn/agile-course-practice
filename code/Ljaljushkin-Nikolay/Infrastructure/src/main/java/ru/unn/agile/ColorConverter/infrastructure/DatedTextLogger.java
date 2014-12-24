package ru.unn.agile.ColorConverter.infrastructure;

import ru.unn.agile.ColorConverter.viewmodel.ILogger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatedTextLogger implements ILogger {
    private final BufferedWriter fileWriter;
    private final String logFilename;

    public DatedTextLogger(final String filename) {
        logFilename = filename;

        BufferedWriter logFileWriter = null;
        try {
            logFileWriter = new BufferedWriter(new FileWriter(logFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileWriter = logFileWriter;
    }

    @Override
    public List<String> getLog() {

        BufferedReader fileReader;
        ArrayList<String> log = new ArrayList<>();

        try {
            fileReader = new BufferedReader(new FileReader(logFilename));
            String line = fileReader.readLine();

            while (line != null) {
                log.add(line);
                line = fileReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

    @Override
    public void addToLog(final String message) {
        try {
            fileWriter.write(message);
            fileWriter.newLine();
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
