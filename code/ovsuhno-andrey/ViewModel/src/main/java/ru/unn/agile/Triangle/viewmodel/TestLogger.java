package ru.unn.agile.Triangle.viewmodel;

import java.util.ArrayList;

public class TestLogger implements LoggerInterface {
        private final ArrayList<String> log = new ArrayList<>();

        @Override
        public void logRecord(final String record) {
            log.add(record);
        }

        @Override
        public ArrayList<String> getLogRecords() {
            return log;
        }
    }
