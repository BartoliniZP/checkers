package com.checkers.bartolini.checkersModel;

public class NormalPawn extends Pawn {

    public NormalPawn(Team team) {
        super(team);
    }

    @Override
    public PossibleMovesChecker getPossibleMovesChecker() {
        return new NormalPawnPossibleMovesChecker();
    }

    @Override
    public TextureWrapper getPawnTexture() {
        //TODO implement
        return null;
    }
}
