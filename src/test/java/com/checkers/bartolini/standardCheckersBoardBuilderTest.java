package com.checkers.bartolini;

import com.checkers.bartolini.checkersModel.Board;
import com.checkers.bartolini.checkersModel.StandardCheckersBoardBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class standardCheckersBoardBuilderTest {
    @Test
    void TestIfCorrectlyBuildsEightByEightBoard() {
        String expectedOutput = "EPEPEPEP\nPEPEPEPE\nEPEPEPEP\nEEEEEEEE\nEEEEEEEE\nPEPEPEPE\nEPEPEPEP\nPEPEPEPE\n";
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8,8,3);
        Board eightEight = builder.createBoard();
        String boardToString = eightEight.toString();
        assertEquals(expectedOutput,boardToString);
    }
    @Test
    void TestIfCorrectlyBuildsTenByTenBoard() {
        String expectedOutput = "EPEPEPEPEP\nPEPEPEPEPE\nEPEPEPEPEP\nPEPEPEPEPE\nEEEEEEEEEE\nEEEEEEEEEE\nEPEPEPEPEP\nPEPEPEPEPE\nEPEPEPEPEP\nPEPEPEPEPE\n";
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(10,10,4);
        Board eightEight = builder.createBoard();
        String boardToString = eightEight.toString();
        assertEquals(expectedOutput,boardToString);
    }
}
