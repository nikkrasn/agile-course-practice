package ru.unn.agile.Metrics.viewmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class ILogger {
    private final SimpleDateFormat dateTimeFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    private static final String SEPARATOR = ">>> ";

    public abstract void log(final String s);
    public abstract List<String> getLog();
    public String addDateTimeToMessage(final String message) {
        return dateTimeFormat.format(new Date()) + SEPARATOR + message;
    }

    public Date getMessageDateTime(final Integer index) throws ParseException {
        return dateTimeFormat.parse(getFullMessage(index).split(SEPARATOR)[0]);
    }

    public String getMessageText(final Integer index) {
        return getFullMessage(index).split(SEPARATOR)[1];
    }

    public String getFullMessage(final Integer index) {
        return getLog().get(index);
    }
}
