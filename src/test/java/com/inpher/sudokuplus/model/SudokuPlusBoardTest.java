package com.inpher.sudokuplus.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.inpher.sudokuplus.TestUtils.getFullPathForResourceFile;

class SudokuPlusBoardTest {

    @Test
    public void mustReturnTrueBoardSize4x4_1() {
        String fileName = getFullPathForResourceFile("sampleInput_4x4.txt");
        SudokuPlusBoard actual = SudokuPlusBoard.fromFile(fileName);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.isValidSudokuPlus()).isTrue();
    }

    @Test
    public void mustReturnTrueBoardSize4x4_2() {
        String fileName = getFullPathForResourceFile("sampleInput_4x4_comments.txt");
        SudokuPlusBoard actual = SudokuPlusBoard.fromFile(fileName);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.isValidSudokuPlus()).isTrue();
    }

    @Test
    public void mustReturnTrueBoardSize9x9_1() {
        String fileName = getFullPathForResourceFile("sampleInput_9x9.txt");
        SudokuPlusBoard actual = SudokuPlusBoard.fromFile(fileName);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.isValidSudokuPlus()).isTrue();
    }

    @Test
    public void mustReturnTrueBoardSize9x4_1() {
        String fileName = getFullPathForResourceFile("sampleInput_9x4.txt");
        SudokuPlusBoard actual = SudokuPlusBoard.fromFile(fileName);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.isValidSudokuPlus()).isTrue();
    }

    @Test
    public void mustReturnNullNonExistentFile() {
        Assertions.assertThat(SudokuPlusBoard.fromFile("myNonExistentFile")).isNull();
    }

    @Test
    public void mustReturnNullFolderGiven() {
        Assertions.assertThat(SudokuPlusBoard.fromFile("classes")).isNull();
    }

    @Test
    public void mustReturnNullInvalidFile() {
        String fileName = getFullPathForResourceFile("invalidFile.txt");
        Assertions.assertThat(SudokuPlusBoard.fromFile(fileName)).isNull();
    }

    @Test
    public void mustReturnNullBoardSize4x5_1() {
        String fileName = getFullPathForResourceFile("sampleInput_4x5_invalid1.txt");
        Assertions.assertThat(SudokuPlusBoard.fromFile(fileName)).isNull();
    }

    @Test
    public void mustReturnNullBoardSize4x4_1() {
        String fileName = getFullPathForResourceFile("sampleInput_4x4_invalid1.txt");
        Assertions.assertThat(SudokuPlusBoard.fromFile(fileName)).isNull();
    }

    @Test
    public void mustReturnNullBoardSize4x4_4() {
        String fileName = getFullPathForResourceFile("sampleInput_4x4_invalid4.txt");
        SudokuPlusBoard underTest = SudokuPlusBoard.fromFile(fileName);
        Assertions.assertThat(underTest).isNull();
    }

    @Test
    public void mustReturnNullBoardSize4x4_2() {
        String fileName = getFullPathForResourceFile("sampleInput_4x4_invalid2.txt");
        Assertions.assertThat(SudokuPlusBoard.fromFile(fileName)).isNull();
    }

    @Test
    public void mustReturnNullBoardSize4x4_3() {
        String fileName = getFullPathForResourceFile("sampleInput_4x4_invalid3.txt");
        Assertions.assertThat(SudokuPlusBoard.fromFile(fileName)).isNull();
    }

    @Test
    public void mustReturnFalseBoardSize4x4_5() {
        String fileName = getFullPathForResourceFile("sampleInput_4x4_invalid5.txt");
        SudokuPlusBoard underTest = SudokuPlusBoard.fromFile(fileName);
        Assertions.assertThat(underTest)
                .isNotNull();
        Assertions.assertThat(underTest.isValidSudokuPlus()).isFalse();
    }

    @Test
    public void mustReturnFalseBoardSize4x4_6() {
        String fileName = getFullPathForResourceFile("sampleInput_4x4_invalid6.txt");
        SudokuPlusBoard underTest = SudokuPlusBoard.fromFile(fileName);
        Assertions.assertThat(underTest)
                .isNotNull();
        Assertions.assertThat(underTest.isValidSudokuPlus()).isFalse();
    }

    @Test
    public void mustReturnFalseInvalidRows() {
        int[][] board = {
                {1, 2, 3},
                {4, 5, 4},
                {7, 8, 9}
        };
        Assertions.assertThat(new SudokuPlusBoard(board).isValidSudokuPlus())
                .isFalse();
    }
}