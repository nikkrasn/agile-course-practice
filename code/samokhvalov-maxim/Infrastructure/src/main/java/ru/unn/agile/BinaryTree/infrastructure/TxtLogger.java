package ru.unn.agile.BinaryTree.infrastructure;

import ru.unn.agile.BinaryTree.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";
    private final BufferedWriter bufWriter;
    private final String filename;

    public TxtLogger(final String filename) {
        this.filename = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bufWriter = logWriter;
    }

    @Override
    public void log(final String text) {
        try {
            bufWriter.write(now() + " : " + text);
            bufWriter.newLine();
            bufWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufReader;
        ArrayList<String> logs = new ArrayList<String>();
        try {
            bufReader = new BufferedReader(new FileReader(filename));
            String line = bufReader.readLine();

            while (line != null) {
                logs.add(line);
                line = bufReader.readLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return logs;
    }

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dataFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return dataFormat.format(calendar.getTime());
    }
}
