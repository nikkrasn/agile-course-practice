package ru.unn.agile.Dichotomy.infrastructure;

import ru.unn.agile.Dichotomy.viewmodel.ILogger;

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
    private final BufferedWriter writer;
    private final String fileName;

    public TxtLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public void log(final String text) {
        try {
            writer.write(now() + " - " + text);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> logs = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {
                logs.add(line);
                line = reader.readLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return logs;
    }

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(calendar.getTime());
    }
}
