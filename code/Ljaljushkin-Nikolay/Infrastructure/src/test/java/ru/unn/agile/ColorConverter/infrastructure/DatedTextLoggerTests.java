package ru.unn.agile.ColorConverter.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class DatedTextLoggerTests {
    private static final String LOG_FILENAME = "./DateTextLogger_Tests.log";
    private DatedTextLogger datedTextLogger;

    @Before
    public void setUp() {
        datedTextLogger = new DatedTextLogger(LOG_FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(datedTextLogger);
    }

    @Test
    public void checkLogFileIsCreated() throws FileNotFoundException {
        new FileReader(LOG_FILENAME);
    }

    @Test
    public void canWriteCorrectLogMessage() {
        String message = "Log message";
        datedTextLogger.addToLog(message);
        assertNotEquals(message.indexOf(datedTextLogger.getLog().get(0)), -1);
    }
}
