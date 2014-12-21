package ru.unn.agile.Stack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogMessage {
    private Date dateTime;
    private String message;
    private final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public LogMessage(Date d, String s) {
        dateTime = d;
        message = s;
    }

    public LogMessage(String logMessage) {
        String[] split = logMessage.split(" >> ");

        try {
            dateTime = dateTimeFormat.parse(split[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        message = split[1];
    }

    public String getMessage() {
        return message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String toString() {
        return String.format("%s >> %s", dateTimeFormat.format(dateTime), message);
    }
}
