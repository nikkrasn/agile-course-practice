package ru.unn.agile.QuickSort.infrastructure;

import ru.unn.agile.QuickSort.viewmodel.ILogger;

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
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;
    private final String filename;

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    public TxtLogger(final String filename) {
        this.filename = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public void log(final String s) {
        try {
            writer.write(now() + " > " + s);
            writer.newLine();
            writer.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufReader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            bufReader = new BufferedReader(new FileReader(filename));
            String line = bufReader.readLine();

            while (line != null) {
                log.add(line);
                line = bufReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

}
