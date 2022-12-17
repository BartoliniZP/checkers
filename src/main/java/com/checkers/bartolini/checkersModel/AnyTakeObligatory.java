package com.checkers.bartolini.checkersModel;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

public class AnyTakeObligatory implements PermittedMovesRules {
    @Override
    public List<Move> getPermittedMoves(Board board, Pawn.Team team) {

        List<Move> toReturn = new ArrayList<>();
        toReturn.addAll(PermittedMovesRules.getAllPossibleTakes(board,team));
        if(toReturn.isEmpty()) {
            toReturn.addAll(PermittedMovesRules.getPossibleMoves(board,team));
        }
        return toReturn;
    }
}
