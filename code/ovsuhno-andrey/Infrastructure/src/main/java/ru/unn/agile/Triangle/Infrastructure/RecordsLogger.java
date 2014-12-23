package ru.unn.agile.Triangle.Infrastructure;

import ru.unn.agile.Triangle.viewmodel.LoggerInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RecordsLogger implements LoggerInterface {
    private static final String LOG_TIME_FORMAT = "dd.MM.yyyy - HH:mm:ss";
    private final BufferedWriter recorder;
    private final String recordFile;

    private static String now() {
        Calendar date = Calendar.getInstance();
        SimpleDateFormat dFormat = new SimpleDateFormat(LOG_TIME_FORMAT, Locale.ENGLISH);
        return dFormat.format(date.getTime());
    }

    public RecordsLogger(final String recordFile) {
        this.recordFile = recordFile;

        BufferedWriter recorder = null;
        try {
            recorder = new BufferedWriter(new FileWriter(recordFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.recorder = recorder;
    }

    @Override
    public void logRecord(final String record) {
        try {
            recorder.write(now() + " => " + record);
            recorder.newLine();
            recorder.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLogRecords() {
        BufferedReader recordReader;
        ArrayList<String> recordLog = new ArrayList<String>();
        try {
            recordReader = new BufferedReader(new FileReader(recordFile));
            String record = recordReader.readLine();

            while (record != null) {
                recordLog.add(record);
                record = recordReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return recordLog;
    }

}
