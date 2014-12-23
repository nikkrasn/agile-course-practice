package ru.unn.agile.BinaryTree.viewmodel;

import java.util.List;

public interface ILogger {
    void log(final String text);

    List<String> getLog();
}
