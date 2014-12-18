package ru.unn.agile.Matrix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.io.*;
import java.util.List;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLoggerTests.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Message";

        txtLogger.createLog(testMessage);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Message 1", "Message 2"};

        txtLogger.createLog(messages[0]);
        txtLogger.createLog(messages[1]);

        List<String> actualMessages = txtLogger.getLog();
        assertTrue(actualMessages.get(0).matches(".*" + messages[0] + "$"));
        assertTrue(actualMessages.get(1).matches(".*" + messages[1] + "$"));
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Message";

        txtLogger.createLog(testMessage);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches("^\\d{4}-\\d{2}-\\d{2} \\[\\d{2}:\\d{2}:\\d{2}\\]: .*"));
    }
}
