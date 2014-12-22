package ru.unn.agile.Vector3D.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> log = new ArrayList<>();

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public void log(final String str) {
        log.add(str);
    }


}
