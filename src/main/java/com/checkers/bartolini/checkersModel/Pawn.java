package com.checkers.bartolini.checkersModel;

public abstract class Pawn {
    public enum Team {
        WHITE, BLACK
    }
    Team team;

    public Team getTeam() {
        return team;
    }

    public Pawn(Team team) {
        this.team = team;
    }

    public abstract PossibleMovesChecker getPossibleMovesChecker();

    public abstract TextureWrapper getPawnTexture();

    @Override
    public String toString() {
        return "P";
    }

}
