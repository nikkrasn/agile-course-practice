package ru.unn.agile.StatisticalValues.viewmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FakeLogger implements ILogger {
    private final String LOG_SEPARATOR = " :: ";
    private final List<String> log = new ArrayList<>();
    private final SimpleDateFormat dateTimeFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    public String mergeDataWithMessage(final String logMessage) {
        return dateTimeFormat.format(new Date()) + LOG_SEPARATOR + logMessage;
    }

    @Override
    public String getLastLoggedMessage() {
        return getLoggedMessage(log.size() - 1);
    }

    @Override
    public String getLoggedMessage(final Integer index) {
        if (index < 0 || index >= log.size()) {
            return "";
        }
        return log.get(index);
    }

    @Override
    public String getLoggedMessageText(final Integer index) {
        return getLoggedMessage(index).split(LOG_SEPARATOR)[1];
    }

    @Override
    public Date getLoggedMessageDate(final Integer index) throws ParseException {
        return dateTimeFormat.parse(getLoggedMessage(index).split(LOG_SEPARATOR)[0]);
    }

    @Override
    public void log(final String logMessage) {
        log.add(mergeDataWithMessage(logMessage));
    }

    @Override
    public final List<String> getLog() {
        return log;
    }
}
