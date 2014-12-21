package ru.unn.agile.TemperatureConverter.infrastructure;

import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static ru.unn.agile.TemperatureConverter.viewmodel.RegexMatcher.matchesPattern;

public class TxtLoggerTest {
    private static final String FILENAME = "./TxtLoggerTest.log";
    private TxtLogger textLog;

    @Before
    public void setUp() {
        textLog = new TxtLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithUsingFileName() {
        assertNotNull(textLog);
    }

    @Test
    public void canCreateLogFile() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteMessageToLog() {
        String testMessage = "Test message";
        textLog.log(testMessage);
        String message = textLog.getLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteTwoLogMessages() {
        String[] messages = {"First test message", "Second test message"};
        textLog.log(messages[0]);
        textLog.log(messages[1]);
        List<String> realMessages = textLog.getLog();
        assertThat(realMessages.get(0), matchesPattern(".*" + messages[0] + "$"));
        assertThat(realMessages.get(1), matchesPattern(".*" + messages[1] + "$"));
    }

    @Test
    public void doesLoggerWriteDateAndTime() {
        String testMessage = "Test message";
        textLog.log(testMessage);
        String message = textLog.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
