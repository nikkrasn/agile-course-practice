package ru.unn.agile.BinaryTree.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FictitiousLogger implements ILogger {
    private final ArrayList<String> log = new ArrayList<>();

    @Override
    public void log(final String text) {
        log.add(text);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
