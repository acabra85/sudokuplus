package com.inpher.sudoku.model;

import com.inpher.sudoku.BoardUtils;

public class SudokuBoard {

    public final int N;
    private final int[][] board;

    protected SudokuBoard(int[][] board) {
        this.N = board.length;
        this.board = board;
    }

    public static SudokuBoard of(String fileName) {
        int[][] board = BoardUtils.parseBoard(fileName);
        return new SudokuBoard(board);
    }


    public boolean isValidSudokuPlus() {

        //validate columns
        for (int i = 0; i < N; i++) {
            if(!validLine(board[0])) {
                return false;
            }
        }

        //validate rows
        for (int i = 0; i < N; i++) {
            int[] row = new int[N];
            if(!validLine(row)) {
                return false;
            }
        }

        //valid sub-areas
        return true;
    }

    private boolean validLine(int[] col) {
        return false;
    }
}
