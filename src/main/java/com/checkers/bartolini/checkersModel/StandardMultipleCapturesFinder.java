package com.checkers.bartolini.checkersModel;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

public class StandardMultipleCapturesFinder implements MultipleCapturesFinder {

    public static List<Move> getAllPossibleTakesForTeam(Board board, Pawn.Team team) {
        List<Move> toReturn = new ArrayList<>();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getFieldAtPos(i, j).containsPawn()) {
                    if (board.getFieldAtPos(i, j).getPawnOnField().getTeam() == team) {
                        try {
                            List<Move> takes = board.getFieldAtPos(i, j).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(board, i, j);
                            toReturn.addAll(takes);
                        } catch (InvalidAlgorithmParameterException e) {

                        }
                    }
                }
            }
        }
        return toReturn;
    }

    @Override
    public List<Move> getMultipleCaptures(Board board, int row, int col) throws InvalidAlgorithmParameterException {
        Board boardCopy = board.clone();
        List<Move> captures = boardCopy.getFieldAtPos(row, col).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(boardCopy, row, col);
        List<Move> multipleCaptures = new ArrayList<>();
        int prevCapturesSize=0;
        while(prevCapturesSize!=captures.size()) {
            for (int i = prevCapturesSize; i < captures.size(); i++) {
                Move move = captures.get( i);
                try {
                    move.applyMove();
                    List<Move> PossibleCapturesAfterMove = boardCopy.getFieldAtPos(move.getDestination().getRow(), move.getDestination().getColumn()).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(boardCopy, move.getDestination().getRow(), move.getDestination().getColumn());
                    for (Move nextMove : PossibleCapturesAfterMove) {
                        Move toAdd = move.clone();
                        toAdd.setFollowingMove(nextMove);
                        multipleCaptures.add(toAdd);
                    }
                    move.undoMove();
                } catch (IncorrectMoveException e) {

                }
            }
            prevCapturesSize = captures.size();
            captures.addAll(multipleCaptures);
            multipleCaptures.clear();
        }
        List<Move> toReturn = new ArrayList<>();
        for (Move m : captures) {
            toReturn.add(Move.translateMove(board, m));
        }
        return toReturn;
    }
}
