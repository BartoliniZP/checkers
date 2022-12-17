package com.checkers.bartolini.checkersModel;

import java.util.List;

public interface PermittedMovesRules {
    List<Move> getPermittedMoves(Board board, Pawn.Team team);
}
