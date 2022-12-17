package com.checkers.bartolini.checkersModel;

public interface PawnFactory {
    Pawn getPawn(Pawn.Team team);


    Pawn getQueen(Pawn.Team team);
}
