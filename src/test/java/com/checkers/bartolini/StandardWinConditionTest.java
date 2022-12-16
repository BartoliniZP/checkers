package com.checkers.bartolini;

import com.checkers.bartolini.checkersModel.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardWinConditionTest {
    @Test
    void onDefaultBoardReturnsNoWinner() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8,8,3, new NormalPawnFactory());
        Board board = builder.createBoard();
        winCondition resultChecker = new StandardWinCondition();
        boolean result = (winCondition.gameState.noWinner ==  resultChecker.checkForGameFinish(board));
        assertEquals(result,true);
    }

    @Test
    void ReturnsBlackWinIfWhiteTeamHasNoMoves() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8,8,0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(7, 7).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper()));
        board.getFieldAtPos(6, 6).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(5, 5).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        winCondition resultChecker = new StandardWinCondition();
        boolean result = (winCondition.gameState.BlackWin ==  resultChecker.checkForGameFinish(board));
        assertEquals(result,true);
    }

    @Test
    void ReturnsInvalidStateInWeirdSituation() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(2,2,1, new NormalPawnFactory());
        Board board = builder.createBoard();
        winCondition resultChecker = new StandardWinCondition();
        boolean result = (winCondition.gameState.InvalidState ==  resultChecker.checkForGameFinish(board));
        assertEquals(result,true);
    }

    @Test
    void ReturnsWhiteWinWhenOnlyWhitePawnOnBoard() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8,8,0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(3, 3).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(false, false), new TextureWrapper()));
        winCondition resultChecker = new StandardWinCondition();
        boolean result = (winCondition.gameState.WhiteWin ==  resultChecker.checkForGameFinish(board));
        assertEquals(result,true);
    }
}
