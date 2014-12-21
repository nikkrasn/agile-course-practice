package ru.unn.agile.Stack;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Stack.ViewModel.ILogger;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;

public class PlainTextLoggerTests {
    private ILogger logger;
    private final String logFilename = "PlainTextLoggerTests.log";

    @Before
    public void setUp() {
        logger = new PlainTextLogger(logFilename);
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(logger);
    }

    @Test
    public void checkLogFileIsCreated() {
        try {
            new FileReader(logFilename);
        } catch (FileNotFoundException e) {
            fail(String.format("Log file '%s' was not found", logFilename));
        }
    }

    @Test
    public void canLogMessage() {
        logger.log("message");

        assertLogMessageEquals("message", logger.getLog().get(0));
    }

    private void assertLogMessageEquals(final String expected, final String actual) {
        assertTrue(new LogMessage(actual).getMessage().equals(expected));
    }
}
