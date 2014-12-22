package ru.unn.agile.NumberInPositionalNotation.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.unn.agile.NumberInPositionalNotation.infrastructure.RegexMatcher.matchesPattern;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLogger_Tests.log";
    private TxtLogger testTxtLogger;

    @Before
    public void setUp() {
        testTxtLogger = new TxtLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(testTxtLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() throws FileNotFoundException {
            new BufferedReader(new FileReader(FILENAME));
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "1-st message";

        testTxtLogger.log(testMessage);

        String message = testTxtLogger.getLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "1-st message";

        testTxtLogger.log(testMessage);

        String message = testTxtLogger.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"1-st message", "2-nd message"};

        testTxtLogger.log(messages[0]);
        testTxtLogger.log(messages[1]);

        List<String> logMessages = testTxtLogger.getLog();
        assertThat(logMessages.get(0), matchesPattern(".*" + messages[0] + "$"));
        assertThat(logMessages.get(1), matchesPattern(".*" + messages[1] + "$"));
    }
}
