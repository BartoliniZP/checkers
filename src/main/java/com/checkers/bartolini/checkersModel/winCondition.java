package com.checkers.bartolini.checkersModel;

public interface winCondition {
    gameState checkForGameFinish(Board board);

    enum gameState {
        WhiteWin, BlackWin, InvalidState, noWinner
    }
}
