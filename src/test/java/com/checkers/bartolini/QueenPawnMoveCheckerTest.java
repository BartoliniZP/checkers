package com.checkers.bartolini;

import com.checkers.bartolini.checkersModel.*;
import org.junit.jupiter.api.Test;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueenPawnMoveCheckerTest {
    @Test
    void correctlyHandlesAllDiagonals() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8,8,0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(3,3).setPawn(new Pawn(Pawn.Team.WHITE,new QueenPawnPossibleMovesChecker(true,true),new TextureWrapper()));
        try {
            List<Move> moves = board.getFieldAtPos(3, 3).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(board, 3, 3);
            String expectedOutput = "22110024150642516044556677";
            String output = "";
            for(Move field : moves) {
                output = output.concat(Integer.toString(field.getDestination().getRow()));
                output = output.concat(Integer.toString(field.getDestination().getColumn()));
            }
            assertEquals(expectedOutput,output);
        }
        catch(InvalidAlgorithmParameterException e) {
            fail();
        }
    }


    @Test
    void surroundedReturnsEmptyPossibleMoves() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(10, 10, 0, new NormalPawnFactory());

        Board board = builder.createBoard();
        board.getFieldAtPos(3, 3).setPawn(new Pawn(Pawn.Team.WHITE, new QueenPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(2, 2).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(2, 4).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(4, 2).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(4, 4).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        try {
            List<Move> possibleFields = board.getFieldAtPos(3, 3).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(board, 3, 3);
            String expectedOutput = "s";
            String output = "s";
            for (Move f : possibleFields) {
                output = output.concat(Integer.toString(f.getDestination().getRow()));
                output = output.concat(Integer.toString(f.getDestination().getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }

    @Test
    void correctlyDetectCaptures() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8,8,0, new NormalPawnFactory());
        Board board = builder.createBoard();
        board.getFieldAtPos(3,3).setPawn(new Pawn(Pawn.Team.WHITE,new QueenPawnPossibleMovesChecker(true,true),new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(4, 2).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(0, 6).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(6, 6).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        try {
            List<Move> possibleFields = board.getFieldAtPos(3, 3).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(board, 3, 3);
            String expectedOutput = "005177";
            String output = "";
            for (Move f : possibleFields) {
                output = output.concat(Integer.toString(f.getDestination().getRow()));
                output = output.concat(Integer.toString(f.getDestination().getColumn()));

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
        board.getFieldAtPos(2, 2).setPawn(new Pawn(Pawn.Team.BLACK, new QueenPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(1, 3).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 1).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(3, 3).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        try {
            List<Move> possibleFields = board.getFieldAtPos(2, 2).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(board, 2, 2);
            String expectedOutput = "00044044";
            String output = "";
            for (Move f : possibleFields) {
                output = output.concat(Integer.toString(f.getDestination().getRow()));
                output = output.concat(Integer.toString(f.getDestination().getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }

    @Test
    void surroundedByOwnTeamReturnsNoCaptures() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(10, 10, 0, new NormalPawnFactory());

        Board board = builder.createBoard();
        board.getFieldAtPos(3, 3).setPawn(new Pawn(Pawn.Team.WHITE, new QueenPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(2, 2).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(2, 4).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(4, 2).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        board.getFieldAtPos(4, 4).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(true, true), new TextureWrapper()));
        try {
            List<Move> possibleFields = board.getFieldAtPos(3, 3).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(board, 3, 3);
            String expectedOutput = "s";
            String output = "s";
            for (Move f : possibleFields) {
                output = output.concat(Integer.toString(f.getDestination().getRow()));
                output = output.concat(Integer.toString(f.getDestination().getColumn()));

            }
            assertEquals(expectedOutput, output);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
    }
}
