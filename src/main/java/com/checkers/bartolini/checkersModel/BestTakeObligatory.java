package com.checkers.bartolini.checkersModel;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

public class BestTakeObligatory implements PermittedMovesRules {
    @Override
    public List<Move> getPermittedMoves(Board board, Pawn.Team team) {
        MultipleCapturesFinder captureFinder = new StandardMultipleCapturesFinder();
        List<Move> toReturn = new ArrayList<>();
        toReturn.addAll(PermittedMovesRules.getAllPossibleTakes(board,team));
        if(toReturn.isEmpty()) {
            toReturn.addAll(PermittedMovesRules.getPossibleMoves(board,team));
            return toReturn;
        } else {
            int longestFoundTake = 0;
            List<Move> longestTakes = new ArrayList<>();
            for(Move m : toReturn) {
                if(m.getMoveSize()>longestFoundTake) {
                    longestFoundTake=m.getMoveSize();
                    longestTakes.clear();
                    longestTakes.add(m);
                } else if (m.getMoveSize()==longestFoundTake) {
                    longestTakes.add(m);
                }
            }
            return longestTakes;
        }

    }
}
