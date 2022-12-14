package com.checkers.bartolini.checkersModel;

import java.util.Vector;

public interface PossibleMovesChecker {
    Vector<Field> getPossibleMoves(Field position);
    int getLongestTake(Field position);
}
