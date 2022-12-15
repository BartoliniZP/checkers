package com.checkers.bartolini.checkersModel;

public class NormalPawnFactory implements PawnFactory {
    @Override
    public Pawn getPawn(Pawn.Team team) {
        return new Pawn(team,new NormalPawnPossibleMovesChecker(false,true),new TextureWrapper());
    }
}
