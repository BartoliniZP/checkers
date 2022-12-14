package com.checkers.bartolini.checkersModel;

public class QueenPawn extends Pawn {
    @Override
    public Team getTeam() {
        return super.getTeam();
    }

    public QueenPawn(Team team) {
        super(team);
    }

    @Override
    public PossibleMovesChecker getPossibleMovesChecker() {

        return new QueenPawnPossibleMovesChecker();
    }

    @Override
    public TextureWrapper getPawnTexture() {
        //TODO implement
        return null;
    }
}
