package ru.unn.agile.AreaConverter.Infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.unn.agile.AreaConverter.viewmodel.ACRegexMatcher.matchesPattern;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLoggerTests.log";
    private TxtLoggerAreaConverter txtLoggerForAreaConverter;

    @Before
    public void setUp() {
        txtLoggerForAreaConverter = new TxtLoggerAreaConverter(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLoggerForAreaConverter);
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
        String testMessage = "Test message";

        txtLoggerForAreaConverter.log(testMessage);

        String message = txtLoggerForAreaConverter.getLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        txtLoggerForAreaConverter.log(messages[0]);
        txtLoggerForAreaConverter.log(messages[1]);

        List<String> actualMessages = txtLoggerForAreaConverter.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertThat(actualMessages.get(i), matchesPattern(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        txtLoggerForAreaConverter.log(testMessage);

        String message = txtLoggerForAreaConverter.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
