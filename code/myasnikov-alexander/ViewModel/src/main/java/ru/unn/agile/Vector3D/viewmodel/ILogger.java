package ru.unn.agile.Vector3D.viewmodel;

import java.util.List;

public interface ILogger {
    void log(final String s);

    List<String> getLog();
}
