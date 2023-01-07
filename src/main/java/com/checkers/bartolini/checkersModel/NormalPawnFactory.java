package com.checkers.bartolini.checkersModel;

public class NormalPawnFactory implements PawnFactory {
    @Override
    public Pawn getPawn(Pawn.Team team) {
        if (team == Pawn.Team.WHITE) {
            return new Pawn(team, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper("P W"));
        } else {
            return new Pawn(team, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper("P B"));
        }
    }

    @Override
    public Pawn getQueen(Pawn.Team team) {
        if (team == Pawn.Team.WHITE) {
            return new Pawn(team, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper("Q W"));
        } else {
            return new Pawn(team, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper("Q B"));
        }
    }
}
