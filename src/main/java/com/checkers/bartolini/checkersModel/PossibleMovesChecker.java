package com.checkers.bartolini.checkersModel;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

public abstract class PossibleMovesChecker {
    public abstract List<Move> getPossibleMoves(Board board, int row, int column) throws InvalidAlgorithmParameterException;
    public abstract List<Move> getPossibleTakes(Board board, int row, int col) throws InvalidAlgorithmParameterException;

    private boolean CanMoveBackwards;
    private boolean CanTakeBackwards;

    public PossibleMovesChecker(PossibleMovesChecker toCopy) {
        this.CanMoveBackwards = toCopy.CanMoveBackwards();
        this.CanTakeBackwards = toCopy.CanTakeBackwards();
    }
    protected Field getLeftUpperField(Board board, int row, int column) {
        if (row != 0) {
            if (column != 0) {
                return board.getFieldAtPos(row - 1, column - 1);
            }
        }
        return null;
    }

    protected Field getRightUpperField(Board board, int row, int column) {
        if (row != 0) {
            if (column != (board.getWidth() - 1)) {
                return board.getFieldAtPos(row - 1, column + 1);
            }
        }
        return null;
    }

    protected Field getLeftLowerField(Board board, int row, int column) {
        if (row != (board.getHeight() - 1)) {
            if (column != 0) {
                return board.getFieldAtPos(row + 1, column - 1);
            }
        }
        return null;
    }

    protected Field getRightLowerField(Board board, int row, int column) {
        if (row != (board.getHeight() - 1)) {
            if (column != (board.getWidth() - 1)) {
                return board.getFieldAtPos(row + 1, column + 1);
            }
        }
        return null;
    }
    public boolean CanMoveBackwards() {
        return CanMoveBackwards;
    }

    public boolean CanTakeBackwards() {
        return CanTakeBackwards;
    }

    public PossibleMovesChecker(boolean canMoveBackwards, boolean canTakeBackwards) {
        CanMoveBackwards = canMoveBackwards;
        CanTakeBackwards = canTakeBackwards;
    }

    public abstract PossibleMovesChecker clone();
}
