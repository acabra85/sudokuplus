package com.inpher.sudokuplus;

import com.inpher.sudokuplus.model.SudokuPlusBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private Main() {}

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) {
        if(args != null && args.length > 0 && args[0] != null) {
            SudokuPlusBoard sudokuPlusBoard = SudokuPlusBoard.fromFile(args[0]);
            if(null != sudokuPlusBoard && sudokuPlusBoard.isValidSudokuPlus()) {
                logger.info("The given board is a VALID sudoku plus!!!");
                return;
            }
            logger.info("The given board is an INVALID sudoku plus board");
        } else {
            logger.error("No file given, please note the <BOARD_SOURCE_FILE> is mandatory, refer to README.md for more");
        }
    }
}
