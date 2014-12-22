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
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter bufWriter;
    private final String fileName;

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return sdf.format(calendar.getTime());
    }

    public TxtLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bufWriter = logWriter;
    }

    @Override
    public void log(final String s) {
        try {
            bufWriter.write(now() + " > " + s);
            bufWriter.newLine();
            bufWriter.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufReader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            bufReader = new BufferedReader(new FileReader(fileName));
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
