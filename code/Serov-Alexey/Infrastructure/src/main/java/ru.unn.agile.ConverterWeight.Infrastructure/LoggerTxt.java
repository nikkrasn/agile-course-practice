package ru.unn.agile.ConverterWeight.Infrastructure;

import ru.unn.agile.ConverterWeight.viewmodel.ILogger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class LoggerTxt implements ILogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String file;
    private final BufferedWriter writer;

    public LoggerTxt(final String file) {
        this.file = file;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public List<String> getLog() {
        BufferedReader logReader;
        ArrayList<String> logMessages = new ArrayList<String>();
        try {
            logReader = new BufferedReader(new FileReader(file));
            String line = logReader.readLine();

            while (line != null) {
                logMessages.add(line);
                line = logReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return logMessages;
    }

    @Override
    public void log(final String s) {
        try {
            writer.write(time() + " > " + s);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String time() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }
}
