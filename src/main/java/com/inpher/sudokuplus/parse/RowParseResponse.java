package com.inpher.sudokuplus.parse;

public class RowParseResponse {
    public final ParseResult result;
    public final String reason;
    public final int[] row;

    private RowParseResponse(int[] row, ParseResult result, String reason) {
        this.row = row;
        this.result = result;
        this.reason = reason;
    }

    public static RowParseResponse ok(int[] row) {
        return new RowParseResponse(row, ParseResult.SUCCESS, "");
    }

    public static RowParseResponse fail(ParseResult result, String reason) {
        return new RowParseResponse(null, result, reason);
    }
}
