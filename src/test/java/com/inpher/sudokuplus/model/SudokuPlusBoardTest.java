package com.inpher.sudokuplus.model;

import com.inpher.sudokuplus.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class SudokuPlusBoardTest {

    @Test
    public void mustReturnTrueBoardSize4x4_1() {
        InputStream ints = new TestUtils().readSudokuBoardFromFile("sampleInput_4x4.txt");
        SudokuPlusBoard actual = SudokuPlusBoard.fromInputStream(ints);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.isValidSudokuPlus()).isTrue();
    }

    @Test
    public void mustReturnTrueBoardSize9x9_1() {
        InputStream ints = new TestUtils().readSudokuBoardFromFile("sampleInput_9x9.txt");
        SudokuPlusBoard actual = SudokuPlusBoard.fromInputStream(ints);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.isValidSudokuPlus()).isTrue();
    }

    @Test
    public void mustReturnTrueBoardSize9x4_1() {
        InputStream ints = new TestUtils().readSudokuBoardFromFile("sampleInput_9x4.txt");
        SudokuPlusBoard actual = SudokuPlusBoard.fromInputStream(ints);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.isValidSudokuPlus()).isTrue();
    }

    @Test
    public void mustFailBoardSize4x4_1() {
        InputStream ints = new TestUtils().readSudokuBoardFromFile("sampleInput_4x4_invalid1.txt");
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> SudokuPlusBoard.fromInputStream(ints))
                .withMessageContaining("The given columns do not match the board size");
    }

    @Test
    public void mustReturnFalseBoardSize4x4_4() {
        InputStream ints = new TestUtils().readSudokuBoardFromFile("sampleInput_4x4_invalid4.txt");
        SudokuPlusBoard underTest = SudokuPlusBoard.fromInputStream(ints);
        Assertions.assertThat(underTest)
                .isNotNull();
        Assertions.assertThat(underTest.isValidSudokuPlus()).isFalse();
    }

    @Test
    public void mustReturnFalseBoardSize4x4_5() {
        InputStream ints = new TestUtils().readSudokuBoardFromFile("sampleInput_4x4_invalid5.txt");
        SudokuPlusBoard underTest = SudokuPlusBoard.fromInputStream(ints);
        Assertions.assertThat(underTest)
                .isNotNull();
        Assertions.assertThat(underTest.isValidSudokuPlus()).isFalse();
    }

    @Test
    public void mustReturnFalseBoardSize4x4_6() {
        InputStream ints = new TestUtils().readSudokuBoardFromFile("sampleInput_4x4_invalid6.txt");
        SudokuPlusBoard underTest = SudokuPlusBoard.fromInputStream(ints);
        Assertions.assertThat(underTest)
                .isNotNull();
        Assertions.assertThat(underTest.isValidSudokuPlus()).isFalse();
    }

    @Test
    public void mustFailBoardSize4x4_2() {
        InputStream ints = new TestUtils().readSudokuBoardFromFile("sampleInput_4x4_invalid2.txt");
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> SudokuPlusBoard.fromInputStream(ints))
                .withMessageContaining("Error the given file contains invalid digits at");
    }

    @Test
    public void mustFailBoardSize4x4_3() {
        InputStream ints = new TestUtils().readSudokuBoardFromFile("sampleInput_4x4_invalid3.txt");
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> SudokuPlusBoard.fromInputStream(ints))
                .withMessageContaining("Total columns must be perfect square");
    }
}