package com.checkers.bartolini.checkersModel;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

public class StandardWinCondition implements winCondition {


    public int countPossibleMoveForTeam(Board board, Pawn.Team team) {
        int toReturn = 0;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getFieldAtPos(i, j).containsPawn()) {
                    if (board.getFieldAtPos(i, j).getPawnOnField().getTeam() == team) {
                        toReturn++;
                    }
                }
            }
        }
        return toReturn;
    }

    public boolean TeamHasPossibleMoves(Board board, Pawn.Team team) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getFieldAtPos(i, j).containsPawn()) {
                    if (board.getFieldAtPos(i, j).getPawnOnField().getTeam() == team) {
                        try {
                            List<Field> moves = board.getFieldAtPos(i, j).getPawnOnField().getPossibleMovesChecker().getPossibleMoves(board, i, j);
                            if(!moves.isEmpty()) {
                                return true;
                            }
                            moves = board.getFieldAtPos(i, j).getPawnOnField().getPossibleMovesChecker().getPossibleTakes(board, i, j);
                            if(!moves.isEmpty()) {
                                return true;
                            }
                        }
                        catch(InvalidAlgorithmParameterException e) {

                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public gameState checkForGameFinish(Board board) {
        int whitePawnsCounter = countPossibleMoveForTeam(board, Pawn.Team.WHITE);
        int blackPawnsCounter = countPossibleMoveForTeam(board, Pawn.Team.BLACK);

        if ((whitePawnsCounter != 0 && blackPawnsCounter == 0) || (!TeamHasPossibleMoves(board, Pawn.Team.BLACK) && TeamHasPossibleMoves(board, Pawn.Team.WHITE))){
            return gameState.WhiteWin;
        } else if ((whitePawnsCounter == 0 && blackPawnsCounter != 0) || (!TeamHasPossibleMoves(board, Pawn.Team.WHITE) && TeamHasPossibleMoves(board, Pawn.Team.BLACK))) {
            return gameState.BlackWin;
        } else if (whitePawnsCounter != 0 && blackPawnsCounter != 0 && TeamHasPossibleMoves(board, Pawn.Team.BLACK) && TeamHasPossibleMoves(board, Pawn.Team.WHITE)) {
            return gameState.noWinner;
        } else {
            return gameState.InvalidState;
        }
    }
}
