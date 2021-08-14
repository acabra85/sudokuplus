package com.inpher.sudokuplus.model;

import com.inpher.sudokuplus.BoardUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class SudokuPlusBoard {

    private static final Logger logger = LoggerFactory.getLogger(SudokuPlusBoard.class);

    public final int height;
    private final int sqrtH; //square root of number of rows

    public final int width;
    private final int sqrtW; //square root of the number of columns

    private final int[][] board;
    private final int regionSize;
    private final int regionCount;

    protected SudokuPlusBoard(int[][] board) {
        this.width = board[0].length;
        this.height = board.length;
        this.sqrtH = (int) Math.sqrt(height);
        this.sqrtW = (int) Math.sqrt(width);
        this.regionSize = sqrtH * sqrtW;
        this.regionCount = (width * height) / regionSize;
        this.board = board;
    }

    static SudokuPlusBoard fromInputStream(InputStream ints) throws NullPointerException {
        int[][] board = BoardUtils.parseBoardFromStream(ints);
        return new SudokuPlusBoard(board);
    }

    public static SudokuPlusBoard fromFile(String fileName) throws FileNotFoundException {
        int[][] board = BoardUtils.parseBoardFromFile(fileName);
        return new SudokuPlusBoard(board);
    }


    private boolean validRegions() {
        logger.debug("Validating Regions ... ");
        int x = 0;
        int y = 0;
        for (int r = 0; r < regionCount; ++r) {
            if (r > 0 && r % sqrtW == 0) {
                x = 0;
                y += sqrtH;
            }
            if(invalidRegion(r, x, y)) {
                return false;
            }
            x += sqrtW;
        }
        return true;
    }


    private boolean invalidRegion(int r, int x, int y) {
        Set<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder("region:" + r + " [");
        int idx = 0;
        for (int i = 0, rx = x; i < sqrtW; ++i, ++rx) {
            for (int j = 0, ry = y; j < sqrtH; ++j, ++ry) {
                int num =  board[ry][rx];
                sb.append(num);
                if(idx < regionSize - 1) sb.append(",");
                if(set.contains(num)) {
                    logger.debug(sb + "]");
                    logger.info(String.format("Invalid Board Duplicate detected [%d] in REGION: %d", num, r));
                    return true;
                }
                set.add(num);
                ++idx;
            }
        }
        logger.debug(sb + "]");
        return false;
    }

    private boolean validRows() {
        logger.debug("Validating Rows ... ");
        for (int i = 0; i < height; i++) {
            logger.debug(String.format("row: %d -> %s", i + 1, Arrays.toString(board[i])));
            if (invalidLine(board[i], width, "Row", width)) {
                return false;
            }
        }
        return true;
    }

    private boolean validColumns() {
        logger.debug("Validating Columns ... ");
        for (int i = 0; i < width; i++) {
            int[] col = buildCol(i);
            logger.debug(String.format("col: %d -> %s", i + 1, Arrays.toString(col)));
            if (invalidLine(col, width, "Column", i)) {
                return false;
            }
        }
        return true;
    }

    private int[] buildCol(int idx) {
        int[] col = new int[width];
        for (int i = 0; i < width; i++) {
            col[i] = board[i][idx];
        }
        return col;
    }

    private boolean invalidLine(int[] col, int N, String id, int idx) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int num = col[i];
            if (set.contains(num)) {
                logger.info(String.format("Invalid Board duplicate detected [%d] in %s: %d]", num, id, idx));
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public boolean isValidSudokuPlus() {
        return validRows() && validColumns() && validRegions();
    }
}
