package com.inpher.sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardUtils {

    private static final Logger logger = LoggerFactory.getLogger(BoardUtils.class);

    public static int[][] parseBoard(String filename) {
        File boardDef = new File(filename);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(boardDef)))) {
            String line = bf.readLine();
            String[] rowStr = line.split(",");
            int cols = rowStr.length;
            if (notPowerOfTwo(cols)) {
                throw new InputMismatchException("Columns must be a power of two given: " + cols);
            }
            List<int[]> rows = new ArrayList<>();
            AtomicInteger rowNum = new AtomicInteger(0);
            rows.add(readRow(rowNum.getAndIncrement(), rowStr, cols));
            while((line = bf.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length != cols) {
                    throw new InputMismatchException("The given columns do not match the board size");
                }
                rows.add(readRow(rowNum.getAndIncrement(), arr, cols));
            }
            if(rowNum.get() != cols) {
                throw new InputMismatchException(String.format("Both sides must be equal [NxN] given: [%d x %d] ",
                        rowNum.get(), cols));
            }
            int[][] board = new int[rowNum.get()][cols];
            for (int i = 0; i < rowNum.get(); i++) {
                board[i] = rows.get(i);
            }
            return board;
        } catch (IOException e) {
            logger.error("Failed to read the file: "  + filename, e);
        }
        throw new NullPointerException("Unable to build the board from given file: " + filename);
    }

    static int[] readRow(int rowNum, String[] rowStr, int cols) {
        int[] row = new int[cols];
        for (int i = 0; i < cols; i++) {
            try {
                row[i] = Integer.parseInt(rowStr[i]);
            } catch (NumberFormatException nfe) {
                logger.error(String.format("Error the given file contains invalid digits at [%d, %d] given: %s",
                        rowNum + 1, i + 1, rowStr[i]))  ;
            }
        }
        return row;
    }

    static boolean notPowerOfTwo(int n) {
        return (n == 0) || !((n & (n - 1)) == 0);
    }
}
