package com.inpher.sudokuplus.parse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class BoardParserTest {

    @Test
    void mustReturnTrueAllPerfectSquares() {
        Assertions.assertThat(IntStream.range(4, 100)
                .map(n -> n * n)
                .noneMatch(BoardParser::notPerfectSquare)).isTrue();
    }

    @Test
    void mustReturnFalseNotPerfectSquares() {
        Assertions.assertThat(IntStream.of(-1, 12, 15, 19)
                .allMatch(BoardParser::notPerfectSquare)).isTrue();
    }
}