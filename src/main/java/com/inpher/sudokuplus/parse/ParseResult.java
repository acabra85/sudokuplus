package com.inpher.sudokuplus.parse;

public enum ParseResult {
    SUCCESS,
    DUPLICATE,
    WIDTH_NOT_SQUARE,
    HEIGHT_NOT_SQUARE,
    INVALID_FILE,
    NON_DIGITS_FOUND, WIDTH_MISMATCH;
}
