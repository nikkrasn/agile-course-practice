package ru.unn.agile.CurrencyConverter.Logger;

import ru.unn.agile.CurrencyConverter.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PlainTextLogger implements ILogger {
    private static final String TIMESTAMP_FORMAT = "yyyy.MM.dd HH:mm:ss";
    private String lastLoggedMessage = null;
    private String filename = null;
    private BufferedWriter logFileWriter = null;

    public PlainTextLogger(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("filename argument can't be null");
        }
        this.filename = filename;

        try {
            logFileWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logEvent(String message) {
        try {
            lastLoggedMessage = timestamp() + " Event: " + message;
            logFileWriter.write(lastLoggedMessage);
            logFileWriter.newLine();
            logFileWriter.flush();
        } catch (IOException e) {
            lastLoggedMessage = null;
            e.printStackTrace();
        }
    }

    @Override
    public void logError(String message) {
        try {
            lastLoggedMessage = timestamp() + " Error: " + message;
            logFileWriter.write(lastLoggedMessage);
            logFileWriter.newLine();
            logFileWriter.flush();
        } catch (IOException e) {
            lastLoggedMessage = null;
            e.printStackTrace();
        }
    }

    @Override
    public String getLastLogMessage() {
        return lastLoggedMessage;
    }

    @Override
    public ArrayList<String> getFullLog() {
        BufferedReader reader;
        ArrayList<String> fullLog = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                fullLog.add(line);
                line = reader.readLine();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return fullLog;
    }

    private static String timestamp() {
        String timestamp = "[" +
                (new SimpleDateFormat(TIMESTAMP_FORMAT, Locale.ENGLISH).format(new Date())) + "]";
        return timestamp;
    }
}