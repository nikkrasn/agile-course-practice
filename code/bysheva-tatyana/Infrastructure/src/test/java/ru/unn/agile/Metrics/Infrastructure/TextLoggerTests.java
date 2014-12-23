package ru.unn.agile.Metrics.Infrastructure;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Metrics.viewmodel.ILogger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

public class TextLoggerTests {
    private ILogger logger;
    private final String logFilename = "TextLoggerTests.log";
    private final SimpleDateFormat dateTimeFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    @Before
    public void setUp() {
        logger = new TextLogger(logFilename);
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(logger);
    }

    @Test
    public void canOpenLogFile() throws FileNotFoundException {
        new FileReader(logFilename);
    }

    @Test
    public void canLogSomething() {
        logger.log("test");
        assertEquals("test", getMessageText(0));
    }

    @Test
    public void canLogSeveralMessages() {
        logger.log("message1");
        logger.log("message2");
        assertEquals("message1", getMessageText(0));
        assertEquals("message2", getMessageText(1));
    }

    @Test
    public void checkLogContainsDateAndTime() throws ParseException {
        logger.log("test");
        assertTrue(new Date().after(getMessageDateTime(0)));
    }

    @Test
    public void checkSecondMessageHasLaterTime() throws ParseException, InterruptedException {
        logger.log("message1");
        Thread.sleep(1000);
        logger.log("message2");

        assertTrue(getMessageDateTime(1).after(getMessageDateTime(0)));
    }

    private Date getMessageDateTime(final Integer index) throws ParseException {
        return dateTimeFormat.parse(getFullMessage(index).split(" : ")[0]);
    }

    private String getMessageText(final Integer index) {
        return getFullMessage(index).split(" : ")[1];
    }

    private String getFullMessage(final Integer index) {
        List<String> log = logger.getLog();
        return log.get(index);
    }
}
