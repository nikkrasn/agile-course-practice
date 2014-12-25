package ru.unn.agile.ColorConverter.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        String actualLogMessage = datedTextLogger.getFirstLogMessage();
        assertTrue(actualLogMessage.matches(".*" + message + ".*"));
    }

    @Test
    public void canWriteSeveralLogMessages() {
        String[] messagesToLog = {"first message", "second message"};
        datedTextLogger.addToLog(messagesToLog[0]);
        datedTextLogger.addToLog(messagesToLog[1]);

        List<String> actualLogMessages = datedTextLogger.getLog();

        assertTrue(actualLogMessages.get(0).matches(".*" + messagesToLog[0] + ".*"));
        assertTrue(actualLogMessages.get(1).matches(".*" + messagesToLog[1] + ".*"));
        assertEquals(actualLogMessages.size(), messagesToLog.length);
    }

    @Test
    public void checkLogMessageContainDate() {
        datedTextLogger.addToLog("message without date and time");

        String actualLogMessage = datedTextLogger.getFirstLogMessage();

        String dateRegex = new String("\\d{4}\\.\\d{2}\\.\\d{2}");
        String timeRegex = new String("\\d{2}:\\d{2}:\\d{2}");
        assertTrue(actualLogMessage.matches("^\\[" + dateRegex + " " + timeRegex + "\\].*"));
    }
}
