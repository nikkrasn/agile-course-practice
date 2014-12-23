package ru.unn.agile.Metrics.Infrastructure;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Metrics.viewmodel.ILogger;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;

public class TextLoggerTests {
    private ILogger logger;
    private final String logFilename = "TextLoggerTests.log";

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
}
