package ru.unn.agile.Triangle.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.unn.agile.Triangle.Infrastructure.ExpressionMatcher.matchesPattern;

public class RecordsLoggerTest {
    private static final String LOGFILE = "./Triangle_Logger.log";
    private RecordsLogger recordsLogger;

    @Before
    public void setUp() {
        recordsLogger = new RecordsLogger(LOGFILE);
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(recordsLogger);
    }

    @Test
    public void canCreateLogFile() throws FileNotFoundException {
        new BufferedReader(new FileReader(LOGFILE));
    }

    @Test
    public void canRecord() {
        String helloMessage = "Say hello to logger";

        recordsLogger.logRecord(helloMessage);
        String message = recordsLogger.getLogRecords().get(0);

        assertThat(message, matchesPattern(".*" + helloMessage + "$"));
    }

    @Test
    public void canRecordSeveralTimes() {
        String[] records = {"Hello", "Hi"};

        recordsLogger.logRecord(records[0]);
        recordsLogger.logRecord(records[1]);
        List<String> actualMessages = recordsLogger.getLogRecords();

        assertThat(actualMessages.get(0), matchesPattern(".*" + records[0] + "$"));
        assertThat(actualMessages.get(1), matchesPattern(".*" + records[1] + "$"));
    }

    @Test
    public void checkTime() {
        String testMessage = "What time is it?";

        recordsLogger.logRecord(testMessage);
        String message = recordsLogger.getLogRecords().get(0);

        assertThat(message, matchesPattern("^\\d{2}.\\d{2}.\\d{4} - \\d{2}:\\d{2}:\\d{2} => .*"));
    }
}
