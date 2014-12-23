package ru.unn.agile.Vector3D.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static ru.unn.agile.Vector3D.infrastructure.RegexMatcher.matchesPattern;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;

public class TxtLoggerTests {
    private static final String FILENAMELOG = "./TxtLogger_Tests_lab3.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAMELOG);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFile() throws FileNotFoundException {
        FileReader fileReader = new FileReader(FILENAMELOG);
        BufferedReader reader = new BufferedReader(fileReader);
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern("(.*)" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messageList = {"Test message 1", "Test message 2"};

        txtLogger.log(messageList[0]);
        txtLogger.log(messageList[1]);

        List<String> actualMessages = txtLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertThat(actualMessages.get(i), matchesPattern("(.*)" + messageList[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
