package ru.unn.agile.CalculateSquare.infrastructure;

import ru.unn.agile.CalculateSquare.viewmodel.ILog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TextLog implements ILog {
    private static final String FORMAT_DATE_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter bufWriter;
    private final String fname;

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    public TextLog(final String filename) {
        this.fname = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
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
            bufReader = new BufferedReader(new FileReader(fname));
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
