package com.checkers.bartolini;

import com.checkers.bartolini.checkersModel.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoImposedMoveRestrictionsTest {
    @Test
    void returnCorrectMovesAtStartPos() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8, 8, 3, new NormalPawnFactory()); //standard builder creates pawns that cannot move backwards
        Board board = builder.createBoard();
        PermittedMovesRules rules = new NoImposedMoveRestrictions();
        List<Move> possibleWhiteMoves = rules.getPermittedMoves(board, Pawn.Team.WHITE);
        assertEquals(possibleWhiteMoves.size(),7);
        List<Move> possibleBlackMoves = rules.getPermittedMoves(board, Pawn.Team.BLACK);
        assertEquals(possibleBlackMoves.size(),7);
    }

    @Test
    void returnsMoveSingleCaptureAndMultipleCapture() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8, 8, 0, new NormalPawnFactory()); //standard builder creates pawns that cannot move backwards
        Board board = builder.createBoard();
        board.getFieldAtPos(6, 6).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper()));
        board.getFieldAtPos(5,5).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(false,true), new TextureWrapper()));
        board.getFieldAtPos(5,3).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(false,true), new TextureWrapper()));
        PermittedMovesRules rules = new NoImposedMoveRestrictions();
        List<Move> possibleWhiteMoves = rules.getPermittedMoves(board, Pawn.Team.WHITE);
        assertEquals(possibleWhiteMoves.size(),3);
    }
}
