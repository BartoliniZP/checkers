package com.checkers.bartolini;

import com.checkers.bartolini.checkersModel.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BestTakeObligatoryTest {
    @Test
    void returnCorrectMovesAtStartPos() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8, 8, 3, new NormalPawnFactory()); //standard builder creates pawns that cannot move backwards
        Board board = builder.createBoard();
        PermittedMovesRules rules = new BestTakeObligatory();
        List<Move> possibleWhiteMoves = rules.getPermittedMoves(board, Pawn.Team.WHITE);
        assertEquals(possibleWhiteMoves.size(),7);
        List<Move> possibleBlackMoves = rules.getPermittedMoves(board, Pawn.Team.BLACK);
        assertEquals(possibleBlackMoves.size(),7);
    }

    @Test
    void ifNoNeighborsReturnsTwoMovesIfAddMultipleCapturePossibleReturnsOneMove() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8, 8, 0, new NormalPawnFactory()); //standard builder creates pawns that cannot move backwards
        Board board = builder.createBoard();
        PermittedMovesRules rules = new BestTakeObligatory();
        board.getFieldAtPos(6, 6).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper()));
        List<Move> possibleMoves = rules.getPermittedMoves(board, Pawn.Team.WHITE);
        assertEquals(possibleMoves.size(),2);
        possibleMoves.clear();
        board.getFieldAtPos(5,5).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(false,true), new TextureWrapper()));
        possibleMoves = rules.getPermittedMoves(board, Pawn.Team.WHITE);
        assertEquals(possibleMoves.size(),1);
        board.getFieldAtPos(5,3).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(false,true), new TextureWrapper()));
        possibleMoves.clear();
        possibleMoves = rules.getPermittedMoves(board, Pawn.Team.WHITE);
        assertEquals(possibleMoves.size(),1);
    }

    @Test
    void ifMultipleSameLengthCapturesArePossibleReturnsBoth() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8, 8, 0, new NormalPawnFactory()); //standard builder creates pawns that cannot move backwards
        Board board = builder.createBoard();
        PermittedMovesRules rules = new BestTakeObligatory();
        board.getFieldAtPos(6, 6).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper()));
        board.getFieldAtPos(5,5).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(false,true), new TextureWrapper()));
        board.getFieldAtPos(5,3).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(false,true), new TextureWrapper()));
        board.getFieldAtPos(3,3).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(false,true), new TextureWrapper()));
        List<Move> possibleMoves = rules.getPermittedMoves(board, Pawn.Team.WHITE);
        assertEquals(possibleMoves.size(),2);
        board.getFieldAtPos(1,1).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(false,true), new TextureWrapper())); //adding take 3 pawns possible so now shall return one move
        possibleMoves.clear();
        possibleMoves =  rules.getPermittedMoves(board, Pawn.Team.WHITE);
        assertEquals(possibleMoves.size(),1);
    }
}
