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
            fail(String.format("Log file '{0}' was not found", logFilename));
        }
    }

    @Test
    public void canLogMessage() {
        logger.log("message");

        assertEquals("message", logger.getLog().get(0));
    }
}
