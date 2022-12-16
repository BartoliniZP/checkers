package com.checkers.bartolini;

import com.checkers.bartolini.checkersModel.*;
import org.junit.jupiter.api.Test;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NormalPawnMoveCheckerTest {
    @Test
    public void TestWhetherNormalPawnMoveCheckerReturnsCorrectFields() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8, 8, 3, new NormalPawnFactory()); //standard builder creates pawns that cannot move backwards
        Board board = builder.createBoard();
        try {
            List<Field> possibleFields = board.getFieldAtPos(2, 1).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(board, 2, 1);
            String expectedOutput = "30324143";//Black pawn at start pos (2,1) can move to field (3,0), can move to field (3,2), White pawn at start pos (5,2) returns (4,1) (4,3)
            String output = "";
            for (Field f : possibleFields) {
                output = output.concat(Integer.toString(f.getRow()));
                output = output.concat(Integer.toString(f.getColumn()));

            }
            possibleFields = board.getFieldAtPos(5, 2).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(board, 5, 2);
            for (Field f : possibleFields) {
                output = output.concat(Integer.toString(f.getRow()));
                output = output.concat(Integer.toString(f.getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }

    @Test
    public void PawnWithEmptyNeighborsAndEnabledMovingBackwardsReturnsAllFourFields() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(3, 3, 0, new NormalPawnFactory());

        Board board = builder.createBoard();
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        try {
            List<Field> possibleFields = board.getFieldAtPos(1, 1).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(board, 1, 1);
            String expectedOutput = "00022022";
            String output = "";
            for (Field f : possibleFields) {
                output = output.concat(Integer.toString(f.getRow()));
                output = output.concat(Integer.toString(f.getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }

    @Test
    public void PawnLockedReturnsEmptyList() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(3, 3, 0, new NormalPawnFactory());

        Board board = builder.createBoard();
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(0, 0).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(0, 2).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(2, 0).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(2, 2).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        try {
            List<Field> possibleFields = board.getFieldAtPos(1, 1).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(board, 1, 1);
            String expectedOutput = "";
            String output = "";
            for (Field f : possibleFields) {
                output = output.concat(Integer.toString(f.getRow()));
                output = output.concat(Integer.toString(f.getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }

    @Test
    void NormalPawnPossibleMovesCheckerHandlesProperlyEdge() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(3, 3, 0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(0, 0).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper()));
        try {
            List<Field> possibleFields = board.getFieldAtPos(0, 0).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(board, 0, 0);
            String expectedOutput = "11";
            String output = "";
            for (Field f : possibleFields) {
                output = output.concat(Integer.toString(f.getRow()));
                output = output.concat(Integer.toString(f.getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }

    @Test
    void WhitePawnThatCannotMoveBackwardReturnNoPossibleMovesIn00Position() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(3, 3, 0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(0, 0).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper()));
        try {
            List<Field> possibleFields = board.getFieldAtPos(0, 0).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(board, 0, 0);
            String expectedOutput = "";
            String output = "";
            for (Field f : possibleFields) {
                output = output.concat(Integer.toString(f.getRow()));
                output = output.concat(Integer.toString(f.getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }

    @Test
    void ReturnsFourCapturesWhenSurroundedByOtherColorPawns() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(5, 5, 0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(2, 2).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 3).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 3).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        try {
            List<Field> possibleFields = board.getFieldAtPos(2, 2).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(board, 2, 2);
            String expectedOutput = "00044044";
            String output = "";
            for (Field f : possibleFields) {
                output = output.concat(Integer.toString(f.getRow()));
                output = output.concat(Integer.toString(f.getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }

    @Test
    void ReturnsNoCapturesIfSurroundedByOwnTeamPawns() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(5, 5, 0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(2, 2).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 3).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 1).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 3).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        try {
            List<Field> possibleFields = board.getFieldAtPos(2, 2).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(board, 2, 2);
            String expectedOutput = "";
            String output = "";
            for (Field f : possibleFields) {
                output = output.concat(Integer.toString(f.getRow()));
                output = output.concat(Integer.toString(f.getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }

    @Test
    void ReturnsOnlyTwoCapturesIfNotAllowTakeBackwards() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(5, 5, 0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(2, 2).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, false), new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 3).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 3).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        try {
            List<Field> possibleFields = board.getFieldAtPos(2, 2).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(board, 2, 2);
            String expectedOutput = "4044";
            String output = "";
            for (Field f : possibleFields) {
                output = output.concat(Integer.toString(f.getRow()));
                output = output.concat(Integer.toString(f.getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }

    @Test
    void DoesNotReturnCaptureIfThereIsNoFieldAfterPawn() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(5, 5, 0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(0, 0).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        try {
            List<Field> possibleFields = board.getFieldAtPos(1, 1).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(board, 1, 1);
            String expectedOutput = "";
            String output = "";
            for (Field f : possibleFields) {
                output = output.concat(Integer.toString(f.getRow()));
                output = output.concat(Integer.toString(f.getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }
}
