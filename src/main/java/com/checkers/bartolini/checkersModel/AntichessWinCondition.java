package com.checkers.bartolini.checkersModel;

public class AntichessWinCondition implements winCondition {
    @Override
    public gameState checkForGameFinish(Board board) {
        StandardWinCondition s = new StandardWinCondition();
        gameState standardWinCondition = s.checkForGameFinish(board);
        if(standardWinCondition==gameState.WhiteWin) {
            return gameState.BlackWin;
        } else if(standardWinCondition==gameState.BlackWin) {
            return  gameState.WhiteWin;
        } else {
            return standardWinCondition;
        }
    }
}
