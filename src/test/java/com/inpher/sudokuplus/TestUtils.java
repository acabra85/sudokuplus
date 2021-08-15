package com.inpher.sudokuplus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class TestUtils {

    private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);
    //see README.md for description format of expected filename
    public static String getFullPathForResourceFile(String fileName) {
        return Objects.requireNonNull(TestUtils.class.getClassLoader().getResource(fileName)).getFile();
    }
}
