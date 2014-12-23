package ru.unn.agile.CalculateSquare.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FictiousLog implements ILog {
    private final ArrayList<String> log = new ArrayList<>();

    @Override
    public void log(final String s) {
        log.add(s);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
