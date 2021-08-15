package com.inpher.sudokuplus.parse;

public class BoardParseResponse {
    public final int[][] board;
    public final ParseResult result;
    private final String reason;

    private BoardParseResponse(int[][] board, ParseResult result, String reason) {
        this.board = board;
        this.result = result;
        this.reason = reason;
    }

    public static BoardParseResponse ok(int[][] board) {
        return new BoardParseResponse(board, ParseResult.SUCCESS, "VALID SUDOKU BOARD");
    }

    public static BoardParseResponse fail(ParseResult result, String reason) {
        return new BoardParseResponse(null, result, reason);
    }

    public static BoardParseResponse fail(RowParseResponse rowParseResponse) {
        return new BoardParseResponse(null, rowParseResponse.result, rowParseResponse.reason);
    }

    @Override
    public String toString() {
        return String.format("%s : %s", null == board ? "FAILURE" : "SUCCESS", reason);
    }
}
