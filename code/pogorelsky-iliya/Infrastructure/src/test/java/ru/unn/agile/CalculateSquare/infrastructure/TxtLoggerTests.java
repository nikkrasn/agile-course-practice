package ru.unn.agile.CalculateSquare.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static junit.framework.TestCase.assertNotNull;
import static ru.unn.agile.CalculateSquare.infrastructure.RegConform.matchesPattern;

public class TxtLoggerTests {
    private static final String FNAME = "./TxtLogTest.log";
    private TextLog txtLog;

    @Before
    public void setUp() {
        txtLog = new TextLog(FNAME);
    }

    @Test
    public void canCreateLogWithFileName() {
        assertNotNull(txtLog);
    }

    @Test
    public void canCreateLogFileOnDisk() throws FileNotFoundException {
        new BufferedReader(new FileReader(FNAME));
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        txtLog.log(testMessage);

        String message = txtLog.getLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteFewLogMessage() {
        String[] messages = {"Message 1", "Message 2"};

        txtLog.log(messages[0]);
        txtLog.log(messages[1]);
        
        List<String> nowMessages = txtLog.getLog();
        assertThat(nowMessages.get(0), matchesPattern(".*" + messages[0] + "$"));
        assertThat(nowMessages.get(1), matchesPattern(".*" + messages[1] + "$"));
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        txtLog.log(testMessage);

        String message = txtLog.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
