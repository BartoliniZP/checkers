package com.checkers.bartolini.checkersModel;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

public abstract class PossibleMovesChecker {
    public abstract List<Field> getPossibleMoves(Board board, int row, int column) throws InvalidAlgorithmParameterException;
    public abstract List<Field> getPossibleTakes(Board board, int row, int col) throws InvalidAlgorithmParameterException;
    public abstract int getLongestTake(Field position);

    private boolean CanMoveBackwards;
    private boolean CanTakeBackwards;


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
}
