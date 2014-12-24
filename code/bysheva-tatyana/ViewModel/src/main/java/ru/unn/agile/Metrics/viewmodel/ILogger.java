package ru.unn.agile.Metrics.viewmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class ILogger {
    private final SimpleDateFormat dateTimeFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    public abstract void log(final String s);
    public abstract List<String> getLog();
    public String addDateTimeToMessage(final String message) {
        return dateTimeFormat.format(new Date()) + " : " + message;
    }

    public Date getMessageDateTime(final Integer index) throws ParseException {
        return dateTimeFormat.parse(getFullMessage(index).split(" : ")[0]);
    }

    public String getMessageText(final Integer index) {
        return getFullMessage(index).split(" : ")[1];
    }

    public String getFullMessage(final Integer index) {
        return getLog().get(index);
    }
}
