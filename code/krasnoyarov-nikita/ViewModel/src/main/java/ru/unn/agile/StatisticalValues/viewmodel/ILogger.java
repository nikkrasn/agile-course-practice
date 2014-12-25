package ru.unn.agile.StatisticalValues.viewmodel;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ILogger {
    void log(final String s);

    public String getLastLoggedMessage();
    public String getLoggedMessage(final Integer index);
    public String getLoggedMessageText(final Integer index);
    public Date getLoggedMessageDate(final Integer index) throws ParseException;

    List<String> getLog();
}
