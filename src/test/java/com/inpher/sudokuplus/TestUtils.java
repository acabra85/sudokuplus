package com.inpher.sudokuplus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class TestUtils {

    private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);
    //see README.md for description format of expected filename
    public InputStream readSudokuBoardFromFile(String fileName) {
        return TestUtils.class.getClassLoader()
                .getResourceAsStream(fileName);
    }
}
