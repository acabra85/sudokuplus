package com.inpher.sudokuplus;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    public void callWithEmptyParams() {
        String[] args = {};
        Main.main(args);
    }

    @Test
    public void callWithNull() {
        String[] args = null;
        Main.main(args);
    }

    @Test
    public void callWithNullFirstParam() {
        String[] args = {null, ""};
        Main.main(args);
    }

    @Test
    public void callWithValidBoardParam() {
        String[] args = {TestUtils.getFullPathForResourceFile("sampleInput_4x4.txt"), ""};
        Main.main(args);
    }

    @Test
    public void callWithInvalidBoardParam() {
        String[] args = {TestUtils.getFullPathForResourceFile("sampleInput_4x4_invalid6.txt")};
        Main.main(args);
    }

    @Test
    public void callWithInvalidFile() {
        String[] args = {"invalidFile.txt"};
        Main.main(args);
    }

    @Test
    public void callWithNonExistentFile() {
        String[] args = {"non-existent-file"};
        Main.main(args);
    }
}