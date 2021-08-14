package com.inpher.sudokuplus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardUtils {

    private static final Logger logger = LoggerFactory.getLogger(BoardUtils.class);

    public static int[][] parseBoardFromStream(InputStream inputStream) {
        return parseBoard(inputStream);
    }

    public static int[][] parseBoardFromFile(String filename) throws FileNotFoundException {
        File boardDef = new File(filename);
        return parseBoard(new FileInputStream(boardDef));
    }

    private static int[][] parseBoard(InputStream is) {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(is))) {
            String line = bf.readLine();
            if(line.startsWith("#")) {
                line = bf.readLine();
            }
            String[] rowStr = line.split(",");
            int cols = rowStr.length;
            if (notPerfectSquare(cols)) {
                throw new IllegalArgumentException("Total columns must be perfect square: " + cols);
            }
            List<int[]> rows = new ArrayList<>();
            AtomicInteger rowNum = new AtomicInteger(0);
            rows.add(readRow(rowNum.getAndIncrement(), rowStr, cols));
            while ((line = bf.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length != cols) {
                    throw new IllegalArgumentException("The given columns do not match the board size");
                }
                rows.add(readRow(rowNum.getAndIncrement(), arr, cols));
            }
            if (notPerfectSquare(rowNum.get())) {
                throw new IllegalArgumentException("Total rows must be perfect square given: " + (rowNum.get()));
            }
            int[][] board = new int[rowNum.get()][cols];
            for (int i = 0; i < rowNum.get(); i++) {
                board[i] = rows.get(i);
            }
            return board;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to read the file: " + e.getMessage());
        }
    }

    static int[] readRow(int rowNum, String[] rowStr, int cols) {
        int[] row = new int[cols];
        for (int i = 0; i < cols; i++) {
            try {
                row[i] = Integer.parseInt(rowStr[i]);
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException(String.format("Error the given file contains invalid digits at [%d, %d] given: %s",
                        rowNum + 1, i + 1, rowStr[i]));
            }
        }
        return row;
    }

    static boolean notPerfectSquare(int num) {
        if (num >= 0) {
            int root = (int) Math.sqrt(num);
            return ((root * root) != num);
        }
        return false;
    }
}
