package com.inpher.sudoku;

import com.inpher.sudoku.model.SudokuBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) {
        if(args != null && args.length > 1 || args[0] != null) {
            if(SudokuBoard.of(args[0]).isValidSudokuPlus()) {
                logger.info("The given board is a valid sudoku!!!");
            } else {
                logger.info("The given board is NOT! a valid sudoku");
            }
        } else {
            logger.info("None file given");
        }
    }
}
