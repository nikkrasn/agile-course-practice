package ru.unn.agile.StatisticalValues.model;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.StatisticalValues.Infrastructure.SimpleTxtLogger;
import ru.unn.agile.StatisticalValues.viewmodel.ILogger;

import java.io.FileNotFoundException;
import java.io.FileReader;

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
}
