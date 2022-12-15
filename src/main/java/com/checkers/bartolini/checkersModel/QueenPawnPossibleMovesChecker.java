package com.checkers.bartolini.checkersModel;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

public class QueenPawnPossibleMovesChecker extends PossibleMovesChecker {
    @Override
    public List<Field> getPossibleMoves(Board board, int row, int column) throws InvalidAlgorithmParameterException {
        if (!board.getFieldAtPos(row, column).containsPawn()) {
            throw new InvalidAlgorithmParameterException();
        }
        Pawn.Team team = board.getFieldAtPos(row, column).getPawnOnField().getTeam();
        ArrayList<Field> toReturn = new ArrayList<>();
        Field possibleMove;
        for (int i = 0; i < Math.min(row, column); i++) {
            possibleMove = getLeftUpperField(board, row - i, column - i);
            if (possibleMove == null || possibleMove.containsPawn()) break;
            toReturn.add(possibleMove);
        }
        for (int i = 0; i < Math.min(row, board.getWidth() - column - 1); i++) {
            possibleMove = getRightUpperField(board, row - i, column + i);
            if (possibleMove == null || possibleMove.containsPawn()) break;
            toReturn.add(possibleMove);
        }
        for (int i = 0; i < Math.min(board.getHeight() - row - 1, column); i++) {
            possibleMove = getLeftLowerField(board, row + i, column - i);
            if (possibleMove == null || possibleMove.containsPawn()) break;
            toReturn.add(possibleMove);
        }
        for (int i = 0; i < Math.min(board.getHeight() - row - 1, board.getWidth() - column - 1); i++) {
            possibleMove = getRightLowerField(board, row + i, column + i);
            if (possibleMove == null || possibleMove.containsPawn()) break;
            toReturn.add(possibleMove);
        }
        return toReturn;
    }

    @Override
    public List<Field> getPossibleTakes(Board board, int row, int col) throws InvalidAlgorithmParameterException {
        if (!board.getFieldAtPos(row, col).containsPawn()) {
            throw new InvalidAlgorithmParameterException();
        }
        Pawn.Team team = board.getFieldAtPos(row, col).getPawnOnField().getTeam();
        ArrayList<Field> toReturn = new ArrayList<>();
        Field possibleMove;
        Field possiblePositionAfterTake;
        for (int i = 0; i < Math.min(row, col); i++) {
            possibleMove = getLeftUpperField(board, row - i, col - i);
            if (possibleMove == null) break;
            if (possibleMove.containsPawn()) {
                possiblePositionAfterTake = getLeftUpperField(board, possibleMove.getRow(), possibleMove.getColumn());
                if (possiblePositionAfterTake != null && !possiblePositionAfterTake.containsPawn()) {
                    toReturn.add(possiblePositionAfterTake);
                }
                break;
            }
        }
        for (int i = 0; i < Math.min(row, board.getWidth() - col - 1); i++) {
            possibleMove = getRightUpperField(board, row - i, col + i);
            if (possibleMove == null) break;
            if (possibleMove.containsPawn()) {
                possiblePositionAfterTake = getRightUpperField(board, possibleMove.getRow(), possibleMove.getColumn());
                if (possiblePositionAfterTake != null && !possiblePositionAfterTake.containsPawn()) {
                    toReturn.add(possiblePositionAfterTake);
                }
                break;
            }
        }
        for (int i = 0; i < Math.min(board.getHeight() - row - 1, col); i++) {
            possibleMove = getLeftLowerField(board, row + i, col - i);
            if (possibleMove == null) break;
            if (possibleMove.containsPawn()) {
                possiblePositionAfterTake = getLeftLowerField(board, possibleMove.getRow(), possibleMove.getColumn());
                if (possiblePositionAfterTake != null && !possiblePositionAfterTake.containsPawn()) {
                    toReturn.add(possiblePositionAfterTake);
                }
                break;
            }
        }
        for (int i = 0; i < Math.min(board.getHeight() - row - 1, board.getWidth() - col - 1); i++) {
            possibleMove = getRightLowerField(board, row + i, col + i);
            if (possibleMove == null) break;
            if (possibleMove.containsPawn()) {
                possiblePositionAfterTake = getRightLowerField(board, possibleMove.getRow(), possibleMove.getColumn());
                if (possiblePositionAfterTake != null && !possiblePositionAfterTake.containsPawn()) {
                    toReturn.add(possiblePositionAfterTake);
                }
                break;
            }

        }
        return toReturn;
    }

    @Override
    public int getLongestTake(Field position) {
        //TODO implement
        return 0;
    }

    public QueenPawnPossibleMovesChecker(boolean canMoveBackwards, boolean canTakeBackwards) {
        super(canMoveBackwards, canTakeBackwards);
    }
}



