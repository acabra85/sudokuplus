package com.inpher.sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestUtils {

    private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);
    //see README.md for description format of expected filename
    public int[][] readSudokuBoardFromFile(String fileName) {
        InputStream resourceAsStream = TestUtils.class.getClassLoader()
                .getResourceAsStream(fileName);
        if (resourceAsStream != null) {
            try (BufferedReader bf = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8.name()))) {
                String line = bf.readLine();
                String[] rowStr = line.split(",");
                int cols = rowStr.length;
                if (BoardUtils.notPowerOfTwo(cols)) {
                    throw new InputMismatchException("Columns must be a power of two given: " + cols);
                }
                List<int[]> rows = new ArrayList<>();
                AtomicInteger rowNum = new AtomicInteger(0);
                rows.add(BoardUtils.readRow(rowNum.getAndIncrement(), rowStr, cols));
                while((line = bf.readLine()) != null) {
                    String[] arr = line.split(",");
                    if (arr.length != cols) {
                        throw new InputMismatchException(String.format("The given columns do not match the board size expected [%d] given: %d",
                                cols, arr.length));
                    }
                    rows.add(BoardUtils.readRow(rowNum.getAndIncrement(), arr, cols));
                }
                if(BoardUtils.notPowerOfTwo(rowNum.get())) {
                    throw new InputMismatchException("Rows must be a power of two given: " + rowNum.get());
                }
                int[][] board = new int[rowNum.get()][cols];
                for (int i = 0; i < rowNum.get(); i++) {
                    board[i] = rows.get(i);
                }
                return board;
            } catch (IOException e) {
                logger.error("Failed to read the file: "  + fileName, e);
            }
            throw new NullPointerException("Unable to build the board from given file: " + fileName);
        }
        throw new NullPointerException();
    }
}
