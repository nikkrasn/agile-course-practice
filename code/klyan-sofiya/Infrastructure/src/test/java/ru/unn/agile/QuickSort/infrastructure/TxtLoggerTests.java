package ru.unn.agile.QuickSort.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.unn.agile.QuickSort.infrastructure.RegexpMatcher.havePatternMatch;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLogger_Tests-lab3.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAME);
    }

    @Test
    public void ableToCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void ableToCreateLogFileOnDisk() {
        BufferedReader reader = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(FILENAME);
            reader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            fail("Log file " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void ableToWriteLogMessage() {
        String testMessage = "Log test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, havePatternMatch(".*" + testMessage + "$"));
    }

    @Test
    public void ableToWriteSeveralLogMessage() {
        String[] messages = {"Log test message 1", "Log test message 2"};

        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);

        List<String> actualMessages = txtLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertThat(actualMessages.get(i), havePatternMatch(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void containsDateAndTime() {
        String testMessage = "Log test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, havePatternMatch("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
