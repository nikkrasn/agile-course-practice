package ru.unn.agile.ConverterWeight.Infrastructure;

import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static ru.unn.agile.ConverterWeight.viewmodel.RegexMatcher.matchesPattern;

public class LoggerTxtTests {
    private static final String FILENAME = "./TxtLoggerTests-Serov.log";
    private LoggerTxt loggerTxt;

    @Before
    public void setUp() {
        loggerTxt = new LoggerTxt(FILENAME);
    }
    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(loggerTxt);
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
    public void canWriteMessageInLog() {
        String testMessage = "Test message";
        loggerTxt.log(testMessage);
        String message = loggerTxt.getLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String message1 = "Test message 1";
        String message2 = "Test message 2";
        loggerTxt.log(message1);
        loggerTxt.log(message2);
        List<String> actualMessages = loggerTxt.getLog();
        assertThat(actualMessages.get(0), matchesPattern(".*" + message1 + "$"));
        assertThat(actualMessages.get(1), matchesPattern(".*" + message2 + "$"));
    }

    @Test
    public void canWritesTimeInLog() {
        String testMessage = "Test message";
        loggerTxt.log(testMessage);
        String message = loggerTxt.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
