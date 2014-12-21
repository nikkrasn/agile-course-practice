package ru.unn.agile.AreaConverter.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeAreaConverterLogger implements ILogger {
    private ArrayList<String> areaConverterLog = new ArrayList<String>();

    @Override
    public void log(final String s) {
        areaConverterLog.add(s);
    }

    @Override
    public List<String> getLog() {
        return areaConverterLog;
    }
}
