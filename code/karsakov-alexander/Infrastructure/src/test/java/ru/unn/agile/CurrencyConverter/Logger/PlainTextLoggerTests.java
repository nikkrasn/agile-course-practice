package ru.unn.agile.CurrencyConverter.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PlainTextLoggerTests {
    private static final String TEST_LOG_FILENAME = "./test_log.log";
    private PlainTextLogger logger;

    @Before
    public void setUp() {
        logger = new PlainTextLogger(TEST_LOG_FILENAME);
    }

    @After
    public void CleanUp() {
        logger = null;
    }

    @Test
    public void canCreateLoggerWithCorrectFileName() {
        assertNotNull(logger);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionWithNullFilename() {
        new PlainTextLogger(null);
    }

    @Test
    public void loggerCreateLogFileOnDisk() {
        try {
            new FileReader(TEST_LOG_FILENAME);
        } catch (FileNotFoundException e) {
            fail("File " + TEST_LOG_FILENAME + " wasn't found!");
        }
    }

    @Test
    public void canGetCorrectLastLogMessage() {
        String testMessage = "test event";

        logger.logEvent(testMessage);

        String message = logger.getLastLogMessage();
        assertNotEquals(message.indexOf(testMessage), -1);
    }

    @Test
    public void canWriteEventLogMessage() {
        logger.logEvent("test me");

        String message = logger.getLastLogMessage();
        assertNotEquals(message.indexOf("Event: "), -1);
    }

    @Test
    public void canWriteErrorLogMessage() {
        logger.logError("test me");

        String message = logger.getLastLogMessage();
        assertNotEquals(message.indexOf("Error: "), -1);
    }

    @Test
    public void canWriteSeveralLogMessages() {
        String[] messages = {"test me", "and again"};
        logger.logEvent(messages[0]);
        logger.logError(messages[1]);

        List<String> actualMessages = logger.getFullLog();
        assertEquals(actualMessages.size(), messages.length);
        assertNotEquals(actualMessages.get(0).indexOf(messages[0]), -1);
        assertNotEquals(actualMessages.get(1).indexOf(messages[1]), -1);
    }

    @Test
    public void logContainsTimestamp() {
        logger.logEvent("test me");

        String message = logger.getLastLogMessage();
        assertTrue(message.matches("^\\[\\d{4}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2}:\\d{2}\\].*"));
    }
}