package ru.unn.agile.BinaryTree.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static ru.unn.agile.BinaryTree.infrastructure.RegexMatches.matchesPattern;

public class TxtLoggerTests {
    private static final String FILENAME = "./Logger.log";
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
    public void canCreateLogFile() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " not found!");
        }
    }

    @Test
    public void canWriteMessageInLog() {
        String testMessage = "Testing message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteMoreLogMessage() {
        String[] messages = {"Testing Message One", "Testing Message Two", "Testing Message Three"};

        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);
        txtLogger.log(messages[2]);

        List<String> actualMessages = txtLogger.getLog();
        for (int j = 0; j < actualMessages.size(); j++) {
            assertThat(actualMessages.get(j), matchesPattern(".*" + messages[j] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Testing message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2} : .*"));
    }
}
