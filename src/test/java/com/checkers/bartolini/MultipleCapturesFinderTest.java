package com.checkers.bartolini;

import com.checkers.bartolini.checkersModel.*;
import org.junit.jupiter.api.Test;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleCapturesFinderTest {

    @Test
    void BoardCopyTest() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8, 8, 3, new NormalPawnFactory()); //standard builder creates pawns that cannot move backwards
        Board board = builder.createBoard();
        Board copy = board.clone();
        try {
            List<Move> originalBoardMoves = board.getFieldAtPos(5, 2).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(board, 5, 2);
            List<Move> copyBoardMoves = copy.getFieldAtPos(5, 2).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(copy, 5, 2);
            assertNotEquals(originalBoardMoves.size(), 0);
            assertNotEquals(copyBoardMoves.size(), 0);
            originalBoardMoves.get(1).applyMove();
            copyBoardMoves.get(1).applyMove();
            assertEquals(board.toString(), copy.toString());
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        } catch (IncorrectMoveException e) {
            fail();
        }
    }

    @Test
    void findsDoubleCapturesTest() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(5, 5, 0, new NormalPawnFactory());

        Board board = builder.createBoard();
        for(int i = 0;i<5;i++) {
            for(int j = 0;j<5;j++) {
                assertFalse(board.getFieldAtPos(i,j).containsPawn());
            }
        }
        board.getFieldAtPos(0, 0).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 3).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        assertTrue(board.getFieldAtPos(0, 0).containsPawn());
        assertTrue(board.getFieldAtPos(1, 1).containsPawn());
        assertTrue(board.getFieldAtPos(3, 3).containsPawn());
        assertFalse(board.getFieldAtPos(2, 2).containsPawn());
        assertFalse(board.getFieldAtPos(4, 4).containsPawn());
        MultipleCapturesFinder finder = new StandardMultipleCapturesFinder();
        try {
            List<Move> allCaptures = finder.getMultipleCaptures(board, 0, 0);
            assertEquals(allCaptures.size(), 2);
            allCaptures.get(1).applyMove();
            assertFalse(board.getFieldAtPos(0, 0).containsPawn());
            assertFalse(board.getFieldAtPos(1, 1).containsPawn());
            assertFalse(board.getFieldAtPos(3, 3).containsPawn());
            assertFalse(board.getFieldAtPos(2, 2).containsPawn());
            assertTrue(board.getFieldAtPos(4, 4).containsPawn());
            allCaptures.get(1).undoMove();
            assertTrue(board.getFieldAtPos(0, 0).containsPawn());
            assertTrue(board.getFieldAtPos(1, 1).containsPawn());
            assertTrue(board.getFieldAtPos(3, 3).containsPawn());
            assertFalse(board.getFieldAtPos(2, 2).containsPawn());
            assertFalse(board.getFieldAtPos(4, 4).containsPawn());
        } catch (InvalidAlgorithmParameterException e) {
            fail(e.toString());
        } catch (IncorrectMoveException e) {
            fail(e.toString());
        }
    }

    @Test
    void correctlyFindAllMultipleTakesInTripleTake() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8, 8, 0, new NormalPawnFactory());

        Board board = builder.createBoard();

        board.getFieldAtPos(0, 0).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 3).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(5,5).setPawn(new Pawn(Pawn.Team.WHITE,new NormalPawnPossibleMovesChecker(true,true ), new TextureWrapper()));
        assertTrue(board.getFieldAtPos(0, 0).containsPawn());
        assertTrue(board.getFieldAtPos(1, 1).containsPawn());
        assertTrue(board.getFieldAtPos(3, 3).containsPawn());
        assertTrue(board.getFieldAtPos(5,5).containsPawn());
        assertFalse(board.getFieldAtPos(2, 2).containsPawn());
        assertFalse(board.getFieldAtPos(4, 4).containsPawn());
        assertFalse(board.getFieldAtPos(6,6).containsPawn());
        MultipleCapturesFinder finder = new StandardMultipleCapturesFinder();
        try {
            List<Move> allCaptures = finder.getMultipleCaptures(board, 0, 0);
            assertEquals(allCaptures.size(), 3);
            allCaptures.get(2).applyMove();
            assertFalse(board.getFieldAtPos(0, 0).containsPawn());
            assertFalse(board.getFieldAtPos(1, 1).containsPawn());
            assertFalse(board.getFieldAtPos(3, 3).containsPawn());
            assertFalse(board.getFieldAtPos(5,5).containsPawn());
            assertFalse(board.getFieldAtPos(2, 2).containsPawn());
            assertFalse(board.getFieldAtPos(4, 4).containsPawn());
            assertTrue(board.getFieldAtPos(6,6).containsPawn());
            allCaptures.get(2).undoMove();
            assertTrue(board.getFieldAtPos(0, 0).containsPawn());
            assertTrue(board.getFieldAtPos(1, 1).containsPawn());
            assertTrue(board.getFieldAtPos(3, 3).containsPawn());
            assertTrue(board.getFieldAtPos(5,5).containsPawn());
            assertFalse(board.getFieldAtPos(2, 2).containsPawn());
            assertFalse(board.getFieldAtPos(4, 4).containsPawn());
            assertFalse(board.getFieldAtPos(6,6).containsPawn());
            allCaptures.get(1).applyMove();
            assertFalse(board.getFieldAtPos(0, 0).containsPawn());
            assertFalse(board.getFieldAtPos(1, 1).containsPawn());
            assertFalse(board.getFieldAtPos(3, 3).containsPawn());
            assertTrue(board.getFieldAtPos(5,5).containsPawn());
            assertFalse(board.getFieldAtPos(2, 2).containsPawn());
            assertTrue(board.getFieldAtPos(4, 4).containsPawn());
            assertFalse(board.getFieldAtPos(6,6).containsPawn());
            allCaptures.get(1).undoMove();
            assertTrue(board.getFieldAtPos(0, 0).containsPawn());
            assertTrue(board.getFieldAtPos(1, 1).containsPawn());
            assertTrue(board.getFieldAtPos(3, 3).containsPawn());
            assertTrue(board.getFieldAtPos(5,5).containsPawn());
            assertFalse(board.getFieldAtPos(2, 2).containsPawn());
            assertFalse(board.getFieldAtPos(4, 4).containsPawn());
            assertFalse(board.getFieldAtPos(6,6).containsPawn());
        } catch (InvalidAlgorithmParameterException e) {
            fail(e.toString());
        } catch (IncorrectMoveException e) {
            fail(e.toString());
        }
    }

    @Test
    void correctlyFindAllCapturesInPossibleTripleCaptureForQueen() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(10, 10, 0, new NormalPawnFactory());

        Board board = builder.createBoard();

        board.getFieldAtPos(7, 2).setPawn(new Pawn(Pawn.Team.BLACK, new QueenPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 6).setPawn(new Pawn(Pawn.Team.WHITE, new QueenPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 6).setPawn(new Pawn(Pawn.Team.WHITE, new QueenPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(2, 3).setPawn(new Pawn(Pawn.Team.WHITE, new QueenPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        MultipleCapturesFinder finder = new StandardMultipleCapturesFinder();
        assertTrue(board.getFieldAtPos(7,2).containsPawn());
        assertTrue(board.getFieldAtPos(3,6).containsPawn());
        assertTrue(board.getFieldAtPos(1,6).containsPawn());
        assertTrue(board.getFieldAtPos(2,3).containsPawn());
        assertFalse(board.getFieldAtPos(3,2).containsPawn());
        try {
            List<Move> allCaptures = finder.getMultipleCaptures(board, 7, 2);
            assertEquals(allCaptures.size(), 3);
            allCaptures.get(2).applyMove();
            assertFalse(board.getFieldAtPos(7,2).containsPawn());
            assertFalse(board.getFieldAtPos(3,6).containsPawn());
            assertFalse(board.getFieldAtPos(1,6).containsPawn());
            assertFalse(board.getFieldAtPos(2,3).containsPawn());
            assertTrue(board.getFieldAtPos(3,2).containsPawn());
            allCaptures.get(2).undoMove();
            assertTrue(board.getFieldAtPos(7,2).containsPawn());
            assertTrue(board.getFieldAtPos(3,6).containsPawn());
            assertTrue(board.getFieldAtPos(1,6).containsPawn());
            assertTrue(board.getFieldAtPos(2,3).containsPawn());
            assertFalse(board.getFieldAtPos(3,2).containsPawn());
        } catch (InvalidAlgorithmParameterException e) {
            fail(e.toString());
        } catch (IncorrectMoveException e) {
            fail(e.toString());
        }
    }

    @Test
    void returnsEmptyListIfNoCapturesArePossible() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(10, 10, 0, new NormalPawnFactory());

        Board board = builder.createBoard();

        board.getFieldAtPos(7, 2).setPawn(new Pawn(Pawn.Team.BLACK, new QueenPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        MultipleCapturesFinder finder = new StandardMultipleCapturesFinder();
        assertTrue(board.getFieldAtPos(7,2).containsPawn());
        try {
            List<Move> allCaptures = finder.getMultipleCaptures(board, 7, 2);
            assertEquals(allCaptures.size(), 0);
        } catch (InvalidAlgorithmParameterException e) {
            fail(e.toString());
        }
    }
}
