package com.checkers.bartolini.checkersModel;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

public class QueenPawnPossibleMovesChecker extends PossibleMovesChecker {
    @Override
    public List<Field> getPossibleMoves(Board board, int row, int column) {
        //TODO implement
        return null;
    }

    @Override
    public List<Field> getPossibleTakes(Board board, int row, int col) throws InvalidAlgorithmParameterException {
        return null;
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



