package ru.unn.agile.DemandElasticity.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static ru.unn.agile.DemandElasticity.infrastructure.RegularExpressionMatcher.matchesPattern;

public class LoggerToTxtTest {
    private static final String FILE_NAME = "./LoggerToTxt_Tests.log";
    private LoggerToTxt loggerToTxt;

    @Before
    public void setUp() {
        loggerToTxt = new LoggerToTxt(FILE_NAME);
    }

    @After
    public void cleanUp() {
        loggerToTxt = null;
    }

    @Test
    public void canCreateLoggerWithCorrectFileName() {
        assertNotNull(loggerToTxt);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionOnNullFilName() {
        new LoggerToTxt(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionOnEmptyFilName() {
        new LoggerToTxt("");
    }

    @Test
    public void canCreateFileForLoggingOnDisk() throws FileNotFoundException {
        new BufferedReader(new FileReader(FILE_NAME));
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        loggerToTxt.logMessage(testMessage);

        String message = loggerToTxt.getFullLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message #1", "Test message #2"};

        loggerToTxt.logMessage(messages[0]);
        loggerToTxt.logMessage(messages[1]);
        List<String> writtenMessages = loggerToTxt.getFullLog();

        assertEquals(2, writtenMessages.size());
        assertThat(writtenMessages.get(0), matchesPattern(".*" + messages[0] + "$"));
        assertThat(writtenMessages.get(1), matchesPattern(".*" + messages[1] + "$"));
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        loggerToTxt.logMessage(testMessage);

        String message = loggerToTxt.getFullLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
