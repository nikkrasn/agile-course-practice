package ru.unn.agile.StatisticalValues.viewmodel;

import java.util.List;

public interface ILogger {
    void log(final String s);

    public String getLastLoggedMessage();
    public String getLoggedMessage(final Integer index);
    public String getLoggedMessageText(final Integer index);
    public String getLoggedMessageDate(final Integer index);

    List<String> getLog();
}
