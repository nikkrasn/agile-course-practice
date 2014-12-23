package ru.unn.agile.Dichotomy.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.unn.agile.Dichotomy.infrastructure.RegexMatcher.matchesPattern;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLogger_Tests-lab3-dichotomy.log";
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
    public void canCreateLogFileOnDisk() throws FileNotFoundException {
        new BufferedReader(new FileReader(FILENAME));
    }

    @Test
    public void canWriteLogMessage() {
        String messageForTest = "Test message";

        txtLogger.log(messageForTest);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern(".*" + messageForTest + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messageForTest = {"Test message 1", "Test message 2"};

        txtLogger.log(messageForTest[0]);
        txtLogger.log(messageForTest[1]);

        List<String> actualMessages = txtLogger.getLog();
        assertThat(actualMessages.get(0), matchesPattern(".*" + messageForTest[0] + "$"));
        assertThat(actualMessages.get(1), matchesPattern(".*" + messageForTest[1] + "$"));
    }

    @Test
    public void doesLogContainDateAndTime() {
        String messageForTest = "Test message";

        txtLogger.log(messageForTest);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2} - .*"));
    }
}
