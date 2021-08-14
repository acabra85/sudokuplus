package com.inpher.sudoku.model;

import com.inpher.sudoku.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class SudokuBoardTest {

    @Test @Disabled
    public void mustReturnBoardSize4x4_1() {
        int[][] ints = new TestUtils().readSudokuBoardFromFile("sampleInput_4x4.txt");
        SudokuBoard underTest = new SudokuBoard(ints);
        Assertions.assertThat(underTest).isNotNull();
        Assertions.assertThat(underTest.isValidSudokuPlus())
                .isTrue();
    }
}