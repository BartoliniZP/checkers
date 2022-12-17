package com.checkers.bartolini.checkersModel;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

public class AnyTakeObligatory implements PermittedMovesRules {
    @Override
    public List<Move> getPermittedMoves(Board board, Pawn.Team team) {
        MultipleCapturesFinder captureFinder = new StandardMultipleCapturesFinder();
        List<Move> toReturn = new ArrayList<>();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getFieldAtPos(i, j).containsPawn() && board.getFieldAtPos(i, j).getPawnOnField().getTeam() == team) {
                    try {
                        toReturn.addAll(captureFinder.getMultipleCaptures(board,i,j));
                    }
                    catch(InvalidAlgorithmParameterException e) {

                    }
                }
            }
        }
        if(toReturn.isEmpty()) {
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (board.getFieldAtPos(i, j).containsPawn() && board.getFieldAtPos(i, j).getPawnOnField().getTeam() == team) {
                        try {
                            toReturn.addAll(board.getFieldAtPos(i, j).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(board, i, j));
                        }
                        catch(InvalidAlgorithmParameterException e) {

                        }
                    }
                }
            }
        }
        return toReturn;
    }
}
