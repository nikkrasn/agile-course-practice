package ru.unn.agile.StatisticalValues.viewmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class ILogger {
    private static final String LOG_SEPARATOR = " :: ";
    private final SimpleDateFormat dateTimeFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    public abstract void log(final String s);
    public abstract List<String> getLog();

    public String mergeDataWithMessage(final String logMessage) {
        return dateTimeFormat.format(new Date()) + LOG_SEPARATOR + logMessage;
    }

    public String getLoggedMessage(final Integer index) {
        return getLog().get(index);
    }

    public String getLoggedMessageText(final Integer index) {
        return getLoggedMessage(index).split(LOG_SEPARATOR)[1];
    }

    public Date getLoggedMessageDate(final Integer index) throws ParseException {
        return dateTimeFormat.parse(getLoggedMessage(index).split(LOG_SEPARATOR)[0]);
    }
}
