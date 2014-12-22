package ru.unn.agile.Stack.Infrastructure;

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
    public void checkLogFileIsCreated() throws FileNotFoundException {
        new FileReader(logFilename);
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
        assertEquals(messages[0], logMessages.get(0).getMessage());
        assertEquals(messages[1], logMessages.get(1).getMessage());
    }

    @Test
    public void checkLogMessageHasDateTime() {
        String message = "message";
        logger.log(message);
        assertTrue(new Date().after(logger.getLog().get(0).getDateTime()));
    }
}
