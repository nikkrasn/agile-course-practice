package ru.unn.agile.NumberInPositionalNotation.infrastructure;

import ru.unn.agile.NumberInPositionalNotation.viewmodel.ILogger;

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
    private final BufferedWriter writer;
    private final String logName;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void log(final String s) {
        try {
            writer.write(logTime() + " > " + s);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public TxtLogger(final String logName) {
        this.logName = logName;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(logName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public List<String> getLog() {
        BufferedReader bReader;
        ArrayList<String> txtLog = new ArrayList<String>();
        try {
            bReader = new BufferedReader(new FileReader(logName));
            String line = bReader.readLine();

            while (line != null) {
                txtLog.add(line);
                line = bReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return txtLog;
    }

    private static String logTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }
}
