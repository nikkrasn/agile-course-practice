package ru.unn.agile.Matrix;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class TxtLogger implements ILogger {
    private final BufferedWriter writer;
    private final String filename;
    private static final String DATE_NOW = "yyyy-MM-dd [HH:mm:ss]";

    public TxtLogger(final String filename) {
        this.filename = filename;
        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public void createLog(final String logString) {
        try {
            writer.write(dateNow() + ": " + logString);
            writer.newLine();
            writer.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return log;
    }

    private static String dateNow() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(DATE_NOW, Locale.ENGLISH);
        return date.format(calendar.getTime());
    }
}
