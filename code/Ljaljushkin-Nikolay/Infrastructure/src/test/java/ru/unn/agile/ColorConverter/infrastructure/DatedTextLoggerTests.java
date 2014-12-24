package ru.unn.agile.ColorConverter.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static junit.framework.TestCase.assertNotNull;

public class DatedTextLoggerTests {
    private static final String FILENAME = "./DateTextLogger_Tests.log";
    private DatedTextLogger logger;

    @Before
    public void setUp() {
        logger = new DatedTextLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(logger);
    }

    @Test
    public void checkLogFileIsCreated() throws FileNotFoundException {
        new FileReader(FILENAME);
    }

}
