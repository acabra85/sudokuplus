package com.inpher.sudokuplus.parse;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardParser {

    public static BoardParseResponse parseBoardFromFile(String filename) throws FileNotFoundException {
        final File file = new File(filename);
        if (file.exists() && file.isFile()) {
            return parseBoard(new FileInputStream(file));
        }
        return BoardParseResponse.fail(ParseResult.INVALID_FILE, "File does not exist: " + filename);
    }

    private static BoardParseResponse parseBoard(InputStream is) {
        Objects.requireNonNull(is);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(is))) {
            String line = bf.readLine();
            if(line.startsWith("#")) {
                line = bf.readLine();
            }
            String[] rowStr = line.split(",");
            int width = rowStr.length;
            if (notPerfectSquare(width)) {
                String reason = "Total columns must be perfect square: " + width;
                return BoardParseResponse.fail(ParseResult.WIDTH_NOT_SQUARE, reason);
            }
            List<int[]> rows = new ArrayList<>();
            AtomicInteger rowNum = new AtomicInteger(0);
            RowParseResponse rowParseResponse = readRow(rowNum.getAndIncrement(), rowStr, width);
            if(ParseResult.SUCCESS != rowParseResponse.result) {
                return BoardParseResponse.fail(rowParseResponse);
            }
            rows.add(rowParseResponse.row);
            while ((line = bf.readLine()) != null) {
                rowStr = line.split(",");
                if (rowStr.length != width) {
                    String reason = "The given columns do not match the board size";
                    return BoardParseResponse.fail(ParseResult.WIDTH_MISMATCH, reason);
                }
                rowParseResponse = readRow(rowNum.getAndIncrement(), rowStr, width);
                if(ParseResult.SUCCESS != rowParseResponse.result) {
                    return BoardParseResponse.fail(rowParseResponse);
                }
                rows.add(rowParseResponse.row);
            }
            if (notPerfectSquare(rowNum.get())) {
                String reason = "Total rows must be perfect square given: " + rowNum.get();
                return BoardParseResponse.fail(ParseResult.HEIGHT_NOT_SQUARE, reason);
            }
            int[][] board = new int[rowNum.get()][width];
            for (int i = 0; i < rowNum.get(); i++) {
                board[i] = rows.get(i);
            }
            return BoardParseResponse.ok(board);
        }
        catch (Exception e) { return BoardParseResponse.fail(ParseResult.INVALID_FILE, e.getMessage());}
    }

    static RowParseResponse readRow(int rowNum, String[] rowStr, int cols) {
        int[] row = new int[cols];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < cols; i++) {
            try {
                int num = Integer.parseInt(rowStr[i]);
                if(set.contains(num)) {
                    String reason = String.format("Row %d contains duplicate [%d] at idx %d ", rowNum, num, i);
                    return RowParseResponse.fail(ParseResult.DUPLICATE, reason);
                }
                row[i] = num;
                set.add(num);
            } catch (NumberFormatException nfe) {
                String reason = String.format("Error the given file contains invalid digits at [%d, %d] given: %s",
                        rowNum + 1, i + 1, rowStr[i]);
                return RowParseResponse.fail(ParseResult.NON_DIGITS_FOUND, reason);
            }
        }
        return RowParseResponse.ok(row);
    }

    static boolean notPerfectSquare(int num) {
        if (num >= 0) {
            int root = (int) Math.sqrt(num);
            return ((root * root) != num);
        }
        return true;
    }
}
