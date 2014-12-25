package ru.unn.agile.StatisticalValues.model;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.StatisticalValues.infrastructure.SimpleTxtLogger;
import ru.unn.agile.StatisticalValues.viewmodel.ILogger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class SimpleTxtLoggerTests {
    private ILogger logger;
    private final String logFile = "LoggerTestFile.log";

    @Before
    public void setUp() {
        logger = new SimpleTxtLogger(logFile);
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(logger);
    }

    @Test
    public void openLogFileExists() throws FileNotFoundException {
        new FileReader(logFile);
    }

    @Test
    public void checkLogNotNull() throws FileNotFoundException {
        assertNotNull(logger.getLog());
    }

    @Test
    public void canLogSomeMessage() {
        logger.log("testmessage");
        assertEquals("testmessage", logger.getLoggedMessageText(0));
    }

    @Test
    public void canLogMoreThanOneMessage() {
        logger.log("testmessage1");
        logger.log("testmessage2");
        assertEquals("testmessage2", logger.getLoggedMessageText(1));
    }

    @Test
    public void canLogMessageDate() throws ParseException{
        logger.log("testdatemessage");
        assertTrue(new Date().after(logger.getLoggedMessageDate(0)));
    }

    @Test
    public void checkMessageDateNotEquals() throws ParseException, InterruptedException{
        logger.log("testdatemessage1");
        Thread.sleep(1000);
        logger.log("testdatemessage2");
        assertTrue(logger.getLoggedMessageDate(1).after(logger.getLoggedMessageDate(0)));
    }
}
