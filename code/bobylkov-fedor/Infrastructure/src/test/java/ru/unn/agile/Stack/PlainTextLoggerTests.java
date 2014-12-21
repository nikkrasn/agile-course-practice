package ru.unn.agile.Stack;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Stack.ViewModel.ILogger;
import ru.unn.agile.Stack.ViewModel.LogMessage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

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
            fail("Log file " + logFilename + " was not found");
        }
    }

    @Test
    public void canLogMessage() {
        String message = "message";
        logger.log(message);
        assertEquals(message, logger.getLog().get(0).getMessage());
    }

    @Test
    public void canLogManyMessages() {
        String[] messages = {"message1", "message2"};

        logger.log(messages[0]);
        logger.log(messages[1]);

        List<LogMessage> logMessages = logger.getLog();
        for (int i = 0; i < logMessages.size(); i++) {
            assertEquals(messages[i], logMessages.get(i).getMessage());
        }
    }

    @Test
    public void checkLogMessageHasDateTime() {
        String message = "message";
        logger.log(message);
        assertTrue(new Date().after(logger.getLog().get(0).getDateTime()));
    }
}
