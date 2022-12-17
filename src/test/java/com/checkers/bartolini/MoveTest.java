package com.checkers.bartolini;

import com.checkers.bartolini.checkersModel.*;
import org.junit.jupiter.api.Test;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {
    @Test
    void moveWithoutCaptureProperly() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8, 8, 3, new NormalPawnFactory()); //standard builder creates pawns that cannot move backwards
        Board board = builder.createBoard();
        assertFalse(board.getFieldAtPos(3, 0).containsPawn());
        assertTrue(board.getFieldAtPos(2, 1).containsPawn());
        Move move = new Move(board.getFieldAtPos(2, 1), board.getFieldAtPos(3, 0), null);
        try {
            move.applyMove();
            assertTrue(board.getFieldAtPos(3, 0).containsPawn());
            assertFalse(board.getFieldAtPos(2, 1).containsPawn());
        } catch (IncorrectMoveException e) {
            fail();
        }
    }

    @Test
    void moveCaptureProperly() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(5, 5, 0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(2, 2).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        assertTrue(board.getFieldAtPos(2, 2).containsPawn());
        assertTrue(board.getFieldAtPos(1, 1).containsPawn());
        assertFalse(board.getFieldAtPos(0, 0).containsPawn());

        Move move = new Move(board.getFieldAtPos(2, 2), board.getFieldAtPos(0, 0), board.getFieldAtPos(1, 1));
        try {
            move.applyMove();
            assertFalse(board.getFieldAtPos(2, 2).containsPawn());
            assertFalse(board.getFieldAtPos(1, 1).containsPawn());
            assertTrue(board.getFieldAtPos(0, 0).containsPawn());
        } catch (IncorrectMoveException e) {
            fail();
        }
    }

    @Test
    void moveCaptureUndoProperly() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(5, 5, 0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(2, 2).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        assertTrue(board.getFieldAtPos(2, 2).containsPawn());
        assertTrue(board.getFieldAtPos(1, 1).containsPawn());
        assertFalse(board.getFieldAtPos(0, 0).containsPawn());

        Move move = new Move(board.getFieldAtPos(2, 2), board.getFieldAtPos(0, 0), board.getFieldAtPos(1, 1));
        try {
            move.applyMove();
            assertFalse(board.getFieldAtPos(2, 2).containsPawn());
            assertFalse(board.getFieldAtPos(1, 1).containsPawn());
            assertTrue(board.getFieldAtPos(0, 0).containsPawn());
            move.undoMove();
            assertTrue(board.getFieldAtPos(2, 2).containsPawn());
            assertTrue(board.getFieldAtPos(1, 1).containsPawn());
            assertFalse(board.getFieldAtPos(0, 0).containsPawn());
        } catch (IncorrectMoveException e) {
            fail();
        }
    }

    @Test
    void TestOfDoubleMove() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(5, 5, 0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(0, 0).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 3).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        assertTrue(board.getFieldAtPos(0, 0).containsPawn());
        assertTrue(board.getFieldAtPos(1, 1).containsPawn());
        assertTrue(board.getFieldAtPos(3, 3).containsPawn());
        assertFalse(board.getFieldAtPos(2,2).containsPawn());
        assertFalse(board.getFieldAtPos(4,4).containsPawn());
        try {
            List<Move> moves = board.getFieldAtPos(0, 0).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(board, 0, 0);
            assertFalse(moves.isEmpty());
            Move doubleMove = moves.get(0);
            doubleMove.setFollowingMove(new Move(board.getFieldAtPos(2, 2), board.getFieldAtPos(4, 4), board.getFieldAtPos(3, 3)));
            doubleMove.applyMove();
            assertEquals(doubleMove.getDestination().getRow(),4);
            assertEquals(doubleMove.getDestination().getColumn(),4);
            assertFalse(board.getFieldAtPos(0, 0).containsPawn());
            assertFalse(board.getFieldAtPos(1, 1).containsPawn());
            assertFalse(board.getFieldAtPos(3, 3).containsPawn());
            assertFalse(board.getFieldAtPos(2,2).containsPawn());
            assertTrue(board.getFieldAtPos(4,4).containsPawn());
            doubleMove.undoMove();
            assertTrue(board.getFieldAtPos(0, 0).containsPawn());
            assertTrue(board.getFieldAtPos(1, 1).containsPawn());
            assertTrue(board.getFieldAtPos(3, 3).containsPawn());
            assertFalse(board.getFieldAtPos(2,2).containsPawn());
            assertFalse(board.getFieldAtPos(4,4).containsPawn());
            assertEquals(2,doubleMove.getMoveSize());
            assertEquals("00 11 22 33 44/",doubleMove.toString());
        } catch(InvalidAlgorithmParameterException e) {
            fail();
        }
        catch(IncorrectMoveException e) {
            fail();
        }
    }
}
